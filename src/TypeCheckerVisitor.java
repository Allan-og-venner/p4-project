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
                node.setType(new TypeNode("float"));
                return "float";
            }
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
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
        throw new WrongTypeException(node, type1, type2);
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
        throw new WrongTypeException(node, type1, type2);
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
        throw new WrongTypeException(node, type1, type2);
    }

    //Takes only an int (bool) as input, and returns another one if true.
    @Override
    public String visit(NegateNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.equals("int")) {
            node.setType(new TypeNode(innerType));
            return innerType;
        }
        throw new WrongTypeException(node, innerType);
    }

    //Takes 2 ints as input, and returns an int if true
    @Override
    public String visit(ModNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    //Takes int or float, and returns the same
    @Override
    public String visit(NegativeNode node) {
        String innerType = visit(node.getInnerNode());
        if (innerType.matches("int|float")) {
            node.setType(new TypeNode(innerType));
            return innerType;
        }
        throw new WrongTypeException(node, innerType);
    }

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
                throw new WrongTypeException(node, blockType, stmType);
            }
        }
        node.setType(new TypeNode(blockType));
        return blockType;
    }

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

    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ORNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    //The logical expressions accept only ints, and if true, return an int
    @Override
    public String visit(ANDNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.equals("int") && type2.equals("int")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

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
        throw new WrongTypeException(node, type1, type2);
    }

    //The relational expressions accept all numbers, and if true, return an int
    @Override
    public String visit(GreaterThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    @Override
    public String visit(GTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    @Override
    public String visit(LessThanNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    @Override
    public String visit(LTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());
        if (type1.matches("int|float") && type2.matches("int|float")) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
    }

    @Override
    public String visit(NOTEQNode node) {
        String type1 = visit(node.getLeft());
        String type2 = visit(node.getRight());

        if (symbolTable.checkInherits(type1, type2)
                || symbolTable.checkInherits(type2, type1)) {
            node.setType(new TypeNode("int"));
            return "int";
        }
        throw new WrongTypeException(node, type1, type2);
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
        if (arrayType.startsWith("array ") && !symbolTable.checkClass(arrayType)) {
            String type = arrayType.replaceFirst("^array ", "");
            symbolTables.peek().addValue(node.getIterator().getText(), type);
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            node.setType(new TypeNode(returnType));
            return returnType;
        }
        throw new WrongTypeException(node, arrayType);
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
            node.setType(new TypeNode(returnType));
            return returnType;
        }
        throw new WrongTypeException(node, conditionalType);
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
            String returnType = visit(node.getBlock());
            symbolTables.pop();
            node.setType(new TypeNode(returnType));
            return returnType;
        }
        throw new WrongTypeException(node, conditionalType);
    }

    @Override
    public String visit(ArrayAccessNode node) {
        String arrayType = visit(node.getArray());
        if (arrayType.startsWith("array ")) {
            String type = arrayType.replaceFirst("^array ", "");
            node.setType(new TypeNode(type));
            return type;
        }
        throw new WrongTypeException(node, arrayType);
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
            throw new DuplicateDefinitionException(node.lineNumber, identifier);
        }
        symbolTables.peek().addValue(identifier, type);
        node.setType(new TypeNode(type));
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
                throw new SymbolUnboundException(node.lineNumber, identifier);
            }
            node.setType(new TypeNode(identifier));
            return identifier;

        } else {
            String type = symbolTables.peek().fLookup(identifier);
            ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
            if (type.equals("null")) {
                throw new SymbolUnboundException(node.lineNumber, identifier);
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
            throw new WrongTypeException(node, "void", exprType, 1);
        }
        if (!symbolTable.checkInherits(exprType, expectedTypes.get(0))) {
            throw new WrongTypeException(node, expectedTypes.get(0), exprType, 1);
        }
        if (node.getRight() != null) {
            visit(node.getRight(), expectedTypes);
        }
        node.setType(new TypeNode(exprType));
        return exprType;
    }

    @Override
    public String visit(FunctionDNode node) {
        String identifier = symbolTables.peek().fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(node.lineNumber, identifier);
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
            if (symbolTables.peek().checkInherits(returnedType, returnType)) {
                symbolTables.peek().addFunction(identifier, returnType + "," + parameterTypes);
            } else {
                throw new WrongTypeException(node, returnType, returnedType);
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        node.setType(new TypeNode("void"));
        return "void";
    }

    @Override
    public String visit(AssignmentNode node) {
        String identifierType = visit(node.getLeft());
        String expressionType = visit(node.getRight());
        if (symbolTable.checkInherits(expressionType, identifierType)) {
            node.setType(new TypeNode("void"));
            return "void";
        }
        throw new WrongTypeException(node, identifierType, expressionType);
    }

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
                    throw new NoStaticFieldException(node.lineNumber, objectType, identifier);
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
                    throw new SymbolUnboundException(node.lineNumber, identifier);
                }
            } else {
                throw new WrongTypeException(node, objectType);
            }
        }
        node.setType(new TypeNode(objectType));
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
            throw new DuplicateDefinitionException(node.lineNumber, className);
        }
        if (node.getSuperClass() != null && !symbolTables.peek().checkClass(superclassName)) {
            throw new SymbolUnboundException(node.lineNumber, superclassName);
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

    public String visit(FunctionDNode node, SymbolTable table) {
        String identifier = table.fLookup(node.getFunction().getText());
        if (identifier != null) {
            throw new DuplicateDefinitionException(node.lineNumber, identifier);
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
                node.setType(new TypeNode("void"));
                return "void";
            } else {
                throw new WrongTypeException(node, returnType, returnedType);
            }
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }

    public String visit(DefineNode node, SymbolTable table) {
        String identifier = node.getID().getText();
        String type = node.getType().getTypeName();
        if (table.checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.lineNumber, identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node, type, exprType);
            }
        }
        table.addValue( visit(node.getModi()) + identifier,type);
        node.setType(new TypeNode("void"));
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
        if (symbolTables.peek().checkInnerV(identifier)) {
            throw new DuplicateDefinitionException(node.lineNumber, identifier);
        }
        if (node.getValue() != null) {
            String exprType = visit(node.getValue());
            if (!symbolTable.checkInherits(exprType, type)) {
                throw new WrongTypeException(node, type, exprType);
            }
        }
        symbolTables.peek().addValue(identifier, type);
        node.setType(new TypeNode("void"));
        return "void";
    }

    @Override
    public String visit(ExpressionsNode node) {
        String expr1Type = visit(node.getLeft());
        if (node.getRight() != null) {
            String expr2Type = visit(node.getRight());
            String commonAncestor = symbolTables.peek().findClosestAncestor(expr1Type, expr2Type);
            if (commonAncestor.equals("void")) {
                throw new WrongTypeException(node, expr1Type, expr2Type);
            }
            node.setType(new TypeNode(commonAncestor));
            return commonAncestor;
        }
        node.setType(new TypeNode(expr1Type));
        return expr1Type;
    }

    @Override
    public String visit(IdentifierNode node) {
        String className = symbolTables.peek().cLookup(node.getText());
        if (className != null) {
            node.setType(new TypeNode(className));
            return className;
        }
        String type = symbolTables.peek().vLookup(node.getText());
        if (type == null) {
            throw new SymbolUnboundException(node.lineNumber, node.getText());
        }
        node.setType(new TypeNode(type));
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