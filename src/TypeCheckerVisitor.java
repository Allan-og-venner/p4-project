import nodes.*;
import java.util.Stack;



public class TypeCheckerVisitor extends ASTVisitor<String>{
    private SymbolTable symbolTable = new SymbolTable();

    private Stack<SymbolTable> symbolTables = new Stack<>();

    //Addition, subtraction, multiplication and division accept both number types
    //If there is at least one float, all of them return float. Else, int.
    @Override
    public String visit(AdditionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(SubtractionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(MultiplicationNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(DivisionNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    //Takes only an int (bool) as input, and returns another one if true.
    @Override
    public String visit(NegateNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.equals("int")) {
            return innerType;
        }
        throw new WrongTypeException("int", innerType);
    }

    //Takes 2 ints as input, and returns an int if true
    @Override
    public String visit(ModNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    //Takes int or float, and returns the same
    @Override
    public String visit(NegativeNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.matches("int|float")) {
            return innerType;
        }
        throw new WrongTypeException("int or float", innerType);
    }

    @Override
    public String visit(BlockNode node) {
        if (node.getBlocks() != null) {
            return visit(node.getStatement());
        } else {
            return visit(node.getBlocks());
        }
    }

    //Always an int
    @Override
    public String visit(NumberNode node) {
        return "int";
    }

    //Always a float
    @Override
    public String visit(FloatNode node) {
        return "float";
    }

    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ORNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ANDNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(EQEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        if (type1.equals(type2)) {
            return "int";
        }

        if (symbolTable.checkInherits(type1, type2)
            || symbolTable.checkInherits(type2, type1)) {
            return "int";
        }
        throw new WrongTypeException("compatible types", type1 + " and " + type2);
    }

    //The relational expressions accept all numbers, and if true, return an int
    @Override
    public String visit(GreaterThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(GTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(LessThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(LTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            if (type1.equals("float") || type2.equals("float")) {
                return "float";
            }
            return "int";
        }
        throw new WrongTypeException("number and number", type1 + type2);
    }

    @Override
    public String visit(NOTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        if (symbolTable.checkInherits(type1, type2)
                || symbolTable.checkInherits(type2, type1)) {
            return "int";
        }
        throw new WrongTypeException("compatible types", type1 + " and " + type2);
    }

    //START OF CURSED NODES
    @Override
    public String visit(TypeNode node) {
        return null;
    }

    @Override
    public String visit(VoidNode node) {
        return null;
    }

    @Override
    public String visit(IntNode node) {
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

    @Override
    public String visit(ForNode node) {
        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String arrayType = visit(node.getArray());
        if (arrayType.startsWith("array") && !symbolTable.checkClass(arrayType)) {
            visit(node.getBlock());
            symbolTables.pop();
            return "void";
        }
        throw new WrongTypeException("array", arrayType);
    }

    @Override
    public String visit(IfNode node) {
        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String conditionalType = visit(node.getCondition());
        if (conditionalType.matches("int")) {
            visit(node.getBlock());
            symbolTables.pop();
            return "void";
        }
        throw new WrongTypeException("int", conditionalType);
    }

    @Override
    public String visit(WhileNode node) {
        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String conditionalType = visit(node.getCondition());
        if (conditionalType.matches("int")) {
            visit(node.getBlock());
            symbolTables.pop();
            return "void";
        }
        throw new WrongTypeException("int", conditionalType);
    }

    @Override
    public String visit(ArrayAccessNode node) {
        String arrayType = visit(node.getArray());
        if (arrayType.startsWith("array ") && !symbolTable.checkClass(arrayType)) {
            return arrayType.replaceFirst("^array ", "");
        }
        throw new WrongTypeException("array", arrayType);
    }

    @Override
    public String visit(ArrayNode node) {
        return "array " + node.getInnerNode();
    }

    @Override
    public String visit(FparamNode node) {
        return null;
    }

    @Override
    public String visit(FparamsNode node) {
        return null;
    }

    @Override
    public String visit(FunctionCallNode node) {
        return null;
    }

    @Override
    public String visit(FunctionDNode node) {
        String identifier = symbolTables.peek().fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(identifier);
        } else {
            identifier = node.getFunction().getText();
        }
        String returnType = node.getReturnType().getType();

        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
            visit(node.getParameter());
            String returnedType = visit(node.getBlocks());
            symbolTables.pop();
            if (returnType.equals(returnedType)) {
                symbolTables.peek().addFunction(identifier, returnType);
            } else {
                throw new WrongTypeException("matching types", returnType + " and " + returnedType);
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String visit(AssignmentNode node) {
        String identifierType = visit(node.getLeft());
        String expressionType = visit(node.getRight());
        if (identifierType.equals(expressionType)) {
            return null;
        }
        throw new WrongTypeException(identifierType, expressionType);
    }

    @Override
    public String visit(ClassAccessNode node) {
        return null;
    }

    @Override
    public String visit(ClassDNode node) {
        String className = node.getName().getText();
        String superclassName = node.getSuperClass().getText();
        SymbolTable newTable;
        if (symbolTables.peek().checkClass(className)) {
            throw new DuplicateDefinitionException(className);
        }
        if (node.getSuperClass() != null && !symbolTables.peek().checkClass(superclassName)) {
            throw new SymbolUnboundException(superclassName);
        }
        symbolTables.peek().addClass(className, superclassName);
        try {
            newTable = (SymbolTable) symbolTables.peek().clone();
            visit(node.getBlock(), newTable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String visit(BlockNode node, SymbolTable table) {
        if (node.getStatement() instanceof DefineNode 
                || node.getStatement() instanceof FunctionDNode) {
             visit(node.getStatement(), table);
        } else {
            visit(node.getStatement());
        }
        visit(node.getBlocks());
        return null;
    }

    public String visit(FunctionDNode node, SymbolTable table) {
        String identifier = table.fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(identifier);
        } else {
            identifier = node.getFunction().getText();
        }
        String returnType = node.getReturnType().getType();
        String returnedType = visit(node.getBlocks());
        if (returnType.equals(returnedType)) {
            table.addFunction(identifier, visit(node.getModifier()) + returnType);
            return null;
        } else {
            throw new WrongTypeException("matching types", returnType + " and " + returnedType);
        }
    }

    public String visit(DefineNode node, SymbolTable table) {
        String identifier = node.getID().getText();
        String type = node.getType().getType();
        if (table.vLookup(identifier) != null) {
            throw new DuplicateDefinitionException(identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!exprType.equals(type)) {
                throw new WrongTypeException(type, exprType);
            }
        }
        table.addValue(identifier, visit(node.getModi()) + type);
        return null;
    }

    @Override
    public String visit(DefineNode node) {
        String identifier = node.getID().getText();
        String type = node.getType().getType();
        if (symbolTables.peek().vLookup(identifier) != null) {
            throw new DuplicateDefinitionException(identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!exprType.equals(type)) {
                throw new WrongTypeException(type, exprType);
            }
        }
        symbolTables.peek().addValue(identifier, type);
        return null;
    }

    @Override
    public String visit(ExpressionsNode node) {
        String expr1Type = visit(node.getLeft());
        if (node.getRight() != null) {
            String expr2Type = visit(node.getRight());
            if (!expr1Type.equals(expr2Type)) {
                throw new WrongTypeException("matching types", expr1Type + " and " + expr2Type);
            }
        }
        return expr1Type;
    }

    @Override
    public String visit(IdentifierNode node) {
        String type = symbolTables.peek().vLookup(node.getText());
        if (type == null) {
            throw new SymbolUnboundException(node.getText());
        }
        return type;
    }

    @Override
    public String visit(ModifierNode node) {
        if(node.getModifier() == null) {
            return "";
        }
        return node.getModifier() + " ";
    }
    
    public TypeCheckerVisitor() {
        symbolTables.push(symbolTable);
    }
}