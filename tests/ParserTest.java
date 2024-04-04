import gen.ExprLexer;
import gen.ExprParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.Assert;
import org.junit.Test;

//integration test for the lexer and parser, tests if the parser generates the correct parse tree for an expression according to grammar
public class ParserTest {

    //create a class to hold the result of the parse method to be used for the tests
    private static class ParseResult {
        public final ParseTree tree;
        public final Parser parser;

        public ParseResult(ParseTree tree, Parser parser) {
            this.tree = tree;
            this.parser = parser;
        }
    }

    private ParseResult parse(String input) {
        CharStream charStream = CharStreams.fromString(input);
        ExprLexer lexer = new ExprLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.expr();
        return new ParseResult(tree, parser);
    }

    @Test
    public void testValidExpression() {
        String input = "2 / 2;";
        ParseResult result = parse(input);
        String treeString = result.tree.toStringTree(result.parser);

        // Check if the parse tree matches the expected structure
        //The rules are defined in the grammar file, and says: expr --> arith --> arith + term --> term --> factor --> value: 2
        Assert.assertEquals("(expr (arith (term (term (factor (value 2))) / (factor (value 2)))))", treeString);
    }
}
