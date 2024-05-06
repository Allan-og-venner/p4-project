
import nodes.*;
import org.antlr.v4.runtime.misc.Pair;
import java.util.*;

/**
 * CodeBuilderVisitor is a visitor class that visits the AST and builds Java code.
 * It has methods to visit different nodes in the AST.
 * It builds the code using a StringBuilder.
 * */
public class CodeBuilderVisitor extends ASTVisitor<String>{

    private final ArrayList<String> functions = new ArrayList<>();
    private final ArrayList<String> mainClasses = new ArrayList<>();
    private final ArrayList<String> variables = new ArrayList<>();
    private final Hashtable<String, ClassStringBuilder> classes = new Hashtable<>();
    private final Hashtable<String, ArrayList<Pair<String,String>>> classFields = new Hashtable<>();
    private String gameFunction;
    private String endFunction;
    private int scopeCount;
    private String currentClass = "";
    private int playerAddedCalled;

    /**
     * Correctly converts a function call used as an action to parameters
     * play(card) becomes "play", card
     * @param node The first parameter for AllowAction or DisallowAction
     * @return a string
     */
    public String callToParam(ExpressionsNode node) {
        if (node == null)
            return "";
        if (node.getLeft() != null && node.getLeft() instanceof FunctionCallNode) {
            return "\"" + ((FunctionCallNode)node.getLeft()).getFunction().getText()
                    + "\"" +
                    (((FunctionCallNode) node.getLeft()).getParameter() != null ? ", " + visit(((FunctionCallNode) node.getLeft()).getParameter()) : "");

        }
        else throw new RuntimeException(node.getLineNumber() + " - Action called wrong");
    }

    /**
     * handleType is a method that takes a nonRealType as input and returns a real type.
     * It is used to handle the different types for arrays because the syntax for int, float, string is different in
     * Java and needs to be converted.
     * @param nonRealType the type to be converted
     * @return newType the converted type
     */
    public String handleType(String nonRealType) {
        String[] splitType;
        String newType = nonRealType;
        if (nonRealType.startsWith("array ")) {
            splitType = nonRealType.split("^array ");
            nonRealType = splitType[1];
            switch (nonRealType) {
                case "int" -> newType = "Integer";
                case "float" -> newType = "Float";
                case "string" -> newType = "String";
                default -> newType = nonRealType;
            }
        //Even if outside array, string should be changed to String, as string does not exist in Java
        } else if(nonRealType.equals("string")) {
            return "String";
        }
        return newType;
    }

    /**
     * Used in functionDNode to convert parameters to variables:
     * only save the parameters name and not the datatype in order to use
     * it in the function body
     * @param param the particular String to be formatted
     * @return param but converted to a variable with regex
     */
    public String paramToVar(String param) {
        return param
                .replaceFirst("^.* ", "")
                .replaceAll("[\\[\\]]", "");
    }

    /**
     * visitStart is a method that visits the start node of the AST.
     * It is used to start the visitation of the AST.
     * @param node the start node
     * @return the code built from the AST
     */
    public String visitStart(BlockNode node) {

        StringBuilder prog = new StringBuilder();
        //Make card

        //Make action class

        //Make main
        prog.append("import java.util.*;");
        prog.append("public class Main {");

        String setUp = visit(node);
        for (String var : variables) {
            prog.append(var);
        }

        prog.append(gameFunction);
        prog.append(endFunction);
        for (String mainClass : mainClasses) {
            prog.append(mainClass);
        }
        //Loop through all classes to add a closing curly bracket and add to the program
        for (String i : classes.keySet()) {
            prog.append(classes.get(i).close());
        }
        for (String function : functions) {
            System.out.println(function);
            prog.append(function);
        }

        prog.append("public static void main(String[] args) {")
                .append(setUp)
                .append("while (true) {").append("Main.game(); }")
                .append("}}");
        //If no game and end functions declared, throw exception
        if (gameFunction == null) {
            throw new MissingDefinitionException("Game");
        } else if (endFunction == null) {
            throw new MissingDefinitionException("End");
        }
        if (playerAddedCalled == 0){
            throw new MissingDefinitionException("Players");
        }



        return prog.toString();
    }

    /**
     * Visit is a method that visits a node in the AST.
     * It is used to visit the different nodes in the AST.
     * The following visit methods are used to then implement the behaviour of the
     * different nodes in the AST.
     * @param node the node to be visited
     * @return the code built from the node
     */
    @Override
    public String visit(BlockNode node) {
        //visit Statement node
        String blocks = "";
        if (node.getStatement() != null) {
            blocks = visit(node.getStatement());
        }
        if(!(node.getStatement() instanceof CardTypeNode || node.getStatement() instanceof FunctionDNode || node.getStatement() instanceof ClassDNode || node.getStatement() instanceof LoopNode))
            blocks += ";";

        //if existing visit block node
        if (node.getBlocks() != null) {
            blocks += visit(node.getBlocks());
        }
        return blocks;
    }

    @Override
    public String visit(AdditionNode node) {
        if (node.getLeft() instanceof CharNode || node.getRight() instanceof CharNode){
            return visit(node.getLeft())+ "+" + visit(node.getRight())+ "+" + '"' ;
        }
        return visit(node.getLeft()) + "+" + visit(node.getRight());
    }

    @Override
    public String visit(SubtractionNode node) {
        return visit(node.getLeft()) + "-" + visit(node.getRight());
    }

    @Override
    public String visit(MultiplicationNode node) {
        return visit(node.getLeft()) + "*" + visit(node.getRight());
    }

    @Override
    public String visit(DivisionNode node) {
        return visit(node.getLeft()) + "/" + visit(node.getRight());
    }

    @Override
    public String visit(ModNode node) {
        return visit(node.getLeft()) + "%" + visit(node.getRight());
    }

    @Override
    public String visit(NegateNode node) {
        return "!" + node.getInnerNode();
    }

    @Override
    public String visit(NumberNode node) {
        return Integer.toString(((int) node.getValue()));
    }

    @Override
    public String visit(StringNode node) {
        return node.getValue();
    }

    @Override
    public String visit(FloatNode node) {
        return node.getValue()+"f";
    }

    @Override
    public String visit(CharNode node) {
        return node.getValue();
    }
    @Override
    public String visit(DefineNode node) {
        StringBuilder var = new StringBuilder();
        //scopeCount is used to determine which scope is currently being visited
        if (scopeCount == 0) {
            var.append("static");
        } else {
            var.append(visit(node.getModi()));
        }

        var.append(" ");
        if (node.isArray()) {
            var.append("ArrayList<").append(visit(node.getType()))
                .append("> ")
                .append(node.getID().getText());
        } else {
            var.append(visit(node.getType()))
                .append(" ")
                .append(node.getID().getText());
        }
        if (node.getValue() != null) {
            var.append(" = ")
                .append(visit(node.getValue()));
        }
        var.append(";");

        if (!Objects.equals(currentClass, "")) {
            classFields.get(currentClass).add(new Pair<>(node.getType().getTypeName(),node.getID().getText()));
        }

        if (scopeCount == 0) {
            variables.add(var.toString());
            return "";
        } else {
            return var.toString();
        }
    }

    @Override
    public String visit(FunctionDNode node) {
        StringBuilder function = new StringBuilder();
        if (scopeCount == 0) {
            function.append("static ");
        }
        if (node.getIsAction()) {
            String parameters = visit(node.getParameter());
            String actionName = node.getFunction().getText();
            ArrayList<String> vars = new ArrayList<>();
            for (String param : parameters.split(",")) {
                vars.add(paramToVar(param));
            }
            // System.out.println(vars);
            ClassStringBuilder actionMenu = classes.get("ActionMenu");
            actionMenu.addToBlock("static String get" + actionName + "String(" + parameters + ") {" + "return " + visit(node.getExpr()) + ";}");
            String allowMeth = "static void allowAction(String action" + (parameters.isBlank() ? "" : ", ") + parameters + ") {";
            String disallowMeth = "static void disallowAction(String action" + (parameters.isBlank() ? "" : ", ") + parameters + ") {";
            if (!actionMenu.getBlock().toString().contains(allowMeth)) {
                actionMenu
                .addToBlock(allowMeth)
                .addToBlock("if (action.equals(\"" + actionName + "\")) {" +
                "allowedNames.add(get" + actionName + "String(" + String.join(", ", vars) + "));" +
                "indexes.add(action " + ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "")  + ");");
                scopeCount++;
                actionMenu.addToBlock("allowedActions.add(() -> {" + visit(node.getBlock()) +
                        "});}}");
                scopeCount--;
                actionMenu
                .addToBlock(disallowMeth)
                .addToBlock("int index = indexes.indexOf(action " + ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "")+ ");" +
                        "if (index >= 0) {" +
                        "allowedActions.remove(index);" +
                        "allowedNames.remove(index);" +
                        "indexes.remove(index);" +
                        "}}");
            } else {
                actionMenu
                    .getBlock()
                    .insert(actionMenu.getBlock().indexOf(allowMeth) + allowMeth.length(),
                    "if (action.equals(\"" + actionName + "\")) {" +
                        "allowedNames.add(get" + actionName + "String(" + String.join(", ", vars) + "));" +
                            "indexes.add(action " + ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "")  + ");");
                        scopeCount++;
                        actionMenu.addToBlock("allowedActions.add(() -> {" + visit(node.getBlock()) +
                                "});}}");
                        scopeCount--;
            }
        } else {
            function.append("public ")
                    .append(handleType(node.getReturnType().getTypeName()))
                    .append(" ")
                    .append(node.getFunction().getText())
                    .append("(")
                    .append(visit(node.getParameter()))
                    .append(") {");
            scopeCount++;
            function.append(visit(node.getBlock()))
                    .append("}");
            scopeCount--;
            if (Objects.equals(node.getFunction().getText(), "game")) {
                gameFunction = function.toString();
            } else if (Objects.equals(node.getFunction().getText(), "end")) {
                endFunction = function.toString();
            } else if (scopeCount == 0) {
                functions.add(function.toString());
            } else {
                return function.toString();
            }
        }
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        StringBuilder classD = new StringBuilder();
        if (scopeCount == 0){
            classD.append("static ");
        }
        classD.append("class ").append(visit(node.getName()));
        if (node.getSuperClass() != null) {
            classD.append(" extends ").append(visit(node.getSuperClass()));
        }
        classD.append(" {");
        scopeCount++;
        String tmp = currentClass;
        currentClass = visit(node.getName());
        classFields.put(currentClass, new ArrayList<>());
        classD.append(visit(node.getBlock()));
        System.out.println(classFields.get(currentClass));
        scopeCount--;
        StringBuilder instanceFields = new StringBuilder();
        String regex = "int|string|char|float";
        for (Pair<String,String> instanceField: classFields.get(currentClass)) {
            if (!instanceFields.isEmpty()) {
                instanceFields.append(" && ");
            }
            if (instanceField.a.matches(regex)){
                instanceFields.append("this.").append(instanceField.b).append("==((").append(visit(node.getName())).append(") other).").append(instanceField.b);
            } else {
                instanceFields.append("this.").append(instanceField.b).append(".equals(((").append(visit(node.getName())).append(") other).").append(instanceField.b).append(")");
            }
        }
        System.out.println(instanceFields);

        if (!classFields.get(currentClass).isEmpty()) {
            classD.append("public boolean equals(Object other) {" +
                    "if (other.getClass().equals(super.getClass())) {");

            classD.append("return (").append(instanceFields).append(");")
                    .append("}")
                    .append("return false;")
                    .append("}");
        } else {
            classD.append("public boolean equals(Object other) {" +
                    "if (other.getClass().equals(super.getClass())) {");

            classD.append("return true;" +
                    "}" +
                    "return false;" +
                    "}");
        }
        classD.append("}");
        currentClass = tmp;

        if (scopeCount == 0) {
            mainClasses.add(classD.toString());
        } else {
            return classD.toString();
        }

        return "";
    }

    @Override
    public String visit(ForNode node) {
        StringBuilder forString = new StringBuilder();
        forString.append("for (")
                .append(visit(node.getType()))
                .append(" ")
                .append(visit(node.getIterator()))
                .append(" : ")
                .append(visit(node.getArray()))
                .append(") {");
        scopeCount++;
        forString.append(visit(node.getBlock()))
                .append("}");
        scopeCount--;
        return forString.toString();
    }

    @Override
    public String visit(WhileNode node) {
        StringBuilder whileString = new StringBuilder();
        whileString.append("while (")
                .append(visit(node.getCondition()))
                .append(") {");
        scopeCount++;
        whileString.append(visit(node.getBlock()))
                .append("}");
        scopeCount--;
        return whileString.toString();
    }

    @Override
    public String visit(IfNode node) {
        StringBuilder ifString = new StringBuilder();

        ifString.append("if (")
                .append(visit(node.getCondition()))
                .append(") {");
        scopeCount++;
        ifString.append(visit(node.getBlock()))
                .append("}");
        scopeCount--;
        return ifString.toString();
    }

    @Override
    public String visit(ReturnNode node) {
        return "return " + visit(node.getInnerNode());
    }

    @Override
    public String visit(BreakNode node) {
        return "break";
    }

    @Override
    public String visit(ContinueNode node) {
        return "continue";
    }

    /**
     * When visiting a node of type ArrayNode, the method will create a new ArrayList and add the inner nodes to it.
     * This is done in order to convert from the custom array type to the way arrays are typed in Java
     * @param node ArrayNode to be translated
     * @return the newly generated array as a string
     */
    @Override
    public String visit(ArrayNode node) {
        StringBuilder temp = new StringBuilder();
        temp.append("new ArrayList<>() {{");
        String[] adds = visit(node.getInnerNode()).split(", ");
        for (String add : adds) {
            temp.append("add(").append(add).append(");");
        }
        temp.append("}}");
        return temp.toString();
    }

    @Override
    public String visit(ClassAccessNode node) {
        StringBuilder ClassAccessString = new StringBuilder();
        ClassAccessString.append(visit(node.getObject()));
        if (node.getValue() != null) {
            for (ValueNode currentField : node.getValue()) {
                ClassAccessString.append(".");
                ClassAccessString.append(visit(currentField));
            }
        }
        return ClassAccessString.toString();
    }

    @Override
    public String visit(ArrayAccessNode node) {
        return visit(node.getArray()) +
                ".get(" +
                visit(node.getIndex()) +
                ")";
    }

    @Override
    public String visit(FunctionCallNode node) {
        StringBuilder funcCall = new StringBuilder();
        if(node.getFunction().getText().equals("showGameState")) {
            funcCall.append("GameState.");
        } else if(node.getFunction().getText().matches("allowAction|disallowAction|disallowAllActions|displayAllowedActions")) {
            funcCall.append("ActionMenu.");
            funcCall.append(visit(node.getFunction()))
                    .append("(")
                    .append(callToParam(node.getParameter()))
                    .append(")");
            return funcCall.toString();
        }

        if (node.getHasNew()) {
            funcCall.append("new ");
        }
        funcCall.append(visit(node.getFunction()))
                .append("(")
                .append(visit(node.getParameter()))
                .append(")");

        if (visit(node.getFunction()).equals("generatePlayerList")) {
            playerAddedCalled++;
        }
        return funcCall.toString();
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getText();
    }

    @Override
    public String visit(AssignmentNode node) {
        return visit(node.getLeft()) + "=" + visit(node.getRight())+";";
    }

    @Override
    public String visit(NegativeNode node) {
        return "- " + node.getInnerNode();
    }

    @Override
    public String visit(FparamNode node) {
        return visit(node.getType()) +
                " " +
                visit(node.getIdentifier());
    }

    @Override
    public String visit(LessThanNode node) {
        return visit(node.getLeft()) + "<" + visit(node.getRight());
    }

    @Override
    public String visit(GreaterThanNode node) {
        return visit(node.getLeft()) + ">" + visit(node.getRight());
    }

    @Override
    public String visit(LTEQNode node) {
        return visit(node.getLeft()) + "<=" + visit(node.getRight());
    }

    @Override
    public String visit(GTEQNode node) {
        return visit(node.getLeft()) + ">=" + visit(node.getRight());
    }

    @Override
    public String visit(NOTEQNode node) {
        if ((node.getLeft() instanceof ArrayNode && node.getRight() instanceof ArrayNode) ||
                (node.getLeft() instanceof ClassAccessNode && node.getRight() instanceof ClassAccessNode)){
            return "!(" + visit(node.getLeft()) + ".equals(" + visit(node.getRight()) + "))";
        } else if(!(node.getLeft() instanceof ArrayNode) && node.getRight() instanceof ArrayNode ||
                (!(node.getLeft() instanceof ClassAccessNode) && node.getRight() instanceof ClassAccessNode)){
            return "!(" + visit(node.getRight()) + ".equals(" + visit(node.getLeft()) + "))";
        }
        return visit(node.getLeft()) + "!=" + visit(node.getRight());
    }

    @Override
    public String visit(ANDNode node) {
        return visit(node.getLeft()) + "&&" + visit(node.getRight());
    }

    @Override
    public String visit(ORNode node) {
        return visit(node.getLeft()) + "||" + visit(node.getRight());
    }

    @Override
    public String visit(EQEQNode node) {

        if (node.getLeft() instanceof ArrayNode && node.getRight() instanceof ArrayNode ||
                (node.getLeft() instanceof ClassAccessNode && node.getRight() instanceof ClassAccessNode)){
            return visit(node.getLeft()) + ".equals(" + visit(node.getRight()) + ")";
        } else if(!(node.getLeft() instanceof ArrayNode) && node.getRight() instanceof ArrayNode){
            return visit(node.getRight()) + ".equals(" + visit(node.getLeft()) + ")";
        }
        return visit(node.getLeft()) + "==" + visit(node.getRight());
    }

    @Override
    public String visit(ModifierNode node) {
        if (node.getModifier() != null) {
            return node.getModifier();
        } else {
            return "";
        }
    }

    @Override
    public String visit(TypeNode node) {
        String tmp = handleType(node.getTypeName());
         if (tmp.equals("string")){
            return "String";
         }else {
            return tmp;
        }
    }

    @Override
    public String visit(ExpressionsNode node) {
        StringBuilder expr = new StringBuilder();
        if (node != null) {
            expr.append(visit(node.getLeft()));
            if (node.getRight() != null) {
                expr.append(", ")
                        .append(visit(node.getRight()));
            }
            return expr.toString();
        }
        return "";
    }

    @Override
    public String visit(FparamsNode node) {
        StringBuilder fParamsString = new StringBuilder();
        if (node != null) {
            fParamsString.append(visit(node.getLeft()));
            if (node.getRight() != null) {
                fParamsString.append(", ")
                            .append(visit(node.getRight()));
            }
            return fParamsString.toString();
        }
        return "";
    }

    /**
     * The card type is used to specify a card in the game.
     * The syntax for defining a card is as follows:
     * cardType(ID = "skip"
     *     : onPlayed(Player player1) {
     *         "do something";
     *     })
     */
    @Override
    public String visit(CardTypeNode node) {
        scopeCount++;
        String className = "";
        ClassStringBuilder classText = null;
        if (node.getIdentifier() != null) {
            className = "Card" + node.getID();
            System.out.println(className);
            if (classes.get(className) != null) {
                throw new DuplicateDefinitionException(node.getLineNumber(), "Card");
            }
            classText = new ClassStringBuilder().addStart(className, "Card");
        }
        for (DefineNode field : node.getFields()) {
            String fieldText = visit(field);
            if (classes.get("Card").getBlock().indexOf(fieldText) == -1) {
                classes.get("Card").addToBlock(visit(field));
            }
        }
        for (FunctionDNode method : node.getMethods()) {
            String methName = "public "
                    + ((method.getModifier().getModifier() == null)  ? "" : (method.getModifier() + " "))
                    + handleType(method.getReturnType().getTypeName()) + " "
                    + method.getFunction().getText()
                    + "(" + visit(method.getParameter()) + ")";
            if (node.getIdentifier() != null) {
                if (!classes.get("Card").getBlock().toString().contains(methName)) {
                    FunctionDNode emptyMeth = new FunctionDNode();
                    emptyMeth.setModifier(method.getModifier());
                    emptyMeth.setReturnType(method.getReturnType());
                    emptyMeth.setFunction(method.getFunction());
                    emptyMeth.setParameter(method.getParameter());
                    emptyMeth.setBlock(new BlockNode());
                    classes.get("Card").addToBlock(visit(emptyMeth));
                }
                //Maybe add null check
                classText.addToBlock("@Override").addToBlock(visit(method));
            } else {
                if (method.getFunction().getText().equals("toString")) {
                    int index = classes.get("Card").getBlock().indexOf("public String toString() {return ID;}");
                    int endIndex = index + "public String toString() {return ID;}".length();
                    if(index >= 0) {
                        System.out.println(index);
                        System.out.println(endIndex);
                        classes.get("Card").getBlock().replace(index, endIndex, visit(method));
                    } else {
                        throw new DuplicateDefinitionException(node.getLineNumber(), method.getFunction().getText());
                    }
                } else if (!classes.get("Card").getBlock().toString().contains(methName)) {
                    classes.get("Card").addToBlock(visit(method));
                } else {
                    throw new DuplicateDefinitionException(node.getLineNumber(), method.getFunction().getText());
                }
            }
        }
        if(node.getIdentifier() != null) {
            classes.put(className, classText);
        }
        scopeCount--;

        return "";
    }

    /**
     * Constructor for all the built-in classes that are standard part of set up
     */
    public CodeBuilderVisitor() {
        variables.add("static ArrayList<Player> players = new ArrayList<>();");
        functions.add("static void print(String input) {System.out.print(input);}");
        functions.add("static int strlen(String input) {return input.length();}");
        functions.add("public static void generatePlayerList(ArrayList<String> args) {" +
                "for (int i = 0; i<args.size(); i++) {" +
                "Player tmp = new Player(args.get(i));" +
                "players.add(tmp);" +
                "if (i > 0) {" +
                "players.get(i-1).nextPlayer = players.get(i);" +
                "}}" +
                "players.get(args.size()-1).nextPlayer = players.get(0);" +
                "}");
        classes.put("Card", new ClassStringBuilder().addStart("Card").addToBlock("String ID;")
                .addToBlock("public String toString() {return ID;}" +
                    "@Override" +
                    "public Card clone() {" +
                    "try {" +
                    "return (Card) super.clone();" +
                    "} catch (CloneNotSupportedException e) {" +
                    "System.err.println(\"Clone of card not supported\");" +
                    "return null;" +
                    "}" +
                    "}"
                )
        );
        classes.put("Action", new ClassStringBuilder().addToBlock("static interface Action {abstract void act();"));
        classes.put("Location", new ClassStringBuilder()
                .addStart("Location")
                .addToBlock("String name;")
                .addToBlock("ArrayList<Card> cards = new ArrayList<>();")
                .addToBlock("int numberOfCards() {return cards.size();}"));
        classes.put("Deck", new ClassStringBuilder().addStart("Deck","Location")
                .addToBlock("public Deck() {" +
                        "GameState.decks.add(this);" +
                        "}"));
        classes.put("Hand", new ClassStringBuilder().addStart("Hand","Location"));
        classes.put("PlayArea", new ClassStringBuilder().addStart("PlayArea","Location")
                .addToBlock("@Override" +
                        "public String toString() {" +
                        "StringBuilder string = new StringBuilder();" +
                        "string.append(name).append(\"\\n\");" +
                        "for (Card card : cards) {" +
                        "string.append(\"- \").append(card).append(\"\\n\");" +
                        "}" +
                        "return string.toString();" +
                        "}" +
                        "public PlayArea() {" +
                        "GameState.playAreas.add(this);" +
                        "}")
                .addToBlock("public void move(int cardNum, Location moveToLocation) {" +
                        "moveToLocation.cards.add(0, super.cards.get(cardNum));" +
                        "super.cards.remove(cardNum);" +
                        "}"));
        classes.put("Player", new ClassStringBuilder().addStart("Player")
                .addToBlock("String name;")
                .addToBlock("Hand hand;")
                .addToBlock("Player nextPlayer;").addToBlock("public Player findNextPlayer(int count) { " +
                        "Player tmp = this.nextPlayer;" +
                        "count = (players.size()+count)%players.size();" +
                        "for (int i = 0; i < count; i++) {" +
                        "tmp = tmp.nextPlayer;" +
                        "}" +
                        "return tmp;}"
                ).addToBlock("Player(String name){this.name = name;this.hand = new Hand(this);}")
        );
        classes.get("Deck").addToBlock("int visible = 0;").addToBlock("public void draw(Location hand){" +
                "hand.cards.add(super.cards.get(0));" +
                "super.cards.remove(0);" +
                "}")
                .addToBlock("public Card getTop() {return super.cards.get(0);}")
                .addToBlock("public void drawMany(Location hand, int amountToDraw) {" +
                        "for (int i = 0; i < amountToDraw; i++){" +
                        "hand.cards.add(0, super.cards.get(0));" +
                        "super.cards.remove(0);" +
                        "}" +
                        "}")
                .addToBlock("public void shuffle(){" +
                        "Collections.shuffle(super.cards);" +
                        "}")
                .addToBlock("public void add(Card card, int number) {" +
                        "for (int i = 0; i < number; i++) {" +
                        "super.cards.add(0, card.clone());" +
                        "}}")
                .addToBlock("@Override" +
                        "public String toString() {" +
                        "StringBuilder string = new StringBuilder();" +
                        "string.append(name).append(\" - \")" +
                        ".append((visible == 1) ? getTop() : \"hidden (\")" +
                        ".append(cards.size())" +
                        ".append(\" \")" +
                        ".append((cards.size() == 1) ? \"card\" : \"cards\");" +
                        "return string.toString();" +
                        "}");
        classes.get("Hand").addToBlock("String name;")
                .addToBlock("int maxSize;")
                .addToBlock("Player owner;")
                .addToBlock("public void move(int cardNum, Location moveToLocation) {" +
                        "moveToLocation.cards.add(0, super.cards.get(cardNum));" +
                        "super.cards.remove(cardNum);" +
                        "}")
                .addToBlock("@Override" +
                        "public String toString() {" +
                        "StringBuilder string = new StringBuilder();" +
                        "string.append(\"This hand\\n\");" +
                        "for (Card card : cards) {" +
                        "string.append(\"- \").append(card).append(\"\\n\");" +
                        "}" +
                        "return string.toString();" +
                        "}")
                .addToBlock("public Hand(Player player) {GameState.hands.add(this);this.owner = player;}")
                .addToBlock("public Hand() {GameState.hands.add(this);}");
        classes.put("ActionMenu", new ClassStringBuilder().addStart("ActionMenu")
        .addToBlock(
                "private static ArrayList<String> indexes = new ArrayList<String>();" +
                "private static ArrayList<String> allowedNames = new ArrayList<String>();" +
                "private static ArrayList<Action> allowedActions = new ArrayList<Action>();")
        .addToBlock(
                "public static void displayAllowedActions() {" +
                "if (allowedNames.size() > 0){" +
                "for (int i = 0; i < allowedNames.size(); i++) {" +
                "System.out.println(i+1 + \" - \" + allowedNames.get(i));" +
                "}" +
                "int choice = choice(allowedNames.size());" +
                "allowedActions.get(choice-1).act();" +
                "}}")
        .addToBlock("public static void disallowAllActions() {" +
                "        allowedActions.clear();" +
                "        allowedNames.clear();" +
                "        indexes.clear();" +
                "    }")
        .addToBlock(
                "public static int choice(int choices) {" +
                "int choice = -1;" +
                "while (choice < 1 || choice > choices) {" +
                "Scanner sc = new Scanner(System.in);" +
                "while (!sc.hasNextInt()) {" +
                "sc.next();" +
                "System.out.print(\"Not a number\\n\");" +
                "}" +
                "choice = sc.nextInt();" +
                "if (choice > choices) {" +
                "System.out.print(\"Number too big, try again\\n\");" +
                "} else if (choice < 1) {" +
                "System.out.print(\"Number too small, try again\\n\");" +
                "}" +
                "}" +
                "return choice;" +
                "}"));
        classes.put("GameState", new ClassStringBuilder().addToBlock("static class GameState {" +
                "static final ArrayList<Deck> decks = new ArrayList<>();" +
                "static final ArrayList<Hand> hands = new ArrayList<>();" +
                "static final ArrayList<PlayArea> playAreas = new ArrayList<>();" +
                "public static void showGameState(Player player) {" +
                "for (Hand hand : hands) {" +
                "if (!hand.cards.isEmpty()) {" +
                "if (hand.owner.equals(player)) {" +
                "System.out.println(hand);" +
                "} else {" +
                "System.out.print(player.name);" +
                "System.out.print((player.name.endsWith(\"s\")) ? \"'\" : \"'s\");" +
                "System.out.println(\"hand - \" + player.hand.cards.size() + \" cards\");" +
                "}" +
                "}" +
                "}" +
                "for (PlayArea playArea : playAreas) {" +
                "if (playArea.cards.isEmpty()) {" +
                "System.out.println(playArea.toString());" +
                "}" +
                "}" +
                "for (Deck deck : decks) {" +
                "if (deck.cards.isEmpty()) {" +
                "System.out.println(deck.toString());" +
                "}" +
                "}" +
                "}"));
    }

}
