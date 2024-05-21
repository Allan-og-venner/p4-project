import gen.ExprLexer;
import gen.ExprParser;
import nodes.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import org.antlr.v4.runtime.*;
import org.junit.Test;

import java.sql.Array;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class IntegrationTest {

    /*
    @Test
    public void testUNOSuccess() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeUNO.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */



    @Test
    public void testAccessible() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeAccessible.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);

            StatementNode arrayAccessNodeLeft = ((ClassAccessNode) ((AssignmentNode) ((StatementNode) ast.getStatement())).getLeft()).getObject();

            ValueNode identifierNode = ((ArrayAccessNode) arrayAccessNodeLeft).getArray();
            String identifierText = ((IdentifierNode) identifierNode).getText();

            ExpressionNode index = ((ArrayAccessNode) arrayAccessNodeLeft).getIndex();
            double indexValue = ((NumberNode) index).getValue();

            List<ValueNode> arrayAccessNode2 = ((ClassAccessNode) ((AssignmentNode) ((StatementNode) ast.getStatement())).getLeft()).getValue();
            String fieldText = ((IdentifierNode) arrayAccessNode2.get(0)).getText();

            StatementNode arrayAccessNodeRight = ast.getStatement();
            double assignmentValue = ((NumberNode) ((AssignmentNode) ((StatementNode) ast.getStatement())).getRight()).getValue();

            assertNotNull(ast);
            assertNotNull(arrayAccessNodeLeft);
            assertNotNull(arrayAccessNodeRight);
            assertNotNull(identifierNode);
            assertNotNull(index);
            assertNotNull(arrayAccessNode2);

            assertEquals("a", identifierText);
            assertEquals(4.0, indexValue,0);
            assertEquals("size", fieldText);

            assertEquals(2.0, assignmentValue,0);

        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (accessible node)",e.getMessage());
        }
    }

    @Test
    public void testClass() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeClass.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);

            BlockNode rightBlock = ast.getBlocks();
            assertEquals("Dog", ((ClassDNode) rightBlock.getBlocks().getStatement()).getName().getText());
            assertEquals("Animal", (((ClassDNode) rightBlock.getBlocks().getStatement()).getSuperClass().getText()));

            BlockNode classBlock = ((ClassDNode) rightBlock.getBlocks().getStatement()).getBlock();
            assertEquals("Human", classBlock.getStatement().getType().getTypeName());
            assertEquals("static", ((DefineNode) classBlock.getStatement()).getModi().getModifier());
            assertEquals("owner", ((DefineNode) classBlock.getStatement()).getID().getText());

            BlockNode classBlock2 = classBlock.getBlocks();
            assertEquals("Dog", classBlock2.getStatement().getType().getTypeName());
            assertNull(((DefineNode) classBlock2.getStatement()).getModi().getModifier());
            assertEquals("mother", ((DefineNode) classBlock2.getStatement()).getID().getText());

            BlockNode statementClassBlock3 = classBlock2.getBlocks().getBlocks().getStatement();
            assertEquals("void", ((FunctionDNode) statementClassBlock3).getReturnType().getTypeName());

            StatementNode statementClassBlock4 = ((FunctionDNode) statementClassBlock3).getBlock().getStatement();
            assertEquals("name", ((IdentifierNode) ((AdditionNode) ((ExpressionsNode) ((FunctionCallNode) statementClassBlock4).getParameter()).getLeft()).getLeft()).getText());
            assertEquals("\" barks!\"", ((StringNode) ((AdditionNode) ((ExpressionsNode) ((FunctionCallNode) statementClassBlock4).getParameter()).getLeft()).getRight()).getValue());
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (class node)",e.getMessage());
        }
    }

    @Test
    public void testAction() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeAction.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);

            StatementNode leftStatement = ast.getStatement();
            IdentifierNode functionDNode = ((FunctionDNode) leftStatement).getFunction();
            String functionName = functionDNode.getText();
            assertEquals("draw", functionName);
            Boolean isAction = ((FunctionDNode) leftStatement).getIsAction();
            assertEquals(true, isAction);
            ExpressionNode exprLeft = ((FunctionDNode) leftStatement).getExpr();
            assertNotNull(exprLeft);
            String actionDescription = ((StringNode) exprLeft).getValue();
            assertEquals("\"draw from deck\"", actionDescription);

            BlockNode exprRight = ((FunctionDNode) leftStatement).getBlock().getBlocks();
            BlockNode blockRight = exprRight.getBlocks();
            StatementNode statementNode = blockRight.getStatement();
            Boolean statementGetHasNew = ((FunctionCallNode) statementNode).getHasNew();
            assertEquals(false, statementGetHasNew);
            IdentifierNode StatementNodeRight = ((FunctionCallNode) statementNode).getFunction();
            String functionText = StatementNodeRight.getText();
            assertEquals("print", functionText);

            ExpressionsNode expressionNode = ((FunctionCallNode) statementNode).getParameter();
            AdditionNode printText = ((AdditionNode) expressionNode.getLeft());
            List<ValueNode> classAccessNodes = ((ClassAccessNode) printText.getRight()).getValue();
            ValueNode objectValue = (((ClassAccessNode) printText.getRight())).getObject();
            String classField = ((IdentifierNode) classAccessNodes.get(0)).getText();
            assertEquals("ID", classField);
            String identifierID = ((IdentifierNode) objectValue).getText();
            assertEquals("card", identifierID);

            BlockNode blockRight2 = exprRight.getBlocks().getBlocks();
            ExpressionNode ifthenNode = ((IfNode) blockRight2.getStatement()).getCondition();
            assertNotNull(ifthenNode);

            List<ValueNode> ifNodes = ((ClassAccessNode) (((InfixExpressionNode) ifthenNode).getRight())).getValue();
            ValueNode ifNode = ((ClassAccessNode) ((InfixExpressionNode) ifthenNode).getRight()).getObject();

            assertEquals("playPile", ((IdentifierNode) ifNode).getText());
            assertEquals("getTop", ((FunctionCallNode) ifNodes.get(0)).getFunction().getText());
            assertEquals("color", ((IdentifierNode) ifNodes.get(1)).getText());

            BlockNode ifBlock = ((IfNode) blockRight2.getStatement()).getBlock();
            assertEquals("allowAction", (((FunctionCallNode) ifBlock.getStatement()).getFunction().getText()));

            ExpressionsNode ifExpr = (((FunctionCallNode) ifBlock.getStatement()).getParameter());
            assertEquals("play", ((FunctionCallNode) ifExpr.getLeft()).getFunction().getText());
            assertEquals("card", ((IdentifierNode) ((FunctionCallNode) ifExpr.getLeft()).getParameter().getLeft()).getText());
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (action node)",e.getMessage());
        }
    }

    @Test
    public void testCardType() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeCardType.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);

            assertEquals("ID", ((IdentifierNode) ((CardTypeNode) ast.getStatement()).getIdentifier()).getText());
            assertEquals("plus", ((CardTypeNode) ast.getStatement()).getID());

            assertEquals("int", ((DefineNode) ((CardTypeNode) ast.getStatement()).getFields().get(0)).getType().getTypeName());
            assertEquals("amount", ((IdentifierNode) ((DefineNode) ((CardTypeNode) ast.getStatement()).getFields().get(0)).getID()).getText());

            assertEquals("onPlayed", ((IdentifierNode) ((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getFunction()).getText());
            assertEquals("Card", ((FparamsNode) ((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getParameter()).getLeft().getType().getTypeName());
            assertEquals("card", ((IdentifierNode) ((FparamsNode) ((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getParameter()).getLeft().getIdentifier()).getText());
            assertEquals("void", ((TypeNode) ((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getReturnType()).getTypeName());
            assertFalse(((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getIsAction());

            assertNotNull(((FunctionDNode) ((CardTypeNode) ast.getStatement()).getMethods().get(0)).getBlock());
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (cardType node)",e.getMessage());
        }
    }

    @Test
    public void testLoop() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeLoop.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);

            StatementNode leftStatement = ast.getStatement();
            assertEquals("player", ((ForNode) leftStatement).getIterator().getText());
            assertEquals("players", ((IdentifierNode) ((ForNode) leftStatement).getArray()).getText());
            assertNotNull(((ForNode) leftStatement).getBlock());

            BlockNode leftBlock = ast.getBlocks();
            assertEquals("colors", ((IdentifierNode) ((InfixExpressionNode) ((WhileNode) leftBlock.getStatement()).getCondition()).getLeft()).getText());
            assertTrue((((WhileNode) leftBlock.getStatement()).getCondition()) instanceof LessThanNode);
            assertEquals(4.0, ((NumberNode) ((InfixExpressionNode) ((WhileNode) leftBlock.getStatement()).getCondition()).getRight()).getValue(), 0);
            assertNotNull(((WhileNode) leftBlock.getStatement()).getBlock());
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (loop node)",e.getMessage());
        }
    }

    @Test
    public void TestContinueBreak(){
        try {
        String userDir = System.getProperty("user.dir");
        CharStream in = CharStreams.fromFileName(userDir + "/tests/text/codeContinueBreak.txt");
        ExprLexer lexer = new ExprLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ExprParser.ProgContext tree = parser.prog();
        BlockNode ast = new BuildASTVisitor().visitProg(tree);
        assertNotNull(ast);

        StatementNode forloop = ast.getStatement();

        StatementNode continueNode = ((IfNode)((ForNode) forloop).getBlock().getStatement()).getBlock().getStatement();
        assertNotNull(continueNode);

        StatementNode breakNode = ((IfNode)(((ForNode) forloop).getBlock()).getBlocks().getStatement()).getBlock().getStatement();
        assertNotNull(breakNode);

        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (loop node)",e.getMessage());
        }
    }

    @Test
    public void TestTypeAssignment() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeAssign.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeSubtraction() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeSubtraction.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: number and number, received: string, string", e.getMessage());
        }
    }

    @Test
    public void TestTypeClassArray() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeClassArray.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("25: Expected type: array Animal, received: array Object", e.getMessage());
        }
    }

    @Test
    public void TestTypeExceedAParameters() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeExceededAParameters.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("5: Expected type: void, received: int", e.getMessage());
        }
    }

    @Test
    public void TestTypeFunctionReturnError() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeWrongReturnType.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeClass() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeClass.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
            assertEquals(((ClassDNode)ast.getStatement()).getType().getTypeName(),"void");
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeClassAccess() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeClassAccess.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
            assertEquals("Animal", ((ClassAccessNode)((AssignmentNode)ast.getBlocks().getBlocks().getStatement()).getLeft()).getObject().getType().getTypeName());
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeArrayDefine() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeArrayDefine.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
            assertEquals("array Animal", ast.getBlocks().getStatement().getType().getTypeName());
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeReturn() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeReturn.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);
            String returnType = ((FunctionDNode)ast.getStatement()).getReturnType().getTypeName();
            String IfNodeType = ((ReturnNode)((IfNode)((FunctionDNode) ast.getStatement()).getBlock()
                    .getStatement()).getBlock().getStatement()).getInnerNode().getType().getTypeName();
            String outerBlockType = ((FunctionDNode)ast.getStatement()).getBlock().getType().getTypeName();
            assertEquals(returnType, IfNodeType);
            assertEquals(returnType, outerBlockType);
        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }

    @Test
    public void TestTypeSubClass() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/Tests/Text/codeTypeSubClass.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
            typeChecker.visit(ast);

            assertEquals("Object",typeChecker.getSymbolTables().peek().getTypes().get("Animal"));
            assertEquals("Animal",typeChecker.getSymbolTables().peek().getTypes().get("Dog"));
            assertEquals("Animal",typeChecker.getSymbolTables().peek().getTypes().get("Cat"));

            String arrayType = ast.getBlocks().getBlocks().getBlocks().getStatement().getType().getTypeName();
            String firstElementType = ((ArrayNode)((DefineNode)ast.getBlocks().getBlocks().getBlocks().getStatement()).getValue()).getInnerNode().getLeft().getType().getTypeName();
            String secondElementType = ((ArrayNode)((DefineNode)ast.getBlocks().getBlocks().getBlocks().getStatement()).getValue()).getInnerNode().getRight().getType().getTypeName();
            assertEquals(arrayType,"array Animal");
            assertEquals(firstElementType, "Cat");
            assertEquals(secondElementType, "Dog");

        } catch (Exception e) {
            assertThat(e, instanceOf(WrongTypeException.class));
            assertEquals("1: Expected type: int, received: string", e.getMessage());
        }
    }
}
