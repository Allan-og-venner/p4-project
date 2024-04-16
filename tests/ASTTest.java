import gen.ExprLexer;
import gen.ExprParser;
import nodes.BlockNode;
import nodes.LoopNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ASTTest {

    //set up data to be used for tests
    String userDir = System.getProperty("user.dir");
    CharStream in;

    {
        try {
            in = CharStreams.fromFileName(userDir+"/src/code.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ExprLexer lexer = new ExprLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ExprParser parser = new ExprParser(tokens);
    ExprParser.ProgContext tree = parser.prog();
    BlockNode ast = new BuildASTVisitor().visitProg(tree);

    @Test
    public void testASTNode() {

        List<String> visitedNodes = BuildASTVisitor.getVisitedNodes();
        //check if the AST node is not null
        Assert.assertNotNull("Visited nodes should not be null", visitedNodes);
        //check if the AST node is an instance of LoopNode
        Assert.assertTrue("Visited nodes should contain 'Visited loop node'", visitedNodes.contains("Visited loop node"));

    }

}
