import gen.ExprLexer;
import gen.ExprParser;
import nodes.BlockNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
    // EvaluateExpressionVisitor value = new EvaluateExpressionVisitor();
    // System.out.println(value.visit(ast));



}
