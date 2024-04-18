import gen.ExprLexer;
import org.antlr.v4.runtime.*;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

//Unit tests for the lexer

public class LexerTest {

    @Test
    public void testSimpleExpressionTokens() {
        String expression = "3 + 4";
        ExprLexer lexer = new ExprLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill(); // Load all tokens to inspect them

        List<Token> allTokens = tokens.getTokens();

        //print tokens for debugging
        for (Token t : allTokens) {
            System.out.println(t);
        }

        assertEquals(4, allTokens.size()); // Expecting four tokens: INT, '+', INT, EOF

        // Check first token is an integer
        assertEquals(ExprLexer.NUMERAL, allTokens.get(0).getType());
        assertEquals("3", allTokens.get(0).getText());

        // Check second token is the '+' operator
        assertEquals(ExprLexer.PLUS, allTokens.get(1).getType());
        assertEquals("+", allTokens.get(1).getText());

        // Check third token is an integer
        assertEquals(ExprLexer.NUMERAL, allTokens.get(2).getType());
        assertEquals("4", allTokens.get(2).getText());
    }

    @Test
    public void testCharToken() {
        String expression = "'c'";
        ExprLexer lexer = new ExprLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill(); // Load all tokens to inspect them

        List<Token> allTokens = tokens.getTokens();

        //print tokens for debugging
        for (Token t : allTokens) {
            System.out.println(t);
        }

        assertEquals(2, allTokens.size()); // Expecting two tokens: CHAR, EOF

        // Check token is a string
        assertEquals(ExprLexer.CHAR, allTokens.get(0).getType());
        assertEquals("'c'", allTokens.get(0).getText());

    }
}
