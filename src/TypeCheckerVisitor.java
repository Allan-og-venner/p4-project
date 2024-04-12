import nodes.*;
import org.antlr.v4.codegen.model.Loop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        String stmType = visit(node.getStatement());
        String blockType = "void";
        if(node.getBlocks() != null) {
            blockType = visit(node.getBlocks());
        }
        if (node.getStatement() instanceof ReturnNode) {
            return stmType;
        }
        if (node.getStatement() instanceof ControlNode || node.getStatement() instanceof LoopNode) {
            if(stmType != blockType) {
                throw new WrongTypeException(blockType, stmType);
            }
        }
        return blockType;
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
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            return returnType;
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
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            return returnType;
        }
        throw new WrongTypeException("int", conditionalType);
    }

    @Override
    public String visit(WhileNode node) {
        System.out.println("We made it to while");
        try {
            symbolTables.push((SymbolTable) symbolTables.peek().clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        String conditionalType = visit(node.getCondition());
        if (conditionalType.matches("int")) {
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            return returnType;
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
        return "array " + visit(node.getInnerNode());
    }

    @Override
    public String visit(FparamNode node) {
        String identifier = node.getIdentifier().getText();
        String type = node.getType().getTypeName();
        if (symbolTables.peek().checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(identifier);
        }
        symbolTables.peek().addValue(identifier, type);
        return type;
    }


    @Override
    public String visit(FparamsNode node) {
        if (node.getRight() != null) {
            return visit(node.getLeft()) + "," + visit(node.getRight());
        }
        return visit(node.getLeft());
    }

    @Override
    public String visit(FunctionCallNode node) {
        String identifier = node.getFunction().getText();
        if (node.getHasNew()) {
            if (!symbolTable.checkClass(identifier)) {
                throw new SymbolUnboundException(identifier);
            }
            return identifier;

        } else {
            String type = symbolTables.peek().fLookup(identifier);
            ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
            if (type.equals("null")) {
                throw new SymbolUnboundException(identifier);
            }
            visit(node.getParameter(), types);
            return types.get(0);
        }
    }

    public String visit(ExpressionsNode node, ArrayList<String> expectedTypes) {
        expectedTypes.remove(0);
        String exprType = visit(node.getLeft());
        if (!symbolTable.checkInherits(exprType, expectedTypes.get(0))) {
            throw new WrongTypeException(expectedTypes.get(0), exprType);
        }
        if (node.getRight() != null) {
            visit(node.getRight(), expectedTypes);
        }
        return exprType;
    }

    @Override
    public String visit(FunctionDNode node) {
        String identifier = symbolTables.peek().fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(identifier);
        } else {
            identifier = node.getFunction().getText();
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
            if (symbolTable.checkInherits(returnedType, returnType)) {
                symbolTables.peek().addFunction(identifier, returnType + "," + parameterTypes);
            } else {
                throw new WrongTypeException(returnType, returnedType);
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return "void";
    }

    @Override
    public String visit(AssignmentNode node) {
        String identifierType = visit(node.getLeft());
        String expressionType = visit(node.getRight());
        if (identifierType.equals(expressionType)) {
            return "void";
        }
        throw new WrongTypeException(identifierType, expressionType);
    }

    @Override
    public String visit(ClassAccessNode node) {
        String objectType = visit(node.getObject());
        for (ValueNode currentField : node.getValue()) {
            if (objectType.startsWith("Class ")) {
                objectType = objectType.replaceFirst("^Class ", "");
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                if (currentField instanceof FunctionCallNode) {
                    objectType = classST.fLookup(((FunctionCallNode) currentField).getFunction().getText());
                } else if (currentField instanceof IdentifierNode) {
                    objectType = classST.vLookup(((IdentifierNode) currentField).getText());
                }
            } else if (symbolTables.peek().checkClass(objectType)) {
                SymbolTable classST = symbolTables.peek().findClassSymbolTable(objectType);
                if (currentField instanceof FunctionCallNode) {
                    objectType = classST.fLookup(((FunctionCallNode) currentField).getFunction().getText());
                } else if (currentField instanceof IdentifierNode) {
                    objectType = classST.vLookup(((IdentifierNode) currentField).getText());
                }
            } else {
                throw new WrongTypeException("object or class", objectType);
            }
        }
        return objectType;
    }

    @Override
    public String visit(ClassDNode node) {
        String className = node.getName().getText();
        String superclassName = "Object";
        if (node.getSuperClass() != null) {
            superclassName = node.getSuperClass().getText();
        }
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
            visit(node.getBlocks(), newTable);
            symbolTables.peek().addClassSymbols(className, newTable);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return "void";
    }

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
        return "void";
    }

    public String visit(FunctionDNode node, SymbolTable table) {
        String identifier = table.fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(identifier);
        } else {
            identifier = node.getFunction().getText();
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
                return "void";
            } else {
                throw new WrongTypeException(returnType, returnedType);
            }
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }

    public String visit(DefineNode node, SymbolTable table) {
        String identifier = node.getID().getText();
        String type = node.getType().getTypeName();
        if (table.vLookup(identifier) != null) {
            throw new DuplicateDefinitionException(identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!exprType.equals(type)) {
                throw new WrongTypeException(type, exprType);
            }
        }
        table.addValue( visit(node.getModi()) + identifier,type);
        return "void";
    }

    @Override
    public String visit(DefineNode node) {
        String type;
        if(node.getIndex() != null) {
            type = "array " + node.getType().getTypeName();
        } else {
            type = node.getType().getTypeName();
        }
        String identifier = node.getID().getText();
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
        return "void";
    }

    @Override
    public String visit(ExpressionsNode node) {
        String expr1Type = visit(node.getLeft());
        if (node.getRight() != null) {
            String expr2Type = visit(node.getRight());
            String commonAncestor = symbolTable.findClosestAncestor(expr1Type, expr2Type);
            if (commonAncestor.equals("void")) {
                throw new WrongTypeException(expr1Type, expr2Type);
            }
            return commonAncestor;
        }
        return expr1Type;
    }

    @Override
    public String visit(IdentifierNode node) {
        String className = symbolTables.peek().cLookup(node.getText());
        if (className != null) {
            return className;
        }
        String type = symbolTables.peek().vLookup(node.getText());
        if (type == null) {
            throw new SymbolUnboundException(node.getText());
        }
        return type;
    }

    @Override
    public String visit(ModifierNode node) {
        if (node.getModifier() == null) {
            return "";
        }
        return node.getModifier() + " ";
    }

    public TypeCheckerVisitor() {
        symbolTables.push(symbolTable);
    }
}