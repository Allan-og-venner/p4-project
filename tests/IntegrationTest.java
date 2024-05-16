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
}
