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
        ParseTree tree = parser.prog();
        return new ParseResult(tree, parser);
    }

    @Test
    public void testDivisionExpression() {
        String input = "2 / 2;";
        ParseResult result = parse(input);
        String treeString = result.tree.toStringTree(result.parser);
        //Check if the parse tree matches the expected structure
        //print the treeString for debugging
        System.out.println("Actual: " + treeString);
        System.out.println("Expected: (prog (block (statement (expr (relation (arith (term (term (factor (value 2))) / (factor (value 2)))))) ;)) <EOF>)");
        Assert.assertEquals("(prog (block (statement (expr (relation (arith (term (term (factor (value 2))) / (factor (value 2)))))) ;)) <EOF>)", treeString);
    }

    @Test
    public void testPlusExpression() {
        String input = "2 + 2;";
        ParseResult result = parse(input);
        String treeString = result.tree.toStringTree(result.parser);
        //Check if the parse tree matches the expected structure
        //print the treeString for debugging
        System.out.println("Actual: " + treeString);
        System.out.println("Expected: (prog (block (statement (expr (relation (arith (arith (term (factor (value 2)))) + (term (factor (value 2)))))) ;)) <EOF>)");
        Assert.assertEquals("(prog (block (statement (expr (relation (arith (arith (term (factor (value 2)))) + (term (factor (value 2)))))) ;)) <EOF>)", treeString);
    }

    @Test
    public void testStringDeclaration() {
        String input = "int llama = 2;";
        ParseResult result = parse(input);
        String treeString = result.tree.toStringTree(result.parser);
        //Check if the parse tree matches the expected structure
        //print the treeString for debugging
        System.out.println("Actual: " + treeString);
        System.out.println("Expected: (prog (block (statement (decl (defin modifier (type int) llama = (expr (relation (arith (term (factor (value 2))))))) ;))) <EOF>)");
        Assert.assertEquals("(prog (block (statement (decl (defin modifier (type int) llama = (expr (relation (arith (term (factor (value 2))))))) ;))) <EOF>)", treeString);
    }
}
