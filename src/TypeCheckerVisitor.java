import nodes.*;
import org.antlr.v4.codegen.model.Loop;
import gen.*;
import org.antlr.v4.runtime.misc.Pair;
import java.util.*;



public class TypeCheckerVisitor extends ASTVisitor<String> {

    private SymbolTable symbolTable = new SymbolTable();
    private Stack<SymbolTable> symbolTables = new Stack<>();

    /**
     * Addition, subtraction, multiplication and division accept both number types - float and int
     * If there is at least one float, all of them return float. Else, int.
     * @return "float" or "int"
     */

    // Addition accepts both number types
    // If there is at least one float, all of them return float. Else, int
    @Override
    public String visit(AdditionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If either of the operands is of type "string" or "char", treat the result as a "string" type
        if (type1.matches("string|char") || type2.matches("string|char")) {
            node.setType(new TypeNode("string")); // Set the type of the node to "string"
            return "string"; // Return the resulting type

        // If both operands are numerical (either "int" or "float")
        } else if (type1.matches("int|float") && type2.matches("int|float")) {
            // If at least one operand is a "float", the result will be "float"
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float")); // Set the type to "float"
                return "float"; // Return the resulting type
            }
            // Otherwise, the result will be "int"
            node.setType(new TypeNode("int")); // Set the type to "int"
            return "int"; // Return the resulting type
        }

        // If neither of the above conditions are met, it means that the operands are not compatible
        // Throw an exception indicating the error and expected types
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    // Subtraction accepts both number types
    // If there is at least one float, all of them return float. Else, int
    @Override
    public String visit(SubtractionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are numerical types ("int" or "float"), proceed with determining the result type
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If at least one operand is of type "float", the result will be "float"
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float")); // Set the type of the node to "float"
                return "float"; // Return the resulting type
            }
            // Otherwise, the result will be "int"
            node.setType(new TypeNode("int")); // Set the type of the node to "int"
            return "int"; // Return the resulting type
        }
        // If the operands do not match the expected types ("int" or "float")
        // Throw an exception indicating the error and expected types
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    // Multiplication accepts both number types
    // If there is at least one float, all of them return float. Else, int
    @Override
    public String visit(MultiplicationNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are numeric ("int" or "float"), proceed with determining the result type
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If at least one operand is a "float", the result will be "float"
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float")); // Set the type of the node to "float"
                return "float"; // Return the resulting type
            }
            // Otherwise, if both operands are "int", the result will be "int"
            node.setType(new TypeNode("int")); // Set the type of the node to "int"
            return "int"; // Return the resulting type
        }

        // If the operands do not match the expected types ("int" or "float")
        // Throw an exception indicating the error and expected types
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    // Division accepts both number types
    // If there is at least one float, all of them return float. Else, int
    @Override
    public String visit(DivisionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are numeric ("int" or "float"), proceed with determining the result type
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If at least one operand is "float", the result will always be "float"
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float")); // Set the node type to "float"
                return "float"; // Return the resulting type
            }
            // Otherwise, if both operands are integers, the result will also be an integer
            node.setType(new TypeNode("int")); // Set the node type to "int"
            return "int"; // Return the resulting type
        }

        // If the operands are not numeric, throw an exception indicating the error and expected types
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    /**
     * Checks that the bool to negate is an int. Throws an exception if not.
     * @return "int"
     */

    // Takes only an int (bool) as input, and returns another one if true
    @Override
    public String visit(NegateNode node) {
        String innerType = visit(node.getInnerNode());

        // If the inner node is of type "int", it is considered a valid boolean value
        if (innerType.equals("int")) {
            // Set the type of the negation node to "int" (the same as the input type)
            node.setType(new TypeNode(innerType));
            // Return the resulting type, which remains "int"
            return innerType;
        }

        // If the inner node type is not "int", throw an exception indicating an incorrect input type
        throw new WrongTypeException(node.getLineNumber(), "int", innerType);
    }

    /**
     * Checks that both operands of modNode is type int - returns int if true
     * @return "int"
     */

    // Takes 2 ints as input, and returns an int if true
    @Override
    public String visit(ModNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are of type "int", proceed with modulus operation
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int")); // Set the node's type to "int"
            return "int"; // Return the resulting type as "int"
        }

        // If the operands are not both integers, throw an exception indicating a type mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    /**
     * Checks that the input node is either an int or float - returns the same
     * @return "int" or "float"
     */

    // Takes int or float, and returns the same
    @Override
    public String visit(NegativeNode node) {
        String innerType = visit(node.getInnerNode());

        // If the inner node type is "int" or "float", it's valid for the negation operation
        if (innerType.matches("int|float")) {
            node.setType(new TypeNode(innerType)); // Set the node's type to the same as the input type
            return innerType; // Return the resulting type (same as input)
        }

        // If the input type is not "int" or "float", throw an exception indicating a type mismatch
        throw new WrongTypeException(node.getLineNumber(), "int or float", innerType);
    }

    /**
     * Checks for the types of statements in the block
     * Checks for types of statements in nested blocks.
     * if statement in block is returnNode - sets type of block to be the return type
     * if statement in block is controlNode or LoopNode - ?? ~Merete
     * @return ???? ~Merete
     */

    @Override
    public String visit(BlockNode node) {
        // Visit the primary statement within the block to determine its data type
        String stmType = visit(node.getStatement());
        // Initialize the block type as "void", assuming there are no nested blocks
        String blockType = "void";

        // If there are nested blocks, visit them to determine the overall block type
        if (node.getBlocks() != null) {
            blockType = visit(node.getBlocks());
        }

        // If the primary statement is a return statement, the block type is the same as the return type
        if (node.getStatement() instanceof ReturnNode) {
            node.setType(new TypeNode(stmType)); // Set the node type to the statement type
            return stmType; // Return the statement type as the block type
        }

        // If the primary statement is a control or loop statement
        if (node.getStatement() instanceof ControlNode || node.getStatement() instanceof LoopNode) {
            if (!stmType.equals("void") && !stmType.equals(blockType)) {
                // Throw an exception if the types do not match
                throw new WrongTypeException(node.getStatement().getLineNumber(), "Matching types", stmType + ", " + blockType);
            }
        }

        // If none of the above conditions apply, set the block type to the nested block's type
        node.setType(new TypeNode(blockType));
        return blockType; // Return the block type
    }

    /**
     * Input is either int or float - returns the same
     * @return "int" or "float"
     */

    // Always an int
    @Override
    public String visit(NumberNode node) {
        // Set the node's type to "int", assuming all NumberNode instances represent integers
        node.setType(new TypeNode("int"));
        // Return the string "int" to denote the type of this node
        return "int";
    }

    // Always a float
    @Override
    public String visit(FloatNode node) {
        // Set the node's type to "float", assuming all FloatNode instances represent floats
        node.setType(new TypeNode("float"));
        // Return the string "float" to denote the type of this node
        return "float";
    }

    /**
     * Checks if both values on either side of the relational operator is an int - returns int if true.
     * @return "int"
     */

    // The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ORNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are integers, proceed with the logical OR operation
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int")); // Set the node type to "int"
            return "int"; // Return "int" as the resulting type
        }

        // If the operands are not both integers, throw an exception indicating a type mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    /**
     * Checks if both values on either side of the relational operator is an int - returns int if true.
     * @return "int"
     */

    // The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ANDNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands are integers, proceed with the logical OR operation
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int")); // Set the node type to "int"
            return "int"; // Return "int" as the resulting type
        }

        // If the operands are not both integers, throw an exception indicating a type mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    /**
     * Checks if both values on either side of the relational operator is an int - returns int if true.
     * Also checks if one of the types inherits the other - returns int if true.
     * @return "int"
     */
    @Override
    public String visit(EQEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // If both operands have the exact same type, proceed with the comparison
        if (type1.equals(type2)) {
            node.setType(new TypeNode("int")); // Set the node type to "int" to represent a boolean-like result
            return "int"; // Return "int" to denote a true or false result
        }

        // Check for type compatibility through inheritance relationships
        if (symbolTable.checkInherits(type1, type2) || symbolTable.checkInherits(type2, type1)) {
            node.setType(new TypeNode("int")); // Set the node type to "int"
            return "int"; // Return "int" to denote a true or false result
        }

        // If the types are incompatible for comparison, throw an exception indicating the error
        throw new WrongTypeException(node.getLineNumber(), "compatible types", type1 + " and " + type2);
    }

    /**
     * Accepts both number types - float and int
     * If there is at least one float - return float. Else, int.
     * @return "float" or "int"
     */

    // The relational expressions accept all numbers, and if true, return an int
    @Override
    public String visit(GreaterThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // Ensure that both operands are either "int" or "float", which are valid numeric types
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If either operand is a "float", return "float" as the node's type
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }

            // Otherwise, return "int" as the node's type for integer comparisons
            node.setType(new TypeNode("int"));
            return "int";
        }

        // If the operands are not valid numeric types, throw an exception indicating the mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + " and " + type2);
    }

    @Override
    public String visit(GTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // Ensure that both operands are either "int" or "float", which are valid numeric types
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If either operand is a "float", return "float" as the node's type
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }

            // Otherwise, return "int" as the node's type for integer comparisons
            node.setType(new TypeNode("int"));
            return "int";
        }

        // If the operands are not valid numeric types, throw an exception indicating the mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    @Override
    public String visit(LessThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // Ensure that both operands are either "int" or "float", which are valid numeric types
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If either operand is a "float", return "float" as the node's type
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }

            // Otherwise, return "int" as the node's type for integer comparisons
            node.setType(new TypeNode("int"));
            return "int";
        }

        // If the operands are not valid numeric types, throw an exception indicating the mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    @Override
    public String visit(LTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // Ensure that both operands are either "int" or "float", which are valid numeric types
        if (type1.matches("int|float") && type2.matches("int|float")) {
            // If either operand is a "float", return "float" as the node's type
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }

            // Otherwise, return "int" as the node's type for integer comparisons
            node.setType(new TypeNode("int"));
            return "int";
        }

        // If the operands are not valid numeric types, throw an exception indicating the mismatch
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    /**
     * Checks if one of the types inherits the other, or are the same - returns int if true.
     * @return "int"
     */
    @Override
    public String visit(NOTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        // Check for compatibility between the two types via inheritance relationships
        if (symbolTable.checkInherits(type1, type2) || symbolTable.checkInherits(type2, type1)) {
            // Set the node type to "int"
            node.setType(new TypeNode("int"));
            return "int"; // Return "int" as the comparison result
        }

        // If the types aren't compatible, throw an exception indicating the type mismatch
        throw new WrongTypeException(node.getLineNumber(), "compatible types", type1 + " and " + type2);
    }

    @Override
    public String visit(TypeNode node) {
        // No specific type returned for a TypeNode itself
        return null;
    }

    @Override
    public String visit(CharNode node) {
        // Return "char" to denote that this node represents a character value
        return "char";
    }

    @Override
    public String visit(StringNode node) {
        // Return "string" to denote that this node represents a string value
        return "string";
    }

    @Override
    public String visit(BreakNode node) {
        // Return "void" to denote that no value is associated with the break statement
        return "void";
    }

    @Override
    public String visit(ContinueNode node) {
        // Return "void" to denote that no value is associated with the continue statement
        return "void";
    }

    @Override
    public String visit(ReturnNode node) {
        // Visit the inner node to determine the type of the returned value
        return visit(node.getInnerNode());
    }

    /**
     * Enters new scope by pushing the current symbolTable.
     * Checks if the iterator is a valid array, and adds the array identifier and type to ST.
     * Finds the type of the block/scope, pops the newly ST to exit the scope and returns the block type.
     * @return the type of the scope block
     */
    @Override
    public String visit(ForNode node) {
        try {
            // Push a new cloned symbol table to handle loop scope
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            // Log an error if cloning the symbol table fails
            System.err.println(e.getMessage());
        }

        // Determine the data type of the array being iterated over
        String arrayType = visit(node.getArray());

        // Check if the array type starts with "array " and is not a recognized class
        if (arrayType.startsWith("array ") && !symbolTable.checkClass(arrayType)) {
            // Extract the actual data type of the elements within the array
            String type = arrayType.replaceFirst("^array ", "");

            // Add the iterator variable to the current symbol table with the element type
            symbolTables.peek().addValue(node.getIterator().getText(), type);

            // Visit the loop's block to handle the statements within it
            String returnType = visit(node.getBlock());

            // Pop the loop's symbol table off the stack to return to the previous scope
            symbolTables.pop();

            // Set the type of the `ForNode` to the type of the array elements
            node.setType(new TypeNode(type));

            // Return the type of the block
            return returnType;
        }

        // If the node doesn't represent a valid array, throw an exception indicating the type error
        throw new WrongTypeException(node.getLineNumber(), "array", arrayType);
    }

    /**
     * Enters new scope by pushing the current symbolTable.
     * Checks that the condition in the if-statement is type int.
     * Finds the type of the block/scope, pops the newly ST to exit the scope and returns the block type.
     * @return the type of the scope block
     */
    @Override
    public String visit(IfNode node) {
        try {
            // Push a new cloned symbol table to manage the scope of the "if" block
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            // Log an error if cloning the symbol table fails
            System.err.println(e.getMessage());
        }

        // Visit the condition to determine its type
        String conditionalType = visit(node.getCondition());

        // Ensure the conditional expression is of type "int"
        if (conditionalType.matches("int")) {
            // Visit the statements within the "if" block and get their return type
            String returnType = visit(node.getBlock());

            // Pop the symbol table off the stack to restore the previous scope
            symbolTables.pop();

            // Set the node's type to the type of the block
            node.setType(new TypeNode(returnType));

            // Return the type of the block
            return returnType;
        }

        // If the condition isn't of type "int", throw an exception indicating the expected type
        throw new WrongTypeException(node.getLineNumber(), "int", conditionalType);
    }

    /**
     * Enters new scope by pushing the current symbolTable.
     * Checks that the condition in the while-statement is type int.
     * Finds the type of the block/scope, pops the newly ST to exit the scope and returns the block type.
     * @return the type of the scope block
     */
    @Override
    public String visit(WhileNode node) {
        try {
            // Clone and push a new symbol table to manage the scope of the "while" loop
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            // Log an error message if cloning the symbol table fails
            System.err.println(e.getMessage());
        }

        // Visit the condition to determine its type
        String conditionalType = visit(node.getCondition());

        // Ensure that the condition is of type "int"
        if (conditionalType.matches("int")) {
            // Visit the statements within the "while" block and get their return type
            String returnType = visit(node.getBlock());

            // Pop the symbol table off the stack to restore the previous scope
            symbolTables.pop();

            // Set the node's type to the type of the block
            node.setType(new TypeNode(returnType));

            // Return the type of the block
            return returnType;
        }

        // If the condition isn't of type "int", throw an exception indicating the expected type
        throw new WrongTypeException(node.getLineNumber(), "int", conditionalType);
    }

    /**
     * Gets the type of the array, and checks if it starts with "array " to ensure that it is an array.
     * Removes the "array " prefix and returns the type.
     * @return type of array
     */
    @Override
    public String visit(ArrayAccessNode node) {
        // Visit the array node to get its type
        String arrayType = visit(node.getArray());

        // Check if the retrieved type starts with "array ", indicating it's an array type
        if (arrayType.startsWith("array ")) {
            // Extract the type of the elements within the array
            String type = arrayType.replaceFirst("^array ", "");

            // Set the type of this node to the extracted element type
            node.setType(new TypeNode(type));

            // Return the element type
            return type;
        }

        // If the accessed node isn't an array, throw an exception indicating the expected and actual types
        throw new WrongTypeException(node.getLineNumber(), "array", arrayType);
    }

    @Override
    public String visit(ArrayNode node) {
        // Visit the inner node to determine the base type of the array's elements
        String type = visit(node.getInnerNode());

        // Create the full array type by prefixing "array " to the base type
        String arrayType = "array " + type;

        // Set the type of this node to the full array type
        node.setType(new TypeNode(arrayType));

        // Return the array type as a string
        return arrayType;
    }

    /**
     * Retrives the identifier and type of function parameter.
     * Checks if the identifier is already present in the current scope,
     * otherwise it adds the identifier and type to the scope and returns the type
     * @return type of parameter
     */
    @Override
    public String visit(FparamNode node) {
        // Retrieve the identifier (name) and type of the parameter
        String identifier = node.getIdentifier().getText();
        String type = node.getType().getTypeName();

        // Check if the identifier already exists in the current symbol table
        if (symbolTables.peek().checkInnerV(identifier)) {
            // If it already exists, throw an exception indicating a duplicate definition
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }

        // Add the parameter to the current symbol table with its type
        symbolTables.peek().addValue(identifier, type);

        // Set the type of this node to the parameter's type
        node.setType(new TypeNode(type));

        // Return the parameter's type
        return type;
    }

    /**
     * If there are more parameters, checks both the left and right side,
     * otherwise just the left
     * @return left node, or left node and right node
     */
    @Override
    public String visit(FparamsNode node) {
        // If there are additional parameters, recursively visit both the left and right nodes
        if (node.getRight() != null) {
            // Concatenate the types from both the left and right parameters with a comma
            return visit(node.getLeft()) + "," + visit(node.getRight());
        }

        // If there is only a single parameter (left), visit and return its type
        return visit(node.getLeft());
    }

    @Override
    public String visit(CardTypeNode node) {
        // Initialize lists to keep track of processed fields and methods
        ArrayList<String> methods = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<>();

        // Retrieve the symbol table representing the "Card" type from the global symbol table stack
        Hashtable<String, SymbolTable> cTable = symbolTables.peek().getCTable();
        SymbolTable cardTable = cTable.get("Card");

        // Iterate through each field defined in the CardTypeNode
        for (DefineNode field : node.getFields()) {
            // Extract the field's type and name
            String type = field.getType().getTypeName();
            String name = field.getID().getText();

            // Check if the field's type exists in the current symbol table
            if (!symbolTables.peek().checkType(type)) {
                throw new TypeNotFoundException(type); // Type not found in the symbol table
            }

            // Look up the existing field type in the Card symbol table
            String existingType = cardTable.vLookup(name);
            if (existingType != null && !existingType.equals(type)) {
                throw new WrongTypeException(node.getLineNumber(), existingType, type); // Mismatched types
            }

            // Check if the field name has already been processed in the current list
            if (fields.contains(name)) {
                throw new DuplicateDefinitionException(node.getLineNumber(), name); // Duplicate field definition
            }

            // Add the field to the symbol table and mark it as processed
            cardTable.addValue(name, type);
            fields.add(name);
        }

        // Iterate through each method defined in the CardTypeNode
        for (FunctionDNode method : node.getMethods()) {
            // Get the method's name
            String methodName = method.getFunction().getText();
            try {
                // Create a copy of the current symbol table and push it onto the stack
                SymbolTable currentSymbols = (SymbolTable) symbolTables.peek().clone();
                symbolTables.push((SymbolTable) cardTable.clone());

                // Copy the various symbol tables (classes, functions, variables, and types)
                symbolTables.peek().getCTable().putAll(currentSymbols.getCTable());
                symbolTables.peek().getFTable().putAll(currentSymbols.getFTable());
                symbolTables.peek().getVTable().putAll(currentSymbols.getVTable());
                symbolTables.peek().getTypes().putAll(currentSymbols.getTypes());
                // Process the method parameters and block to determine their types
                String parameterTypes = "";
                if (method.getParameter() != null) {
                    parameterTypes = visit(method.getParameter());
                }
                String returnedType = visit(method.getBlock());
                symbolTables.pop();

                // Extract the declared return type of the method
                String returnType = method.getReturnType().getTypeName();
                String types = returnType + "," + parameterTypes;

                // Check if the method already exists in the Card symbol table
                String expectedTypes = cardTable.fLookup(methodName);
                if (expectedTypes != null) {
                    // Verify that the method signature matches the expected signature
                    if (!types.equals(method.getReturnType().getTypeName() + "," + parameterTypes)) {
                        throw new WrongTypeException(node.getLineNumber(), expectedTypes, types);
                    }
                } else {
                    // Check inheritance and ensure the return type is compatible
                    if (symbolTables.peek().checkInherits(returnedType, returnType)) {
                        if (methods.contains(methodName)) {
                            throw new DuplicateDefinitionException(node.getLineNumber(), methodName);
                        }

                        // Add the method to the Card symbol table and mark it as processed
                        cardTable.addFunction(methodName, types);
                        methods.add(methodName);
                    } else {
                        // Mismatched return type
                        throw new WrongTypeException(node.getLineNumber(), returnType, returnedType);
                    }
                }
            } catch (CloneNotSupportedException e) {
                // Print error message if symbol table cloning fails
                System.err.println(e.getMessage());
            }
        }

        // Return "void" indicating the CardTypeNode visit process is complete
        return "void";
    }

    /**
     * Handles class instantiation and normal function calls.
     * If the call has the "new" keyword, the class identifier is returned.
     * Else, the function identifier is checked to see if it exists in the fTable.
     * Visits the function parameters if there are any,
     * returns the return type of the function.
     * @return return type of function call
     */
    @Override
    public String visit(FunctionCallNode node) {
        // Retrieve the function name or class identifier
        String identifier = node.getFunction().getText();

        // If the node represents object instantiation (using 'new')
        if (node.getHasNew()) {
            // Check if the identifier corresponds to a known class type
            if (!symbolTable.checkClass(identifier)) {
                throw new SymbolUnboundException(node.getLineNumber(), identifier); // Class type not found
            }

            // Set the node's type to the class being instantiated
            node.setType(new TypeNode(identifier));
            return identifier; // Return the class type as the result of this visit

        } else {
            // Retrieve the function's signature from the symbol table
            String type = symbolTables.peek().fLookup(identifier);
            if (type == null || type.equals("null")) {
                // Raise an exception if the function identifier is not bound or is "null"
                throw new SymbolUnboundException(node.getLineNumber(), identifier);
            }

            // Split the retrieved function signature into a list of parameter and return types
            ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));

            // If the node has function parameters, validate them against the expected parameter types
            if (node.getParameter() != null) {
                visit(node.getParameter(), new ArrayList<>(types));
            }

            // Set the function call node's type to the function's return type (first in the list)
            node.setType(new TypeNode(types.get(0)));
            return types.get(0); // Return the function's return type
        }
    }

    // @Override
    public String visit(ExpressionsNode node, ArrayList<String> expectedTypes) {
        // Remove the first expected type from the list since it will be compared against the current expression
        expectedTypes.remove(0);

        // Visit the left-side subexpression and determine its type
        String exprType = visit(node.getLeft());

        // Ensure there are still expected types left to validate against
        if (expectedTypes.isEmpty()) {
            throw new WrongTypeException(node.getLineNumber(), "void", exprType);
        }

        // Check if the current expression's type inherits from or matches the expected type
        if (!symbolTable.checkInherits(exprType, expectedTypes.get(0))) {
            throw new WrongTypeException(node.getLineNumber(), expectedTypes.get(0), exprType);
        }

        // If a right-side subexpression exists, recursively visit it with the remaining expected types
        if (node.getRight() != null) {
            visit(node.getRight(), expectedTypes);
        }

        // Set the type of the entire expression node to the current expression's type
        node.setType(new TypeNode(exprType));

        // Return the type of the current expression for further validation or processing
        return exprType;
    }

    /**
     * Function declaration, checks if the function identifier already exists
     * Matches the function returnType with the function block return type,
     * and adds the function with return type + parameter types to the symbolTable.
     * @return "void"
     */
    @Override
    public String visit(FunctionDNode node) {
        // Check that functions are only defined in the global scope
        if (symbolTables.size() > 1) {
            throw new IllegalFunctionLocationException(node.getLineNumber());
        }

        // Retrieve the function identifier
        String identifier = node.getFunction().getText();

        // Check if the function is already defined in the current scope
        if (symbolTables.peek().fLookup(identifier) != null) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }

        // Retrieve the expected return type for the function
        String returnType = node.getReturnType().getTypeName();

        // If this function is an action (with no return value), verify it returns "void"
        if (node.getIsAction() && !returnType.equals("void")) {
            throw new WrongTypeException(node.getLineNumber(), "void", returnType);
        }
        try {
            // Clone the current symbol table and add a new scope for the function's parameters
            symbolTables.push((SymbolTable) symbolTables.peek().clone());

            // Initialize an empty string to hold the parameter types
            String parameterTypes = "";
            // If the function has parameters, visit them and store their types
            if (node.getParameter() != null) {
                parameterTypes = visit(node.getParameter());
            }

            // If the function is an action, ensure that it returns a string expression
            if (node.getIsAction()) {
                String stringType = visit(node.getExpr());
                if (!stringType.equals("string")) {
                    throw new WrongTypeException(node.getLineNumber(), "string", stringType);
                }
            }

            // Visit the function's body block to verify the return type
            String returnedType = visit(node.getBlock());

            // Remove the function scope from the stack
            symbolTables.pop();

            // Check if the returned type matches the declared return type
            if (symbolTables.peek().checkInherits(returnedType, returnType)) {
                // Add the function to the symbol table based on its action status
                if (node.getIsAction()) {
                    symbolTables.peek().addFunction(identifier, "action," + parameterTypes);
                } else {
                    symbolTables.peek().addFunction(identifier, returnType + "," + parameterTypes);
                }
            } else {
                // Throw an exception if the returned type doesn't match the expected return type
                throw new WrongTypeException(node.getLineNumber(), returnType, returnedType);
            }
        } catch (CloneNotSupportedException e) {
            // Handle potential cloning issues with symbol tables
            System.err.println(e.getMessage());
        }

        // Set the function's type to "void" since it is always valid as a return value
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * Checks if the type of expression matches or inherits the type of the identifier
     * @return "void"
     */
    @Override
    public String visit(AssignmentNode node) {
        // Visit the left-hand side (identifier) of the assignment to determine its type
        String identifierType = visit(node.getLeft());
        // Visit the right-hand side (expression) of the assignment to determine its type
        String expressionType = visit(node.getRight());

        // Check if the expression type is compatible with the identifier type
        if (symbolTable.checkInherits(expressionType, identifierType)) {
            // If compatible, set the node's type to "void" and return "void"
            node.setType(new TypeNode("void"));
            return "void";
        }

        // If incompatible, throw a WrongTypeException indicating the expected and actual types
        throw new WrongTypeException(node.getLineNumber(), identifierType, expressionType);
    }

    /**
     * Handles class access by iterating through each field in the class,
     * and checks if the currentField is a static field/method or an instance of the class.
     * @return The type of the last object
     */
    @Override
    public String visit(ClassAccessNode node) {
        // Retrieve the initial object type by visiting the node's object expression
        String objectType = visit(node.getObject());

        // Traverse through each field/method access step by step
        for (ValueNode currentField : node.getValue()) {
            // If the object is prefixed as a class, remove the prefix to process its fields/methods
            if (objectType.startsWith("Class ")) {
                objectType = objectType.replaceFirst("^Class ", "");

                // Retrieve the symbol table for the current class type
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                if (classST == null) {
                    throw new TypeNotClassException(node.getLineNumber(), objectType);
                }
                // Initialize variables to store the identifier and next object type
                String identifier = "";
                String nextObjectType = "";
                // Handle static function calls by resolving the method's type
                if (currentField instanceof FunctionCallNode) {
                    identifier = ((FunctionCallNode) currentField).getFunction().getText();
                    nextObjectType = classST.fLookup("static " + identifier).split(",")[0];
                // Handle static field accesses by finding the field's type
                } else if (currentField instanceof IdentifierNode) {
                    identifier = ((IdentifierNode) currentField).getText();
                    nextObjectType = classST.vLookup("static " + identifier);
                }

                // If the static field/method cannot be found, throw an exception
                if (nextObjectType == null) {
                    throw new NoStaticFieldException(node.getLineNumber(), objectType, identifier);
                }
                objectType = nextObjectType;
            // Handle regular (non-static) class fields/methods
            } else if (symbolTables.peek().checkClass(objectType)) {
                // Retrieve the symbol table for the non-static class
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                if (classST == null) {
                    throw new TypeNotClassException(node.getLineNumber(), objectType);
                }

                String identifier = "";
                // Resolve the type for method calls
                if (currentField instanceof FunctionCallNode) {
                    // Retrieve the function name (method identifier) to search in the symbol table
                    identifier = ((FunctionCallNode) currentField).getFunction().getText();
                    String type = classST.fLookup(identifier);  // Lookup the function in the current class symbol table
                    if (type == null) {
                        // If the function is not found, throw an exception indicating it is not bound
                        throw new SymbolUnboundException(node.getLineNumber(), identifier);
                    }

                    // Parse the method's return type and parameter types
                    ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
                    objectType = types.get(0);  // The first value is the return type

                    // Ensure that the function is not declared with a "null" return type
                    if (type.equals("null")) {
                        throw new SymbolUnboundException(node.getLineNumber(), identifier);
                    }

                    // Process the parameters if the function has any, matching them to expected types
                    if (((FunctionCallNode) currentField).getParameter() != null) {
                        // Pass the remaining types (parameters) to the visit function for validation
                        visit(((FunctionCallNode) currentField).getParameter(), new ArrayList<>(types));
                    }

                // Resolve the type for identifier field accesses
                } else if (currentField instanceof IdentifierNode) {
                    // Retrieve the field name (identifier) and find its type in the class symbol table
                    identifier = ((IdentifierNode) currentField).getText();
                    objectType = classST.vLookup(identifier);  // Lookup the field in the current class symbol table

                // Handle array accesses by temporarily switching to the class symbol table
                } else if (currentField instanceof ArrayAccessNode) {
                    // Temporarily push the class symbol table onto the stack
                    symbolTables.push(classST);

                    // Visit the array access node to retrieve the type of the array elements
                    objectType = visit(currentField);

                    // Pop the class symbol table off the stack to restore the previous state
                    symbolTables.pop();
                }

                // If the field/method is not found, throw an exception
                if (objectType == null) {
                    throw new SymbolUnboundException(node.getLineNumber(), identifier);
                }
            } else {
                // If the object type isn't suitable for class/field access, throw an exception
                throw new WrongTypeException(node.getLineNumber(), "object or class", objectType);
            }
        }

        // Set the resolved type for this node
        node.setType(new TypeNode(objectType));
        return objectType;
    }

    /**
     * Adds a new class to the symbolTable and checks if the class has a superclass,
     * if yes, adds the FTable and VTable of the super to the class symbolTable.
     * Visits the block of the class and adds symbols for the class to the symbolTable.
     * @return "void"
     */
    @Override
    public String visit(ClassDNode node) {
        // Extract the class name from the node
        String className = node.getName().getText();

        // Restrict certain class names that cannot be declared
        if (className.startsWith("Card") || className.equals("ActionMenu") || className.equals("GameState")) {
            throw new IllegalTypeException(node.getLineNumber(), className); // Disallowed type name
        }

        // Default to "Object" superclass if none is provided
        String superclassName = "Object";
        if (node.getSuperClass() != null) {
            // If a superclass is explicitly defined, extract its name
            superclassName = node.getSuperClass().getText();
        }

        SymbolTable newTable;

        // Check for duplicate class definitions in the symbol table
        if (symbolTables.peek().checkClass(className)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), className);
        }

        // Verify that the specified superclass exists
        if (node.getSuperClass() != null && !symbolTables.peek().checkClass(superclassName)) {
            throw new SymbolUnboundException(node.getLineNumber(), superclassName);
        }

        // Add the class to the symbol table with its superclass
        symbolTables.peek().addClass(className, superclassName);

        try {
            // Create a clone of the current symbol table for the new class
            newTable = (SymbolTable) symbolTables.peek().clone();

            // If the superclass is not "Object", copy over its fields and methods
            if (!superclassName.equals("Object")) {
                SymbolTable superNewTable = symbolTables.peek().findClassSymbolTable(superclassName);

                // Copy the superclass's inner function and variable tables to the new class table
                newTable.getFTable().putAll(superNewTable.getInnerFTable());
                newTable.getVTable().putAll(superNewTable.getInnerVTable());
            }

            // Process the block of class fields and methods using the new symbol table
            visit(node.getBlock(), newTable);

            // Add the new class's symbol table to the main symbol table
            symbolTables.peek().addClassSymbols(className, newTable);
        } catch (CloneNotSupportedException e) {
            // Handle potential cloning errors during symbol table manipulation
            System.err.println(e.getMessage());
        }

        // Mark the type of this node as "void" because a class declaration doesn't return a value
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * For a block node, Visits either functionDefinition or Define node
     * @return "void"
     */
    // @Override
    public String visit(BlockNode node, SymbolTable table) {
        // Check if the current statement is a function declaration (FunctionDNode)
        if (node.getStatement() instanceof FunctionDNode) {
            // Process the function declaration specifically using the given symbol table
            visit((FunctionDNode) node.getStatement(), table);

        // Check if the current statement is a definition statement (DefineNode)
        } else if (node.getStatement() instanceof DefineNode) {
            // Process the definition statement specifically using the given symbol table
            visit((DefineNode) node.getStatement(), table);

        // For other types of statements, process them in a general manner
        } else {
            visit(node.getStatement());
        }

        // If the block has nested blocks, process those recursively using the given symbol table
        if (node.getBlocks() != null) {
            visit(node.getBlocks(), table);
        }

        // Set the type of this node to "void" because the block as a whole doesn't return a value directly
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * Function declaration within a class, checks if the function identifier already exists
     * Matches the function returnType with the function block return type,
     * and adds the function with return type + parameter types to the symbolTable.
     * @return "void"
     */
    //@Override
    public String visit(FunctionDNode node, SymbolTable table) {
        // Check if the function is incorrectly labeled as an action
        if (node.getIsAction()) {
            throw new WrongTypeException(node.getLineNumber(), "function", "action");
        }

        // Retrieve the function's identifier and return type
        String identifier = node.getFunction().getText();
        String returnType = node.getReturnType().getTypeName();

        // Check if the function is already defined in the current or superclass context
        String superMethod = table.fLookup(identifier);
        if (table.checkInnerF(identifier)) {
            // Throw an error if a duplicate definition is found in the current scope
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        } else if (superMethod != null) {
            // Verify that the return type matches the one in the superclass if overriding
            String superType = superMethod.split(",")[0];
            if (!returnType.equals(superType)) {
                throw new WrongTypeException(node.getLineNumber(), superType, returnType);
            }
        }

        // Clone the current symbol table to create a new scope for function parameters and local variables
        try {
            symbolTables.push((SymbolTable) table.clone());

            // Handle parameters and obtain their types as a comma-separated string
            String parameterTypes = "";
            if (node.getParameter() != null) {
                parameterTypes = visit(node.getParameter());
            }

            // Process the function's block to determine the return type
            String returnedType = visit(node.getBlock());

            // Restore the previous symbol table
            symbolTables.pop();

            // Check if the returned type matches the declared return type or is compatible via inheritance
            if (symbolTable.checkInherits(returnedType, returnType)) {
                // Add the function to the table with its identifier, return type, and parameter types
                table.addFunction(visit(node.getModifier()) + identifier, returnType + "," + parameterTypes);

                // Set the node's type to "void" since functions do not explicitly return values directly
                node.setType(new TypeNode("void"));
                return "void";
            } else {
                // Throw an error if the actual returned type doesn't match the declared type
                throw new WrongTypeException(node.getLineNumber(), returnType, returnedType);
            }
        } catch (CloneNotSupportedException e) {
            // Handle any exceptions during cloning with a runtime error for debugging purposes
            throw new RuntimeException(e);
        }
    }

    /**
     * For defining a field within a class
     * Checks if the identifier already exists in the innerValueTable of the symbolTable
     * Checks for a value to assign - then finds the type of the value to check if it is compatible with the type
     * adds the identifier along with the type to the symbolTable
     * @return "void"
     */
    //@Override
    public String visit(DefineNode node, SymbolTable table) {
        // Retrieve the identifier (variable name) for this definition
        String identifier = node.getID().getText();

        // Determine the type of the variable, adding "array " prefix if it's an array
        String type;
        if (node.isArray()) {
            type = "array " + node.getType().getTypeName();
        } else {
            type = node.getType().getTypeName();
        }

        // Ensure that the variable type is not "void" because "void" cannot be a data type
        if (type.equals("void")) {
            throw new IllegalTypeException(node.getLineNumber(), "void");
        }

        // Check if the variable is already defined in the current scope
        if (table.checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }

        // If the variable is initialized with a value, validate the value's type against the declared type
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node.getLineNumber(), type, exprType);
            }
        }

        // Add the variable to the symbol table with its identifier and type (including modifiers)
        table.addValue(visit(node.getModi()) + identifier, type);

        // Set the node's type to the determined type and return "void" since no value is directly returned
        node.setType(new TypeNode(type));
        return "void";
    }

    /**
     * For defining new identifier
     * Checks for type array
     * Checks for duplicate identifier within the symbolTables
     * Checks for a value to assign - then finds the type of the value to check if it is compatible with the type
     * adds the identifier along with the type to the symbolTable
     * @return "void"
     */
    @Override
    public String visit(DefineNode node) {
        // Determine the type of the variable, prefixing with "array " if it's an array
        String type;
        if (node.isArray()) {
            type = "array " + node.getType().getTypeName();
        } else {
            type = node.getType().getTypeName();
        }

        // Ensure that the variable type is not "void" because "void" cannot be used as a data type
        if (type.equals("void")) {
            throw new IllegalTypeException(node.getLineNumber(), "void");
        }

        // Retrieve the identifier (variable name) for this definition
        String identifier = node.getID().getText();

        // Check if the variable is already defined in the current scope
        if (symbolTables.peek().checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }

        // If the variable is initialized with a value, validate the value's type against the declared type
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node.getLineNumber(), type, exprType);
            }
        }

        // Add the variable to the current scope's symbol table with its identifier and type
        symbolTables.peek().addValue(identifier, type);

        // Set the node's type to the determined type and return it
        node.setType(new TypeNode(type));
        return type;
    }

    /**
     * Checks if node has right expression, and finds their common Ancestor
     * If there is a right node, sets return type to common ancestors type, else to expr1 type
     * @return The type of the entire expression
     */
    @Override
    public String visit(ExpressionsNode node) {
        // Visit the left subexpression and determine its type
        String expr1Type = visit(node.getLeft());

        // If a right subexpression exists, determine its type and find a common ancestor type
        if (node.getRight() != null) {
            String expr2Type = visit(node.getRight());

            // Find the closest common ancestor type that both subexpressions can inherit from
            String commonAncestor = symbolTables.peek().findClosestAncestor(expr1Type, expr2Type);

            // If the common ancestor type is "void", no valid common ancestor type was found
            if (commonAncestor.equals("void")) {
                throw new WrongTypeException(node.getLineNumber(), expr1Type, expr2Type);
            }

            // Set the node's type to the common ancestor type and return it
            node.setType(new TypeNode(commonAncestor));
            return commonAncestor;
        }

        // If there is no right subexpression, set the node's type to the type of the left subexpression
        node.setType(new TypeNode(expr1Type));
        return expr1Type;
    }

    /**
     * Checks if an identifier is a class or a normal identifier
     * Checks if the identifier exists in the cTable or vTable
     * @return the type of the identifier
     */
    @Override
    public String visit(IdentifierNode node) {
        // Attempt to find the class name associated with the identifier
        String className = symbolTables.peek().cLookup(node.getText());

        // If a class name is found, set the node's type to that class name and return it
        if (className != null) {
            node.setType(new TypeNode(className));
            return className;
        }

        // Attempt to find the variable type associated with the identifier
        String type = symbolTables.peek().vLookup(node.getText());

        // If no type is found, throw an exception indicating the symbol is unbound
        if (type == null) {
            throw new SymbolUnboundException(node.getLineNumber(), node.getText());
        }

        // Set the node's type to the resolved variable type and return it
        node.setType(new TypeNode(type));
        return type;
    }

    /**
     * Adds "" or "static" to node
     * @return The modifier with a space as a string
     */
    @Override
    public String visit(ModifierNode node) {
        // Check if the node has a specified modifier
        if (node.getModifier() == null) {
            // If no modifier is specified, return an empty string
            return "";
        }
        // Return the modifier with a trailing space
        return node.getModifier() + " ";
    }

    public TypeCheckerVisitor() {
        // Create the outer symbol table (global scope)
        symbolTable.createOuterSymbolTable();
        // Push the created symbol table onto the stack of symbol tables
        symbolTables.push(symbolTable);
    }
}