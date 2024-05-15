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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
            assertNotNull(ast);
            StatementNode node = ((ClassAccessNode) ast.getStatement().getLeft()).getObject();
            ValueNode node1 = ((ArrayAccessNode) node).getArray();
            String node2 = ((IdentifierNode) node1).getText();
            assertEquals("a", node2);
            System.out.println(node2);allan hvadspoba, e9 e9 e9 e9 e9
        } catch (Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
            assertEquals("1 Operation not supported (accessible node)",e.getMessage());
        }
    }
}
