
import nodes.*;
import org.antlr.v4.runtime.misc.Pair;
import java.util.*;

/**
 * CodeBuilderVisitor is a visitor class that visits the AST and builds Java code.
 * It has methods to visit different nodes in the AST.
 * It builds the code using a StringBuilder.
 * */
public class CodeBuilderVisitor extends ASTVisitor<String> {

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
        // Check if the input node is null and return an empty string in that case
        if (node == null) {
            return "";
        }

        // Check if the left side of the node contains a FunctionCallNode
        if (node.getLeft() != null && node.getLeft() instanceof FunctionCallNode) {
            // Cast the left side node to FunctionCallNode and retrieve the function name
            FunctionCallNode functionCall = (FunctionCallNode) node.getLeft();
            String functionName = functionCall.getFunction().getText();

            // Format the function name into a quoted string
            String formattedFunctionCall = "\"" + functionName + "\"";

            // Check if the function call node contains a parameter node
            if (functionCall.getParameter() != null) {
                // If a parameter node exists, format it as a comma-separated value
                formattedFunctionCall += ", " + visit(functionCall.getParameter());
            }

            // Return the formatted function call string with optional parameters
            return formattedFunctionCall;
        } else {
            // If the left side node isn't a FunctionCallNode, throw an exception indicating an incorrect action
            throw new RuntimeException(node.getLineNumber() + " - Action called wrong");
        }
    }

    public String boolToInt(String boolExp) {
        // If "false" is found, it returns 0; otherwise, returns -1
        return "-(\"false\".indexOf(\"\" + ("+ boolExp +")))";
    }

    public String intToBool(String intExp) {
        // If it is zero, returns the boolean "false". Otherwise, returns "true"
        return "((" + intExp + ") == 0 ? false : true )";
    }

    /**
     * handleType is a method that takes a nonRealType as input and returns a real type.
     * It is used to handle the different types for arrays because the syntax for int, float, string is different in
     * Java and needs to be converted.
     * @param nonRealType the type to be converted
     * @return newType the converted type
     */
    public String handleType(String nonRealType) {
        // Variable to hold the result type and an array for splitting when "array " prefix is present
        String[] splitType;
        String newType = nonRealType;

        // If the type starts with the "array " prefix, process it to determine the internal type
        if (nonRealType.startsWith("array ")) {
            // Remove the "array " prefix to get the actual internal type
            splitType = nonRealType.split("^array ");
            nonRealType = splitType[1];

            // Map the internal type to Java types
            switch (nonRealType) {
                case "int" -> newType = "Integer";   // Convert "int" to the "Integer" type
                case "float" -> newType = "Float";   // Convert "float" to the "Float" type
                case "string" -> newType = "String"; // Convert "string" to the "String" type
                default -> newType = nonRealType;    // Default to the original type if no match is found
            }
        // Even if outside array, string should be changed to String, as string does not exist in Java
        } else if (nonRealType.equals("string")) {
            return "String";
        }

        // Return the final converted or original type
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
        // Remove the type part by replacing the first occurrence of "type " with an empty string
        // `^.* ` matches any characters up to the last space
        return param.replaceFirst("^.* ", "")
                    // Remove all square brackets to handle arrays
                    // `[\\[\\]]` matches both opening '[' and closing ']' brackets
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

        // Add the necessary import statement and the main class definition
        prog.append("import java.util.*;")
            .append("public class Main {");

        // Generate the setup code by visiting the provided node
        String setUp = visit(node);

        // Append variable declarations
        for (String var : variables) {
            prog.append(var);
        }

        // Append the game and end function definitions
        prog.append(gameFunction);
        prog.append(endFunction);

        // Append any main class definitions
        for (String mainClass : mainClasses) {
            prog.append(mainClass);
        }

        // Close all classes by adding their respective closing curly braces
        for (String i : classes.keySet()) {
            prog.append(classes.get(i).close());
        }

        // Append any function definitions
        for (String function : functions) {
            prog.append(function);
        }

        // Construct the main method that initializes the setup and runs the game loop
        prog.append("public static void main(String[] args) {")
                .append(setUp)
                .append("while (true) { Main.game(); }")
                .append("}}");

        // If no game() or end() functions are defined, throw an exception
        if (gameFunction == null) {
            throw new MissingDefinitionException("Game");
        } else if (endFunction == null) {
            throw new MissingDefinitionException("End");
        }

        // If no players have been added, throw an exception
        if (playerAddedCalled == 0) {
            throw new MissingDefinitionException("Players");
        }

        // Return the final program source code as a string
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
        String blocks = "";

        // If the BlockNode contains a statement, visit it and add its generated code to the blocks string
        if (node.getStatement() != null) {
            blocks = visit(node.getStatement());
        }

        // Append a semicolon unless the statement is a type that requires no semicolon
        if (!(node.getStatement() instanceof CardTypeNode ||
              node.getStatement() instanceof FunctionDNode ||
              node.getStatement() instanceof ClassDNode ||
              node.getStatement() instanceof LoopNode)) {
            blocks += ";";
        }

        // If the BlockNode contains additional nested blocks, visit them and concatenate their generated code
        if (node.getBlocks() != null) {
            blocks += visit(node.getBlocks());
        }

        // Return the final generated string representing the code from the BlockNode
        return blocks;
    }

    @Override
    public String visit(AdditionNode node) {
        // Handles the addition of two nodes
        // If either the left or right node is a CharNode, return string concatenation
        if (node.getLeft() instanceof CharNode || node.getRight() instanceof CharNode) {
            return "\"\" +" + visit(node.getLeft()) + "+" + visit(node.getRight());
        }
        // Regular addition for non-character nodes
        return visit(node.getLeft()) + "+" + visit(node.getRight());
    }

    @Override
    public String visit(SubtractionNode node) {
        // Handles the subtraction of two nodes
        return visit(node.getLeft()) + "-" + visit(node.getRight());
    }

    @Override
    public String visit(MultiplicationNode node) {
        // Handles the multiplication of two nodes
        return visit(node.getLeft()) + "*" + visit(node.getRight());
    }

    @Override
    public String visit(DivisionNode node) {
        // Handles the division of two nodes
        return visit(node.getLeft()) + "/" + visit(node.getRight());
    }

    @Override
    public String visit(ModNode node) {
        // Handles the modulo operation of two nodes
        return visit(node.getLeft()) + "%" + visit(node.getRight());
    }

    @Override
    public String visit(NegateNode node) {
        // Negates the boolean value of the inner node
        return "!" + visit(node.getInnerNode());
    }

    @Override
    public String visit(NumberNode node) {
        // Converts the value of the node to a string representation of an integer
        return Integer.toString((int) node.getValue());
    }

    @Override
    public String visit(StringNode node) {
        // Returns the value of the string node
        return node.getValue();
    }

    @Override
    public String visit(FloatNode node) {
        // Returns the value of the float node with an 'f' to indicate a float literal
        return node.getValue() + "f";
    }

    @Override
    public String visit(CharNode node) {
        // Returns the value of the character node
        return node.getValue();
    }

    @Override
    public String visit(DefineNode node) {
        StringBuilder var = new StringBuilder();

        // Check the scope to determine how to handle the variable declaration
        // If `scopeCount` is zero, it is in the global or static scope
        if (scopeCount == 0) {
            StringBuilder ass = new StringBuilder();

            // Indicate that the variable is static because it is in the global scope
            var.append("static").append(" ");

            // Handle array declarations by using `ArrayList`
            if (node.isArray()) {
                var.append("ArrayList<")
                   .append(visit(node.getType())) // Visit to determine the array's type
                   .append("> ")
                   .append(node.getID().getText());
            } else {
                // Standard variable declaration
                var.append(visit(node.getType())) // Visit to determine the type
                   .append(" ")
                   .append(node.getID().getText());
            }

            // Prepare the assignment statement for the declared variable
            ass.append(node.getID().getText());

            // Store the variable declaration in the `variables` list
            variables.add(var.append(";").toString());

            // If the node has an initial value, add it to the assignment statement
            if (node.getValue() != null) {
                ass.append(" = ").append(visit(node.getValue()));
                // Return the assignment statement with a semicolon to terminate it
                return ass.append(";").toString();
            } else {
                // If no initial value is specified, return an empty string
                return "";
            }
        } else {
            // For other scopes, append the modifier ("static")
            var.append(visit(node.getModi()));
            // Add a space after the modifier
            var.append(" ");
        }

        // Handle array declarations or regular variables
        if (node.isArray()) {
            var.append("ArrayList<")
               .append(visit(node.getType()))
               .append("> ")
               .append(node.getID().getText());
        } else {
            var.append(visit(node.getType()))
               .append(" ")
               .append(node.getID().getText());
        }

        // If an initial value is provided, append it to the declaration
        if (node.getValue() != null) {
            var.append(" = ").append(visit(node.getValue()));
        } else {
            // Provide default values based on the variable's type if no initial value is provided
            String typeName = node.getType().getTypeName();
            if (typeName.matches("int|float")) {
                var.append(" = 0");
            } else if (typeName.equals("char")) {
                var.append(" = '\\0'");
            } else if (typeName.equals("string")) {
                var.append(" = \"\"");
            } else {
                var.append(" = null");
            }
        }

        // Append the terminating semicolon to complete the declaration
        var.append(";");

        // If the variable belongs to a class, add it to that class's field list
        if (!Objects.equals(currentClass, "")) {
            classFields.get(currentClass)
                       .add(new Pair<>(node.getType().getTypeName(), node.getID().getText()));
        }

        // Return the final declaration string
        return var.toString();
    }

    @Override
    public String visit(FunctionDNode node) {
        StringBuilder function = new StringBuilder();

        // Add the `static` modifier if it is a global function
        if (scopeCount == 0) {
            function.append("static ");
        }

        // Check if the node represents an action function
        if (node.getIsAction()) {
            // Process action parameters and extract their variable names
            String parameters = visit(node.getParameter());
            String actionName = node.getFunction().getText();
            ArrayList<String> vars = new ArrayList<>();
            for (String param : parameters.split(",")) {
                vars.add(paramToVar(param));
            }

            // Retrieve the `ActionMenu` class to add some action functions
            ClassStringBuilder actionMenu = classes.get("ActionMenu");

            // Add the action string getter function to the `ActionMenu`
            actionMenu.addToBlock(
                    "static String get" + actionName + "String(" + parameters + ") {" +
                    "return " + visit(node.getExpr()) + "; }"
            );

            // Prepare the method definitions for allowing and disallowing actions
            String allowMeth = "static void allowAction(String action" + (parameters.isBlank() ? "" : ", ") + parameters + ") {";
            String disallowMeth = "static void disallowAction(String action" + (parameters.isBlank() ? "" : ", ") + parameters + ") {";

            // Check if the allow method is already defined in the `ActionMenu`
            if (!actionMenu.getBlock().toString().contains(allowMeth)) {
                // Add the allow method to the `ActionMenu`
                actionMenu.addToBlock(allowMeth)
                          .addToBlock(
                                "if (action.equals(\"" + actionName + "\")) {" +
                                "allowedNames.add(get" + actionName + "String(" + String.join(", ", vars) + "));" +
                                "indexes.add(action " + ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "") + ");"
                          );

                // Increase scope count to enter the function's scope
                scopeCount++;

                // Add the action to the list of allowed actions with an anonymous function to execute it
                actionMenu.addToBlock("allowedActions.add(() -> {")
                          .addToBlock(visit(node.getBlock()))
                          .addToBlock("}); }}");

                // Exit the function's scope
                scopeCount--;

                // Add the disallow method to remove actions
                actionMenu.addToBlock(disallowMeth)
                          .addToBlock(
                                "int index = indexes.indexOf(action " +
                                ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "") + ");" +
                                "if (index >= 0) {" +
                                "allowedActions.remove(index);" +
                                "allowedNames.remove(index);" +
                                "indexes.remove(index);" +
                                "}}"
                          );
            } else {
                // If the allow method already exists, insert the new action logic into it
                actionMenu.getBlock().insert(
                        actionMenu.getBlock().indexOf(allowMeth) + allowMeth.length(),
                        "if (action.equals(\"" + actionName + "\")) {" +
                        "allowedNames.add(get" + actionName + "String(" + String.join(", ", vars) + "));" +
                        "indexes.add(action " + ((vars.isEmpty()) ? " + " + String.join(" + ", vars) : "") + ");"
                );

                // Increase the scope count for this function's scope
                scopeCount++;

                // Add the anonymous function for the action execution
                actionMenu.addToBlock("allowedActions.add(() -> {" +
                        visit(node.getBlock()) +
                        "}); }}");

                // Exit the function's scope
                scopeCount--;
            }
        } else {
            // Handle regular function definitions
            function.append("public ")
                    .append(handleType(node.getReturnType().getTypeName())) // Add return type
                    .append(" ")
                    .append(node.getFunction().getText()) // Add function name
                    .append("(")
                    .append(visit(node.getParameter())) // Add function parameters
                    .append(") {");

            // Enter the function's scope
            scopeCount++;
            function.append(visit(node.getBlock())); // Visit function body
            scopeCount--;

            // Handle special functions like "game()" and "end()"
            if (Objects.equals(node.getFunction().getText(), "game")) {
                gameFunction = function.append("}").toString();
            } else if (Objects.equals(node.getFunction().getText(), "end")) {
                endFunction = function.append("System.exit(0); }").toString();
            } else if (scopeCount == 0) {
                // If it is a global function, add it to the functions list
                functions.add(function.append("}").toString());
            } else {
                // Otherwise, return the function as a string
                return function.append("}").toString();
            }
        }

        // Return an empty string to indicate the end of this action
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        StringBuilder classD = new StringBuilder();

        // Add the `static` keyword if this is a global class
        if (scopeCount == 0) {
            classD.append("static ");
        }

        // Start the class definition with the class name
        classD.append("class ").append(visit(node.getName()));

        // If the class has a superclass, specify inheritance
        if (node.getSuperClass() != null) {
            classD.append(" extends ").append(visit(node.getSuperClass()));
        }

        // Open the class body
        classD.append(" {");

        // Enter the class scope by increasing the scope count
        scopeCount++;

        // Save the current class name and set the currentClass to the name of this class
        String tmp = currentClass;
        currentClass = visit(node.getName());

        // Initialize the field list for this class
        classFields.put(currentClass, new ArrayList<>());

        // Visit the block node to generate the class body content
        classD.append(visit(node.getBlock()));

        // Exit the class scope by decreasing the scope count
        scopeCount--;

        // Prepare the `equals` method to compare instances of this class
        StringBuilder instanceFields = new StringBuilder();
        String regex = "int|char|float";

        // Generate comparison logic for each instance field
        for (Pair<String, String> instanceField : classFields.get(currentClass)) {
            if (!instanceFields.isEmpty()) {
                instanceFields.append(" && ");
            }

            // If the field is a primitive type, compare with `==`
            if (instanceField.a.matches(regex)) {
                instanceFields.append("this.")
                        .append(instanceField.b)
                        .append(" == ((")
                        .append(visit(node.getName()))
                        .append(") other).")
                        .append(instanceField.b);
            } else {
                // Otherwise, use `.equals` for comparison
                instanceFields.append("this.")
                        .append(instanceField.b)
                        .append(".equals(((")
                        .append(visit(node.getName()))
                        .append(") other).")
                        .append(instanceField.b)
                        .append(")");
            }
        }

        // Add the `equals` method implementation to the class
        if (!classFields.get(currentClass).isEmpty()) {
            classD.append("public boolean equals(Object other) {")
                    .append("if (other.getClass().equals(super.getClass())) {")
                    .append("return (")
                    .append(instanceFields)
                    .append(");")
                    .append("}")
                    .append("return false;")
                    .append("}");
        } else {
            // If the class has no instance fields, just compare the class types
            classD.append("public boolean equals(Object other) {")
                    .append("if (other.getClass().equals(super.getClass())) {")
                    .append("return true;")
                    .append("}")
                    .append("return false;")
                    .append("}");
        }

        // Close the class definition
        classD.append("}");

        // Restore the previous current class name
        currentClass = tmp;

        // If it is a global class, add it to the list of main classes
        if (scopeCount == 0) {
            mainClasses.add(classD.toString());
        } else {
            // Otherwise, return the class definition directly
            return classD.toString();
        }

        // Return an empty string since the class is now in the mainClasses list
        return "";
    }

    @Override
    public String visit(ForNode node) {
        StringBuilder forString = new StringBuilder();

        // Begin the `for` statement with type and iterator declaration
        forString.append("for (")
                 .append(visit(node.getType()))      // Visit the type of the iterator variable
                 .append(" ")
                 .append(visit(node.getIterator()))  // Visit the iterator variable
                 .append(" : ")
                 .append(visit(node.getArray()))     // Visit the array being iterated
                 .append(") {");

        // Increase scope count to indicate entering a new block
        scopeCount++;

        // Visit the block of statements inside the loop
        forString.append(visit(node.getBlock()))
                 .append("}");

        // Decrease scope count after exiting the block
        scopeCount--;

        // Return the constructed `for` loop string
        return forString.toString();
    }

    @Override
    public String visit(WhileNode node) {
        StringBuilder whileString = new StringBuilder();

        // Begin the `while` statement with the condition
        whileString.append("while (")
                   .append(intToBool(visit(node.getCondition()))) // Convert integer condition to a boolean expression
                   .append(") {");

        // Increase scope count to indicate entering a new block
        scopeCount++;

        // Visit the block of statements inside the loop
        whileString.append(visit(node.getBlock()))
                   .append("}");

        // Decrease scope count after exiting the block
        scopeCount--;

        // Return the constructed `while` loop string
        return whileString.toString();
    }

    @Override
    public String visit(IfNode node) {
        StringBuilder ifString = new StringBuilder();

        // Begin the `if` statement with the condition
        ifString.append("if (")
                .append(intToBool(visit(node.getCondition()))) // Convert integer condition to a boolean expression
                .append(") {");

        // Increase scope count to indicate entering a new block
        scopeCount++;

        // Visit the block of statements inside the `if`
        ifString.append(visit(node.getBlock()))
                .append("}");

        // Decrease scope count after exiting the block
        scopeCount--;

        // Return the constructed `if` statement string
        return ifString.toString();
    }

    @Override
    public String visit(ReturnNode node) {
        // Generate and return the `return` statement with the expression from the inner node
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

        // Split the inner node's visited elements by commas and add each to the list
        String[] adds = visit(node.getInnerNode()).split(", ");
        for (String add : adds) {
            temp.append("add(").append(add).append(");");
        }

        // Close the ArrayList initializer
        temp.append("}}");

        // Return the constructed code string
        return temp.toString();
    }

    @Override
    public String visit(ClassAccessNode node) {
        StringBuilder ClassAccessString = new StringBuilder();

        // Start by appending the object itself
        ClassAccessString.append(visit(node.getObject()));

        // If there are properties to access, append them to the object reference
        if (node.getValue() != null) {
            for (ValueNode currentField : node.getValue()) {
                ClassAccessString.append(".")
                                 .append(visit(currentField));
            }
        }

        // Return the constructed class access code
        return ClassAccessString.toString();
    }

    @Override
    public String visit(ArrayAccessNode node) {
        // Construct the code string using `get` to access elements by index
        return visit(node.getArray()) +
               ".get(" +
               visit(node.getIndex()) +
               ")";
    }

    @Override
    public String visit(FunctionCallNode node) {
        StringBuilder funcCall = new StringBuilder();

        // If the function is `showGameState`, prepend the `GameState` class
        if (node.getFunction().getText().equals("showGameState")) {
            funcCall.append("GameState.");
        // Handle specific functions related to the action menu
        } else if (node.getFunction().getText().matches("allowAction|disallowAction|disallowAllActions|displayAllowedActions")) {
            funcCall.append("ActionMenu.");
            funcCall.append(visit(node.getFunction()))
                    .append("(")
                    .append(callToParam(node.getParameter()))  // Convert parameters to function arguments
                    .append(")");

            return funcCall.toString();
        }

        // If the function requires the `new` keyword, prepend it
        if (node.getHasNew()) {
            funcCall.append("new ");
        }

        // Append the function name and parameters
        funcCall.append(visit(node.getFunction()))
                .append("(")
                .append(visit(node.getParameter()))
                .append(")");

        // Increment player count if `generatePlayerList` is called
        if (visit(node.getFunction()).equals("generatePlayerList")) {
            playerAddedCalled++;
        }

        // Return the constructed function call code
        return funcCall.toString();
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getText();
    }

    @Override
    public String visit(AssignmentNode node) {
        // Concatenate the left-hand side, assignment operator, and right-hand side
        return visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
    }

    @Override
    public String visit(NegativeNode node) {
        // Concatenate a minus sign with the inner node's value
        return "-" + node.getInnerNode();
    }

    @Override
    public String visit(FparamNode node) {
        // Combine the parameter's type and identifier
        return visit(node.getType()) + " " + visit(node.getIdentifier());
    }

    @Override
    public String visit(LessThanNode node) {
        return boolToInt(visit(node.getLeft()) + " < " + visit(node.getRight()));
    }

    @Override
    public String visit(GreaterThanNode node) {
        return boolToInt(visit(node.getLeft()) + " > " + visit(node.getRight()));
    }

    @Override
    public String visit(LTEQNode node) {
        return boolToInt(visit(node.getLeft()) + " <= " + visit(node.getRight()));
    }

    @Override
    public String visit(GTEQNode node) {
        return boolToInt(visit(node.getLeft()) + " >= " + visit(node.getRight()));
    }

    @Override
    public String visit(NOTEQNode node) {
        // Determine whether to use `!=` or `!.equals()` based on the type
        if (node.getLeft().getType().getTypeName().matches("int|float|char")) {
            return boolToInt(visit(node.getLeft()) + " != " + visit(node.getRight()));
        }
        return boolToInt("!" + visit(node.getLeft()) + ".equals(" + visit(node.getRight()) + ")");
    }

    @Override
    public String visit(ANDNode node) {
        return boolToInt(intToBool(visit(node.getLeft())) + " && " + intToBool(visit(node.getRight())));
    }

    @Override
    public String visit(ORNode node) {
        return boolToInt(intToBool(visit(node.getLeft())) + " || " + intToBool(visit(node.getRight())));
    }

    @Override
    public String visit(EQEQNode node) {
        // Determine whether to use `==` or `.equals()` based on the type
        if (node.getLeft().getType().getTypeName().matches("int|float|char")) {
            return boolToInt(visit(node.getLeft()) + " == " + visit(node.getRight()));
        }
        return boolToInt(visit(node.getLeft()) + ".equals(" + visit(node.getRight()) + ")");
    }

    @Override
    public String visit(ModifierNode node) {
        if (node.getModifier() != null) {
            return node.getModifier();
        }
        return "";
    }

    @Override
    public String visit(TypeNode node) {
        // Convert the type name to the corresponding Java type
        String tmp = handleType(node.getTypeName());
        if (tmp.equals("string")) {
            return "String";
        }
        return tmp;
    }

    @Override
    public String visit(ExpressionsNode node) {
        StringBuilder expr = new StringBuilder();

        // If the node is not null, concatenate the left expression
        if (node != null) {
            expr.append(visit(node.getLeft()));

            // If the right expression is not null, add it, separated by a comma
            if (node.getRight() != null) {
                expr.append(", ").append(visit(node.getRight()));
            }
            return expr.toString();
        }
        return "";
    }

    @Override
    public String visit(FparamsNode node) {
        StringBuilder fParamsString = new StringBuilder();

        // If the node is not null, concatenate the left parameter
        if (node != null) {
            fParamsString.append(visit(node.getLeft()));

            // If the right parameter is not null, add it, separated by a comma
            if (node.getRight() != null) {
                fParamsString.append(", ").append(visit(node.getRight()));
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
        // Increment the scope count to track nested scopes
        scopeCount++;
        String className = "";
        ClassStringBuilder classText = null;

        // If the node has an identifier, define a specialized subclass
        if (node.getIdentifier() != null) {
            // Create a new subclass name prefixed with "Card"
            className = "Card" + node.getID();

            // Ensure that no duplicate class exists
            if (classes.get(className) != null) {
                throw new DuplicateDefinitionException(node.getLineNumber(), "Card");
            }

            // Initialize a ClassStringBuilder for the specialized subclass
            classText = new ClassStringBuilder().addStart(className, "Card");

            // Find the position of "return this;" in the base "Card" class
            int convertMethod = classes.get("Card").getBlock().indexOf("return this;");

            // Insert conversion logic to return a new instance of the specialized subclass
            classes.get("Card").getBlock().insert(
                convertMethod,
                "if (this.ID.equals(\"" + node.getID() + "\")) {" +
                "return new " + className + "(this); }"
            );

            // Add a constructor that initializes a new instance from a base "Card" object
            classText.addToBlock(className + "(Card card) { super(card); }");
        }

        // Process each field defined in the node
        for (DefineNode field : node.getFields()) {
            // Generate code for the field
            String fieldText = visit(field);

            // Add field to base "Card" class if it isn't already present
            if (classes.get("Card").getBlock().indexOf(fieldText) == -1) {
                classes.get("Card").addToBlock(fieldText);

                // Add field initialization to the "Card" class constructor
                int constructorIndex = classes.get("Card").getBlock().indexOf("this.ID = card.ID; }");
                classes.get("Card").getBlock().insert(
                    constructorIndex,
                    "this." + field.getID().getText() + " = card." + field.getID().getText() + ";"
                );
            }
        }

        // Process each method defined in the node
        for (FunctionDNode method : node.getMethods()) {
            // Generate parameter list and method signature
            String params = visit(method.getParameter());
            String methName = 
                ((method.getModifier().getModifier() == null) ? "" : (method.getModifier() + " ")) +
                handleType(method.getReturnType().getTypeName()) + " " +
                method.getFunction().getText() + "(" + params + ")";

            if (node.getIdentifier() != null) {
                // If the method isn't in the base "Card" class, add a conversion check
                if (!classes.get("Card").getBlock().toString().contains(methName)) {
                    String notEmptyMeth = visit(method.getModifier()) + " " +
                        method.getReturnType().getTypeName() + " " +
                        method.getFunction().getText() + "(" + params + ")" +
                        "{ Card a = convert();" +
                        "if (a.getClass() != Card.class) {" +
                        "a." + method.getFunction().getText() + "(" + paramToVar(params) + "); } else {}";
                    classes.get("Card").addToBlock(notEmptyMeth).addToBlock("}");
                }

                // Add the method implementation to the subclass
                classText.addToBlock("@Override").addToBlock(visit(method));
            } else {
                // Handle special cases like "toString" method in the base "Card" class
                if (method.getFunction().getText().equals("toString")) {
                    // Find the starting index of the "toString" method in the base "Card" class
                    int index = classes.get("Card").getBlock().indexOf("public String toString() { return ID; }");
                    // Calculate the ending index based on the length of the "toString" method string
                    int endIndex = index + "public String toString() { return ID; }".length();

                    // If the "toString" method implementation was found
                    if (index >= 0) {
                        // Replace the old "toString" method with the new one generated from the provided method node
                        classes.get("Card").getBlock().replace(index, endIndex, visit(method));
                    } else {
                        throw new DuplicateDefinitionException(node.getLineNumber(), method.getFunction().getText());
                    }
                } else if (!classes.get("Card").getBlock().toString().contains(methName)) {
                    // Add the method to the base "Card" class if not present
                    classes.get("Card").addToBlock(visit(method));
                } else {
                    throw new DuplicateDefinitionException(node.getLineNumber(), method.getFunction().getText());
                }
            }
        }

        // Register the specialized subclass if it has an identifier
        if (node.getIdentifier() != null) {
            classes.put(className, classText);
        }

        // Decrement the scope count once the node processing is complete
        scopeCount--;

        return "";
    }

    /**
     * Constructor for all the built-in classes that are standard part of set up
     */
    public CodeBuilderVisitor() {
        variables.add("static ArrayList<Player> players = new ArrayList<>();");
        functions.add("static void print(String input) { System.out.print(input); }");
        functions.add("static int strlen(String input) { return input.length(); }");
        functions.add("public static void generatePlayerList(ArrayList<String> args) {" +
                "for (int i = 0; i < args.size(); i++) {" +
                "Player tmp = new Player(args.get(i));" +
                "players.add(tmp);" +
                "if (i > 0) {" +
                "players.get(i-1).nextPlayer = players.get(i);" +
                "}}" +
                "players.get(args.size() - 1).nextPlayer = players.get(0);" +
                "}");
        classes.put("Card", new ClassStringBuilder().addStart("Card implements Cloneable").addToBlock("String ID;")
                .addToBlock("public String toString() { return ID; }" +
                    "@Override" +
                    "public Card clone() {" +
                    "try {" +
                    "return (Card) super.clone();" +
                    "} catch (CloneNotSupportedException e) {" +
                    "System.err.println(\"Clone of card not supported\");" +
                    "return null;" +
                    "}" +
                    "}"
                ).addToBlock("public Card convert() {").addToBlock("return this; }")
                .addToBlock("public Card(Card card) {").addToBlock("this.ID = card.ID; }")
                .addToBlock("public Card() {}")
        );
        classes.put("Action", new ClassStringBuilder().addToBlock("static interface Action { abstract void act();"));
        classes.put("Location", new ClassStringBuilder()
                .addStart("Location")
                .addToBlock("String name;")
                .addToBlock("ArrayList<Card> cards = new ArrayList<>();")
                .addToBlock("int numberOfCards() { return cards.size(); }"));
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
                .addToBlock("public boolean equals(Object other) { return this.name.equals(((PlayArea) other).name); }")
                .addToBlock("public void move(int cardNum, Location moveToLocation) {" +
                        "if (!moveToLocation.cards.isEmpty()) { moveToLocation.cards.add(0, super.cards.get(cardNum)); } else { moveToLocation.cards.add(super.cards.get(cardNum)); }" +
                        "super.cards.remove(cardNum);" +
                        "}"));
        classes.put("Player", new ClassStringBuilder().addStart("Player")
                .addToBlock("String name;")
                .addToBlock("Hand hand;")
                .addToBlock("Player nextPlayer;").addToBlock("public Player findNextPlayer(int count) {" +
                        "Player tmp = this.nextPlayer;" +
                        "count = (players.size() + count) % players.size();" +
                        "for (int i = 0; i < count; i++) {" +
                        "tmp = tmp.nextPlayer;" +
                        "}" +
                        "return tmp;" +
                        "}"
                ).addToBlock("public String toString() { return this.name; }")
                .addToBlock("public boolean equals(Object other) { return this.name.equals(((Player) other).name); }")
                .addToBlock("Player(String name) {" +
                        "this.name = name;" +
                        "this.hand = new Hand(this);" +
                        "}")
        );
        classes.get("Deck").addToBlock("int visible = 0;").addToBlock("public void draw(Location hand) {" +
                "hand.cards.add(super.cards.get(0));" +
                "super.cards.remove(0);" +
                "}")
                .addToBlock("public Card getTop() { return super.cards.get(0); }")
                .addToBlock("public void drawMany(Location hand, int amountToDraw) {" +
                        "for (int i = 0; i < amountToDraw; i++) {" +
                        "if (!hand.cards.isEmpty()) { hand.cards.add(0, super.cards.get(0)); } else { hand.cards.add(super.cards.get(0)); }" +
                        "super.cards.remove(0);" +
                        "}" +
                        "}")
                .addToBlock("public void shuffle() {" +
                        "Collections.shuffle(super.cards);" +
                        "}")
                .addToBlock("public void add(Card card, int number) {" +
                        "for (int i = 0; i < number; i++) {" +
                        "if (!super.cards.isEmpty()) { super.cards.add(0, card.clone()); } else { super.cards.add(card.clone()); }" +
                        "}}")
                .addToBlock("public boolean equals(Object other) { return this.name.equals(((Deck) other).name); }")
                .addToBlock("@Override" +
                        "public String toString() {" +
                        "StringBuilder string = new StringBuilder();" +
                        "string.append(name).append(\" - \")" +
                        ".append((visible == 1) ? getTop() + \" (\" : \"hidden (\")" +
                        ".append(cards.size())" +
                        ".append(\" \")" +
                        ".append((cards.size() == 1) ? \"card)\" : \"cards)\");" +
                        "return string.toString();" +
                        "}");
        classes.get("Hand").addToBlock("String name;")
                .addToBlock("int maxSize;")
                .addToBlock("Player owner;")
                .addToBlock("public void move(int cardNum, Location moveToLocation) {" +
                        "if (!moveToLocation.cards.isEmpty()) { moveToLocation.cards.add(0, super.cards.get(cardNum)); } else { moveToLocation.cards.add(super.cards.get(cardNum)); }" +
                        "super.cards.remove(cardNum);" +
                        "}")
                .addToBlock("@Override" +
                        "public String toString() {" +
                        "StringBuilder string = new StringBuilder();" +
                        "string.append(\"\\nThis hand\\n\");" +
                        "for (Card card : cards) {" +
                        "string.append(\"- \").append(card).append(\"\\n\");" +
                        "}" +
                        "return string.toString();" +
                        "}")
                .addToBlock("public Hand(Player player) { GameState.hands.add(this);this.owner = player; }")
                .addToBlock("public boolean equals(Object other) { return this.owner.equals(((Hand) other).owner); }")
                .addToBlock("public Hand() { GameState.hands.add(this); }");
        classes.put("ActionMenu", new ClassStringBuilder().addStart("ActionMenu")
        .addToBlock(
                "private static ArrayList<String> indexes = new ArrayList<String>();" +
                "private static ArrayList<String> allowedNames = new ArrayList<String>();" +
                "private static ArrayList<Action> allowedActions = new ArrayList<Action>();")
        .addToBlock(
                "public static void displayAllowedActions() {" +
                "if (allowedNames.size() > 0) {" +
                "for (int i = 0; i < allowedNames.size(); i++) {" +
                "System.out.println(i+1 + \" - \" + allowedNames.get(i));" +
                "}" +
                "int choice = choice(allowedNames.size());" +
                "allowedActions.get(choice-1).act();" +
                "}}")
        .addToBlock("public static void disallowAllActions() {" +
                "allowedActions.clear();" +
                "allowedNames.clear();" +
                "indexes.clear();" +
                "}")
        .addToBlock(
                "public static int choice(int choices) {" +
                "int choice = -1;" +
                "while (choice < 1 || choice > choices) {" +
                "Scanner sc = new Scanner(System.in);" +
                "while (!sc.hasNextInt()) {" +
                "sc.next();" +
                "System.err.print(\"Not a number\\n\");" +
                "}" +
                "choice = sc.nextInt();" +
                "if (choice > choices) {" +
                "System.err.print(\"Number too big, try again\\n\");" +
                "} else if (choice < 1) {" +
                "System.err.print(\"Number too small, try again\\n\");" +
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
                "String owner = hand.owner.name;" +
                "System.out.print(owner);" +
                "System.out.print((owner.endsWith(\"s\")) ? \"'\" : \"'s\");" +
                "System.out.println(\" hand - \" + hand.cards.size() + \" cards\");" +
                "}" +
                "}" +
                "}" +
                "for (PlayArea playArea : playAreas) {" +
                "if (!playArea.cards.isEmpty()) {" +
                "System.out.println(playArea.toString());" +
                "}" +
                "}" +
                "for (Deck deck : decks) {" +
                "if (!deck.cards.isEmpty()) {" +
                "System.out.println(deck.toString());" +
                "}" +
                "}" +
                "}"));
    }
}