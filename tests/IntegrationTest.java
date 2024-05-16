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

    @Test
    public void testBlockNode() {
        try {
            String userDir = System.getProperty("user.dir");
            CharStream in = CharStreams.fromFileName(userDir + "/tests/codeBlockNode.txt");
            ExprLexer lexer = new ExprLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ExprParser.ProgContext tree = parser.prog();
            BlockNode ast = new BuildASTVisitor().visitProg(tree);
            assertNotNull(ast);
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (block node)",e.getMessage());
        }
    }

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



            StatementNode arrayAccessNodeLeft = ((ClassAccessNode) ast.getStatement().getLeft()).getObject();

            ValueNode identifierNode = ((ArrayAccessNode) arrayAccessNodeLeft).getArray();
            String identifierText = ((IdentifierNode) identifierNode).getText();

            ExpressionNode index = ((ArrayAccessNode) arrayAccessNodeLeft).getIndex();
            double indexValue = ((NumberNode) index).getValue();

            List arrayAccessNode2 = ((ClassAccessNode) ast.getStatement().getLeft()).getValue();
            String fieldText = ((IdentifierNode)arrayAccessNode2.get(0)).getText();

            StatementNode arrayAccessNodeRight = ast.getStatement().getRight();
            double assignmentValue = ((NumberNode)arrayAccessNodeRight).getValue();
            System.out.println(assignmentValue);

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
            assertEquals("name", ((IdentifierNode) ((ExpressionsNode) ((FunctionCallNode) statementClassBlock4).getParameter()).getLeft().getLeft()).getText());
            assertEquals("\" barks!\"", ((StringNode) ((ExpressionsNode) ((FunctionCallNode) statementClassBlock4).getParameter()).getLeft().getRight()).getValue());

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
            ValueNode objectValue = ((ClassAccessNode) printText.getRight()).getObject();
            String classField = ((IdentifierNode) classAccessNodes.get(0)).getText();
            assertEquals("ID", classField);
            String identifierID = ((IdentifierNode) objectValue).getText();
            assertEquals("card", identifierID);

            BlockNode blockRight2 = exprRight.getBlocks().getBlocks();
            ExpressionNode ifthenNode = ((IfNode) blockRight2.getStatement()).getCondition();
            assertNotNull(ifthenNode.getLeft());

            List<ValueNode> ifNodes = ((ClassAccessNode) ifthenNode.getRight()).getValue();
            ValueNode ifNode = ((ClassAccessNode) ifthenNode.getRight()).getObject();

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
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (loop node)",e.getMessage());
        }
    }
}
