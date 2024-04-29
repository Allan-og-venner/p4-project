
import nodes.*;

import java.lang.reflect.Type;
import java.util.*;


public class CodeBuilderVisitor extends ASTVisitor<String>{

    private ArrayList<String> functions = new ArrayList<>();
    private ArrayList<String> actions = new ArrayList<>();
    private ArrayList<String> variables = new ArrayList<>();
    private Hashtable<String, ClassStringBuilder> classes = new Hashtable<String, ClassStringBuilder>();
    private Hashtable<String, ArrayList<String>> classFields = new Hashtable<>();
    private String gameFunction;
    private String endFunction;
    private int scopeCount;

    public String formatCode(String unformattedCode) {

        /*
        unformattedCode = unformattedCode.replaceAll("\n","");

        int indentation = 0;

        StringBuilder chars = new StringBuilder();
        for (char c : unformattedCode.toCharArray()) {
            chars.append(c);
            if (c == '{'){
                indentation++;
                chars.append('\n');
                for (int i = 0; i < indentation; i++) {
                    chars.append('\t');
                }
            }
            if (c == '}') {
                indentation--;
                chars.append('\n');
                for (int i = 0; i < indentation; i++) {
                    chars.append('\t');
                }
            }
            if (c == ';') {
                chars.append('\n');
                for (int i = 0; i < indentation; i++) {
                    chars.append('\t');
                }
            }
        }

        return chars.toString();
        */
        
        int indentation = 0;
        int inParen = 0;
        StringBuilder formattedCode = new StringBuilder();

        for (char c : unformattedCode.toCharArray()) {
            if(inParen > 0) {
                if(c == ')') {
                    inParen--;
                }
                if(c == '(') {
                    inParen++;
                }
                formattedCode.append(c);
            } else {
                switch (c) {
                    case '{':
                        formattedCode.append(c);
                        formattedCode.append('\n');
                        indentation++;
                        for (int i = 0; i < indentation; i++) {
                            formattedCode.append('\t');
                        }
                        break;
                    case '}':
                        indentation--;
                        for (int i = 0; i < indentation; i++) {
                            formattedCode.append('\t');
                        }
                        formattedCode.append(c);
                        formattedCode.append('\n');
                        break;
                    case ';':
                        formattedCode.append(c);
                        formattedCode.append('\n');
                        for (int i = 0; i < indentation; i++) {
                            formattedCode.append('\t');
                        }
                        break;
                    case '(':
                        formattedCode.append(c);
                        inParen++;
                        break;
                    default:
                        formattedCode.append(c);
                        break;
                }
            }
        }

        return formattedCode.toString().replaceAll("@Override", "@Override\n");

    }

    public String handleType(String nonRealType){
        String[] splitType;
        String newType;
        if (nonRealType.startsWith("array ")){
            splitType = nonRealType.split("^array ");
            nonRealType = splitType[1];
        }



         switch (nonRealType) {
            case "int" -> newType="Integer";
            case "float" -> newType="Float";
            case "string" -> newType="String";
            default -> newType=nonRealType;
        }
         return newType;
    }

    public String paramToVar(String param) {
        return param
                .replaceFirst("^.* ", "")
                .replaceAll("[\\[\\]]", "");
    }


    public String visitStart(BlockNode node) {

        StringBuilder prog = new StringBuilder();
        //Make card

        //Make action class

        //Make main
        prog.append("public class Main{");

        String setUp = visit(node);
        System.out.println(setUp);
        for (String var : variables){
            prog.append(var);
        }


        prog.append(gameFunction);
        prog.append(endFunction);

        for (String function : functions){
            prog.append(function);
        }


        prog.append("public static void main(String[] args){")
                .append(setUp)
                .append("while(true){").append("this.gameFunction()}")
                .append("this.endFunction();")
                .append("}")
                .append("}}");
        if (gameFunction == null){
            throw new AlreadyDefinedFunctionException("Game");
        } else if (endFunction == null) {
            throw new AlreadyDefinedFunctionException("End");
        }

        for (String i : classes.keySet()){
            prog.append(classes.get(i).close()).append("");
        }

        return prog.toString();

    }
    @Override
    public String visit(BlockNode node) {
        //visit Statement node
        String blocks = "";
        if(node.getStatement() != null) {
            blocks = visit(node.getStatement());
        }
        //if existing visit block node
        if (node.getBlocks() != null){
            blocks += visit(node.getBlocks());
        }
        return blocks;
    }

    @Override
    public String visit(AdditionNode node) {
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
        return Integer.toString(((int) node.getValue()));}

    @Override
    public String visit(StringNode node) {
        return node.getValue();
    }

    @Override
    public String visit(FloatNode node) {
        return Double.toString(node.getValue());
    }

    @Override
    public String visit(CharNode node) {
        return node.getValue();
    }
    @Override
    public String visit(DefineNode node) {
        StringBuilder var = new StringBuilder();
        if (scopeCount == 0) {
            var.append("static");
        } else {
            var.append(visit(node.getModi()));
        }
        var.append(" ");
        if (node.isArray()){
            var.append("Arraylist<").append(visit(node.getType()))
                .append("> ")
                .append(node.getID().getText());
                if (node.getValue() != null) {
                    var.append(" = ")
                        .append(visit(node.getValue()));
                }
                var.append(";");
        } else {
            var.append(visit(node.getType()))
                .append(" ")
                .append(node.getID().getText());
            if (node.getValue() != null) {
                var.append(" = ")
                    .append(visit(node.getValue()));
            }
            var.append(";");
        }
        if (scopeCount == 0){
            variables.add(var.toString());
            return "";
        } else {
            return var.toString();
        }
    }

    @Override
    public String visit(FunctionDNode node) {
        StringBuilder function = new StringBuilder();
        if (node.getIsAction()){
            String parameters = visit(node.getParameter());
            String actionName = node.getFunction().getText();
            ArrayList<String> vars = new ArrayList<>();
            for(String param : parameters.split(",")) {
                vars.add(paramToVar(param));
            }
            System.out.println(vars);
            ClassStringBuilder actionMenu = classes.get("ActionMenu");
            actionMenu.addToBlock("String get" + actionName + "String(" + parameters + ") {" + "return " + visit(node.getExpr()) + ";}");
            String allowMeth = "void allowAction(String action, " + parameters + ") {";
            String disallowMeth = "void disallowAction(String action, " + parameters + ") {";
            if (!actionMenu.getBlock().toString().contains(allowMeth)) {
                actionMenu
                        .addToBlock(allowMeth)
                        .addToBlock("if (action.equals(\"" + actionName + "\")) {" +
                        "   allowedNames.add(getPlayString(" + String.join(", ", vars) + "));" +
                        "   indeces.add(action + " + String.join(" + ", vars) + ");" +
                        "   allowedActions.add(() -> " + visit(node.getBlock()) +
                        "}");
                actionMenu.addToBlock(disallowMeth)
                        .addToBlock("int index = indeces.indexOf(action + " + String.join(" + ", vars)+ ");" +
                                "        if (index >= 0) {" +
                                "            allowedActions.remove(index);" +
                                "            allowedNames.remove(index);" +
                                "            indeces.remove(index);" +
                                "        }");
            } else {
                actionMenu
                    .getBlock()
                    .insert(actionMenu.getBlock().indexOf(allowMeth) + allowMeth.length(),
                    "if (action.equals(\"" + actionName + "\")) {" +
                        "   allowedNames.add(getPlayString(" + String.join(", ", vars) + "));" +
                        "   indeces.add(action + " + String.join(" + ", vars) + ");" +
                        "   allowedActions.add(() -> " + visit(node.getBlock()) + "" +
                        "}");
            }


        } else {
            function.append("public ")
                    .append(node.getReturnType().getTypeName())
                    .append(" ")
                    .append(node.getFunction().getText())
                    .append("(")
                    .append(visit(node.getParameter()))
                    .append(") {");
            scopeCount++;
            function.append(visit(node.getBlock()))
                    .append("}");
            scopeCount--;
        }
        if (Objects.equals(node.getFunction().getText(), "game")){
            gameFunction = function.toString();
        } else if (Objects.equals(node.getFunction().getText(), "end")){
            endFunction = function.toString();
        } else if (scopeCount == 0){
            functions.add(function.toString());
        } else {
            return function.toString();
        }
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        StringBuilder classD = new StringBuilder();
        classD.append("class ").append(visit(node.getName()));
        if (node.getSuperClass() != null){
            classD.append(" extends ").append(visit(node.getSuperClass()));
        }

        classD.append("{\n");
        scopeCount++;
        String tmp = currentClass;
        currentClass = visit(node.getName());
        classFields.put(currentClass, new ArrayList<>());
        classD.append(visit(node.getBlock()));
        System.out.println(classFields.get(currentClass));
        scopeCount--;
        StringBuilder instancefields = new StringBuilder();
        for (String instancefield: classFields.get(currentClass)){

            if(!instancefields.isEmpty()) {
                instancefields.append(" && ");
            }
            instancefields.append("this." + instancefield +".equals((("+ visit(node.getName()).toString() + ") other)." + instancefield + ")");
        }
        System.out.println(instancefields);

        if (!classFields.get(currentClass).isEmpty()) {
            classD.append("\n public boolean equals(Object other) {\n" +
                    "        if (other.getClass().equals(this.getClass())) {\n");

            classD.append("            return (" + instancefields + ");\n" +
                    "        }\n" +
                    "        return false;\n" +
                    "    }");
        } else {
            classD.append("\n public boolean equals(Object other) {\n" +
                    "        if (other.getClass().equals(this.getClass())) {\n");

            classD.append("            return true;\n" +
                    "        }\n" +
                    "        return false;\n" +
                    "    }");
        }

        currentClass = tmp;
        return classD.toString();
    }

    @Override
    public String visit(ForNode node) {
        StringBuilder forString = new StringBuilder();
        forString.append("for (")
                .append(visit(node.getType()))
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
        whileString.append("while(")
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

        ifString.append("if(")
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
    @Override
    public String visit(ArrayNode node) {
        StringBuilder temp = new StringBuilder();
        temp.append("new Arraylist<>(){{");
        String[] adds = visit(node.getInnerNode()).split(", ");
        for (String add : adds) {
            temp.append("add(").append(add).append(");");
        }
        temp.append("}}");


        return temp.toString();
    }


    /**
     * NOT IMPLEMENTED
     * 2024-04-23 14:30 - meret
     * @return  Class.cat2.cat3.Method(params?) Class.class2.cat3.cat4.methods()
     */
    @Override
    public String visit(ClassAccessNode node) {
        StringBuilder ClassAccessString = new StringBuilder();
        ClassAccessString.append(visit(node.getObject()));
        if (node.getValue() != null){
            for (ValueNode currentField : node.getValue()) {
                ClassAccessString.append(".");
                ClassAccessString.append(visit(currentField));
            }
        }
        return ClassAccessString.toString();
    }

    @Override
    public String visit(ArrayAccessNode node) {
        StringBuilder ArrayAccessString = new StringBuilder();
        ArrayAccessString.append(visit(node.getArray()))
                .append("[")
                .append(visit(node.getIndex()))
                .append("];");
        return ArrayAccessString.toString();
    }

    //JEG VED DET IKKE LIGE
    @Override
    public String visit(FunctionCallNode node) {
        StringBuilder funcCall = new StringBuilder();
        if(node.getHasNew()){
            funcCall.append("new ");
        } else {
            funcCall.append("Main.");
        }
        funcCall.append(visit(node.getFunction()))
                .append("(")
                .append(visit(node.getParameter()))
                .append(");");

        return funcCall.toString();
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getText();
    }

    @Override
    public String visit(AssignmentNode node) {
        return visit(node.getLeft()) + "=" + visit(node.getRight());
    }

    @Override
    public String visit(NegativeNode node) {
        return "- " + node.getInnerNode();
    }

    @Override
    public String visit(FparamNode node) {
        StringBuilder fParamString = new StringBuilder();
        fParamString.append(visit(node.getType()))
                    .append(" ")
                    .append(visit(node.getIdentifier()));
        return fParamString.toString();
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
        return visit(node.getLeft()) + "==" + visit(node.getRight());
    }

    @Override
    public String visit(ModifierNode node) {
        if (node.getModifier() != null){
            return node.getModifier();
        } else {
            return "";
        }
    }

    @Override
    public String visit(TypeNode node) {
        return handleType(node.getTypeName());
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

    @Override
    public String visit(CardTypeNode node) {
        scopeCount++;
        String className = "Card" + node.getID();
        System.out.println(className);
        if (classes.get(className) != null) {
            throw new DuplicateDefinitionException(node.getLineNumber(), "Card");
        }
        ClassStringBuilder classText = new ClassStringBuilder().addStart(className, "Card");

        for (DefineNode field : node.getFields()) {
            classText.addToBlock(visit(field));
        }
        for (FunctionDNode method : node.getMethods()) {
            String methName = "public "
                    + ((method.getModifier().getModifier() == null)  ? "" : (method.getModifier() + " "))
                    + method.getReturnType().getTypeName() + " "
                    + method.getFunction().getText()
                    + "(" + visit(method.getParameter()) + ")";
            if (!classes.get("Card").getBlock().toString().contains(methName)) {
                FunctionDNode emptyMeth = new FunctionDNode();
                emptyMeth.setModifier(method.getModifier());
                emptyMeth.setReturnType(method.getReturnType());
                emptyMeth.setFunction(method.getFunction());
                emptyMeth.setParameter(method.getParameter());
                emptyMeth.setBlock(new BlockNode());
                classes.get("Card").addToBlock(visit(emptyMeth));
            }
            classText.addToBlock("@Override" + visit(method));
        }

        classes.put(className, classText);
        scopeCount--;
        return "";
    }

    public CodeBuilderVisitor() {
        functions.add("void print(String input) {System.out.print(input);}");
        functions.add("int strlen(String input) {return input.length();}");
        classes.put("Card", new ClassStringBuilder().addStart("Card").addToBlock("String ID;"));
        classes.put("Action", new ClassStringBuilder().addToBlock("interface Action {abstract void act();"));

        classes.put("ActionMenu", new ClassStringBuilder().addStart("ActionMenu")
        .addToBlock(
                "private ArrayList<String> indeces = new ArrayList<String>();" +
                "private ArrayList<String> allowedNames = new ArrayList<String>();" +
                "private ArrayList<Action> allowedActions = new ArrayList<Action>();")
        .addToBlock(
                "public void displayAllowedActions() {" +
                "for (int i = 0; i < allowedNames.size(); i++) {" +
                "System.out.println(i+1 + \" - \" + allowedNames.get(i));" +
                "}" +
                "int choice = choice(allowedNames.size());" +
                "allowedActions.get(choice-1).act();" +
                "}")
        .addToBlock(
                "public int choice(int choices){" +
                "int choice = -1;" +
                "while (choice < 1 || choice > choices) {" +
                "Scanner sc = new Scanner(System.in);" +
                "while (!sc.hasNextInt()){" +
                "sc.next();" +
                "System.out.print(\"Not a number\\n\");" +
                "}" +
                "choice = sc.nextInt();" +
                "if (choice > choices){" +
                "System.out.print(\"Number too big, try again\\n\");" +
                "} else if (choice < 1){" +
                "System.out.print(\"Number too small, try again\\n\");" +
                "}" +
                "}" +
                "return choice;" +
                "}"));
    }

}
