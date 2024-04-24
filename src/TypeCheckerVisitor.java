import nodes.*;
import org.antlr.v4.codegen.model.Loop;

import java.util.*;


public class TypeCheckerVisitor extends ASTVisitor<String>{
    private SymbolTable symbolTable = new SymbolTable();

    private Stack<SymbolTable> symbolTables = new Stack<>();


    /**
     * Addition, subtraction, multiplication and division accept both number types - float and int
     * If there is at least one float, all of them return float. Else, int.
     * @return "float" or "int"
     */
    //Addition, subtraction, multiplication and division accept both number types
    //If there is at least one float, all of them return float. Else, int.
    @Override
    public String visit(AdditionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    @Override
    public String visit(SubtractionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    @Override
    public String visit(MultiplicationNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    @Override
    public String visit(DivisionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    /**
     * Checks that the bool to negate is an int. Throws an exception if not.
     * @return "int"
     */
    //Takes only an int (bool) as input, and returns another one if true.
    @Override
    public String visit(NegateNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.equals("int")) {
            node.setType(new TypeNode(innerType));
            return innerType;
        }
        throw new WrongTypeException(node.getLineNumber(), "int", innerType);
    }

    /**
     * Checks that both operands of modNode is type int - returns int if true
     * @return "int"
     */
    //Takes 2 ints as input, and returns an int if true
    @Override
    public String visit(ModNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + ", " + type2);
    }

    /**
     * Checks that the input node is either an int or float - returns the same
     * @return "int" or "float"
     */
    //Takes int or float, and returns the same
    @Override
    public String visit(NegativeNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.matches("int|float")) {
            node.setType(new TypeNode(innerType));
            return innerType;
        }
        throw new WrongTypeException(node.getLineNumber(), "int or float", innerType);
    }

    /**
     * Checks for the types of statements in the block
     * Checks for types of statements in nested blocks.
     * if statement in block is returnNode - sets type of block to be the return type
     * if statement in block is controlNode or LoopNode - ??
     * @return ????
     */
    @Override
    public String visit(BlockNode node) {
        String stmType = visit(node.getStatement());
        String blockType = "void";
        if(node.getBlocks() != null) {
            blockType = visit(node.getBlocks());
        }
        if (node.getStatement() instanceof ReturnNode) {
            node.setType(new TypeNode(stmType));
            return stmType;
        }
        if (node.getStatement() instanceof ControlNode || node.getStatement() instanceof LoopNode) {
            if(!stmType.equals("void") && !stmType.equals(blockType)) {
                throw new WrongTypeException(node.getStatement().getLineNumber(), "Matching types",stmType + ", " + blockType);
            }
        }
        node.setType(new TypeNode(blockType));
        return blockType;
    }

    /**
     * Input is either int or float - returns the same
     * @return "int" or "float"
     */
    //Always an int
    @Override
    public String visit(NumberNode node) {
        node.setType(new TypeNode("int"));
        return "int";
    }

    //Always a float
    @Override
    public String visit(FloatNode node) {
        node.setType(new TypeNode("float"));
        return "float";
    }

    /**
     * Checks if both values on either side of the relational operator is an int - returns int if true.
     * @return "int"
     */
    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ORNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    /**
     * Checks if both values on either side of the relational operator is an int - returns int if true.
     * @return "int"
     */
    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ANDNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
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

        if (type1.equals(type2)) {
            node.setType(new TypeNode("int"));
            return "int";
        }

        if (symbolTable.checkInherits(type1, type2)
            || symbolTable.checkInherits(type2, type1)) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "compatible types", type1 + " and " + type2);
    }

    /**
     * Accepts both number types - float and int
     * If there is at least one float - return float. Else, int.
     * @return "float" or "int"
     */
    //The relational expressions accept all numbers, and if true, return an int
    @Override
    public String visit(GreaterThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + " and " + type2);
    }

    @Override
    public String visit(GTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    @Override
    public String visit(LessThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "number and number", type1 + type2);
    }

    @Override
    public String visit(LTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
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

        if (symbolTable.checkInherits(type1, type2)
                || symbolTable.checkInherits(type2, type1)) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node.getLineNumber(), "compatible types", type1 + " and " + type2);
    }

    //START OF CURSED NODES
    @Override
    public String visit(TypeNode node) {
        return null;
    }
    //END OF CURSED NODES WAAH

    @Override
    public String visit(CharNode node) {
        return "char";
    }


    @Override
    public String visit(StringNode node) {
        return "string";
    }

    @Override
    public String visit(BreakNode node) {
        return "void";
    }

    @Override
    public String visit(ContinueNode node) {
        return "void";
    }

    @Override
    public String visit(ReturnNode node) {
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
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String arrayType = visit(node.getArray());
        if (arrayType.startsWith("array ") && !symbolTable.checkClass(arrayType)) {
            String type = arrayType.replaceFirst("^array ", "");
            symbolTables.peek().addValue(node.getIterator().getText(), type);
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            node.setType(new TypeNode(returnType));
            return returnType;
        }
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
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String conditionalType = visit(node.getCondition());
        if (conditionalType.matches("int")) {
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            node.setType(new TypeNode(returnType));
            return returnType;
        }
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
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String conditionalType = visit(node.getCondition());
        if (conditionalType.matches("int")) {
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            node.setType(new TypeNode(returnType));
            return returnType;
        }
        throw new WrongTypeException(node.getLineNumber(), "int", conditionalType);
    }


    /**
     * Gets the type of the array, and checks if it starts with "array " to ensure that it is an array.
     * Removes the "array " prefix and returns the type.
     * @return type of array
     */
    @Override
    public String visit(ArrayAccessNode node) {
        String arrayType = visit(node.getArray());
        if (arrayType.startsWith("array ")) {
            String type = arrayType.replaceFirst("^array ", "");
            node.setType(new TypeNode(type));
            return type;
        }
        throw new WrongTypeException(node.getLineNumber(), "array", arrayType);
    }

    @Override
    public String visit(ArrayNode node) {
        return "array " + visit(node.getInnerNode());
    }

    /**
     * Retrives the identifier and type of function parameter.
     * Checks if the identifier is already present in the current scope,
     * otherwise it adds the identifier and type to the scope and returns the type
     * @return type of parameter
     */
    @Override
    public String visit(FparamNode node) {
        String identifier = node.getIdentifier().getText();
        String type = node.getType().getTypeName();
        if (symbolTables.peek().checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }
        symbolTables.peek().addValue(identifier, type);
        node.setType(new TypeNode(type));
        return type;
    }

    /**
     * If there are more parameters, checks both the left and right side,
     * otherwise just the left
     * @return left node, or left node and right node
     */
    @Override
    public String visit(FparamsNode node) {
        if (node.getRight() != null) {
            return visit(node.getLeft()) + "," + visit(node.getRight());
        }
        return visit(node.getLeft());
    }

    @Override
    public String visit(CardTypeNode node) {
        String exprType = visit(node.getExpression());
        String identifier = node.getIdentifier().getText();
        String method = node.getMethod().getText();
        if (!symbolTables.peek().checkInherits(exprType, "string")) {
            throw new WrongTypeException(node.getLineNumber(), "string", exprType);
        }
        Hashtable<String, SymbolTable> cTable = symbolTables.peek().getCTable();
        SymbolTable cardTable = cTable.get("Card");
        try {
            symbolTables.push((SymbolTable) cardTable.clone());
            String parameterTypes = "";
            if (node.getParams() != null) {
                parameterTypes = visit(node.getParams());
            }
            String returnedType = visit(node.getBlocks());
            symbolTables.pop();
            String types = "void," + parameterTypes;
            String expectedTypes = cardTable.fLookup(method);
            if (expectedTypes != null) {
                if (!types.equals("void," + parameterTypes)) {
                    throw new WrongTypeException(node.getLineNumber(), expectedTypes, types);
                }
            } else {
                if (symbolTables.peek().checkInherits(returnedType, "void")) {
                    System.out.println(types);
                    cardTable.addFunction(method, types);
                    System.out.println("2");
                } else {
                    throw new WrongTypeException(node.getLineNumber(), "void", returnedType);
                }
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
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
        String identifier = node.getFunction().getText();
        if (node.getHasNew()) {
            if (!symbolTable.checkClass(identifier)) {
                throw new SymbolUnboundException(node.getLineNumber(), identifier);
            }
            node.setType(new TypeNode(identifier));
            return identifier;

        } else {
            String type = symbolTables.peek().fLookup(identifier);
            ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
            if (type.equals("null")) {
                throw new SymbolUnboundException(node.getLineNumber(), identifier);
            }
            if(node.getParameter() != null) {
                visit(node.getParameter(), types);
            }
            node.setType(new TypeNode(types.get(0)));
            return types.get(0);
        }
    }

    public String visit(ExpressionsNode node, ArrayList<String> expectedTypes) {
        expectedTypes.remove(0);
        String exprType = visit(node.getLeft());
        if(expectedTypes.isEmpty()) {
            throw new WrongTypeException(node.getLineNumber(), "void", exprType);
        }
        if (!symbolTable.checkInherits(exprType, expectedTypes.get(0))) {
            throw new WrongTypeException(node.getLineNumber(), expectedTypes.get(0), exprType);
        }
        if (node.getRight() != null) {
            visit(node.getRight(), expectedTypes);
        }
        node.setType(new TypeNode(exprType));
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
        String identifier = node.getFunction().getText();
        if (symbolTables.peek().fLookup(identifier) != null) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }
        String returnType = node.getReturnType().getTypeName();
        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
            String parameterTypes = "";
            if(node.getParameter() != null) {
                parameterTypes = visit(node.getParameter());
            }
            String returnedType = visit(node.getBlocks());
            symbolTables.pop();
            if (symbolTables.peek().checkInherits(returnedType, returnType)) {
                symbolTables.peek().addFunction(identifier, returnType + "," + parameterTypes);
            } else {
                throw new WrongTypeException(node.getLineNumber(), returnType, returnedType);
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * Checks if the type of expression matches or inherits the type of the identifier
     * @return "void"
     */
    @Override
    public String visit(AssignmentNode node) {
        String identifierType = visit(node.getLeft());
        String expressionType = visit(node.getRight());
        if (symbolTable.checkInherits(expressionType, identifierType)) {
            node.setType(new TypeNode("void"));
            return "void";
        }
        throw new WrongTypeException(node.getLineNumber(), identifierType, expressionType);
    }

    /**
     * Handles class access by iterating through each field in the class,
     * and checks if the currentField is a static field/method or an instance of the class.
     * @return The type of the last object
     */
    @Override
    public String visit(ClassAccessNode node) {
        String objectType = visit(node.getObject());
        System.out.println(objectType);
        for (ValueNode currentField : node.getValue()) {
            if (objectType.startsWith("Class ")) {
                objectType = objectType.replaceFirst("^Class ", "");
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                String identifier = "";
                String nextObjectType = "";
                if (currentField instanceof FunctionCallNode) {
                    identifier = ((FunctionCallNode) currentField).getFunction().getText();
                    nextObjectType = classST.fLookup("static " + identifier);
                } else if (currentField instanceof IdentifierNode) {
                    identifier = ((IdentifierNode) currentField).getText();
                    nextObjectType = classST.vLookup("static " + identifier);
                }
                if(nextObjectType == null) {
                    throw new NoStaticFieldException(node.getLineNumber(), objectType, identifier);
                }
                objectType = nextObjectType;
            } else if (symbolTables.peek().checkClass(objectType)) {
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                System.out.println(classST.getInnerVTable());
                String identifier = "";
                if (currentField instanceof FunctionCallNode) {
                    identifier = ((FunctionCallNode) currentField).getFunction().getText();
                    objectType = classST.fLookup(identifier);
                } else if (currentField instanceof IdentifierNode) {
                    identifier = ((IdentifierNode) currentField).getText();
                    objectType = classST.vLookup(identifier);
                    System.out.println(objectType);
                }
                if (objectType == null) {
                    throw new SymbolUnboundException(node.getLineNumber(), identifier);
                }
            } else {
                throw new WrongTypeException(node.getLineNumber(), "object or class", objectType);
            }
        }
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
        String className = node.getName().getText();
        String superclassName = "Object";
        if (node.getSuperClass() != null) {
            superclassName = node.getSuperClass().getText();
        }
        SymbolTable newTable;
        if (symbolTables.peek().checkClass(className)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), className);
        }
        if (node.getSuperClass() != null && !symbolTables.peek().checkClass(superclassName)) {
            throw new SymbolUnboundException(node.getLineNumber(), superclassName);
        }
        symbolTables.peek().addClass(className, superclassName);
        try {
            newTable = (SymbolTable) symbolTables.peek().clone();
            if (!superclassName.equals("Object")){
                SymbolTable superNewTable = symbolTables.peek().findClassSymbolTable(superclassName);
                newTable.getFTable().putAll(superNewTable.getInnerFTable());
                newTable.getVTable().putAll(superNewTable.getInnerVTable());
            }
            visit(node.getBlocks(), newTable);
            symbolTables.peek().addClassSymbols(className, newTable);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * For a block node, Visits either functionDefinition or Define node
     * @return "void"
     */
    public String visit(BlockNode node, SymbolTable table) {
        if (node.getStatement() instanceof FunctionDNode) {
            visit((FunctionDNode) node.getStatement(), table);
        } else if (node.getStatement() instanceof DefineNode) {
            visit((DefineNode) node.getStatement(), table);
        } else {
            visit(node.getStatement());
        }
        if(node.getBlocks() != null) {
            visit(node.getBlocks(), table);
        }
        node.setType(new TypeNode("void"));
        return "void";
    }

    /**
     * Function declaration within a class, checks if the function identifier already exists
     * Matches the function returnType with the function block return type,
     * and adds the function with return type + parameter types to the symbolTable.
     * @return "void"
     */
    public String visit(FunctionDNode node, SymbolTable table) {
        String identifier = node.getFunction().getText();
        if (symbolTables.peek().fLookup(identifier) != null) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }
        String returnType = node.getReturnType().getTypeName();
        try {
            symbolTables.push((SymbolTable) table.clone());
            String parameterTypes = "";
            if(node.getParameter() != null) {
                parameterTypes = visit(node.getParameter());
            }
            String returnedType = visit(node.getBlocks());
            symbolTables.pop();
            if (symbolTable.checkInherits(returnedType, returnType)) {
                table.addFunction(visit(node.getModifier()) + identifier, returnType + "," + parameterTypes);
                node.setType(new TypeNode("void"));
                return "void";
            } else {
                throw new WrongTypeException(node.getLineNumber(), returnType, returnedType);
            }
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }


    /**
     * For defining a field within a class
     * Checks if the identifier already exists in the innerValueTable of the symbolTable
     * Checks for a value to assign - then finds the type of the value to check if it is compatible with the type
     * adds the identifier along with the type to the symbolTable
     * @return "void"
     */
    public String visit(DefineNode node, SymbolTable table) {
        String identifier = node.getID().getText();
        String type = node.getType().getTypeName();
        if (table.checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node.getLineNumber(), type, exprType);
            }
        }
        table.addValue( visit(node.getModi()) + identifier,type);
        node.setType(new TypeNode("void"));
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
        String type;
        if(node.getIndex() != null) {
            type = "array " + node.getType().getTypeName();
        } else {
            type = node.getType().getTypeName();
        }
        String identifier = node.getID().getText();
        if (symbolTables.peek().checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.getLineNumber(), identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node.getLineNumber(), type, exprType);
            }
        }
        symbolTables.peek().addValue(identifier, type);
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
        String expr1Type = visit(node.getLeft());
        if (node.getRight() != null) {
            String expr2Type = visit(node.getRight());
            String commonAncestor = symbolTables.peek().findClosestAncestor(expr1Type, expr2Type);
            if (commonAncestor.equals("void")) {
                throw new WrongTypeException(node.getLineNumber(), expr1Type, expr2Type);
            }
            node.setType(new TypeNode(commonAncestor));
            return commonAncestor;
        }
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
        String className = symbolTables.peek().cLookup(node.getText());
        if (className != null) {
            node.setType(new TypeNode(className));
            return className;
        }
        String type = symbolTables.peek().vLookup(node.getText());
        if (type == null) {
            throw new SymbolUnboundException(node.getLineNumber(), node.getText());
        }
        node.setType(new TypeNode(type));
        return type;
    }

    /**
     * Adds "" or "static" to node
     * @return The modifier with a space as a string
     */
    @Override
    public String visit(ModifierNode node) {
        if (node.getModifier() == null) {
            return "";
        }
        return node.getModifier() + " ";
    }

    public TypeCheckerVisitor() {
        symbolTable.createOuterSymbolTable();
        symbolTables.push(symbolTable);
    }
}