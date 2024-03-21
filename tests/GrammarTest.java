package tests;

import gen.ExprLexer;
import org.antlr.v4.runtime.*;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GrammarTest {

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

        assertEquals(4, allTokens.size()); // Expecting three tokens: INT, '+', INT

        // Check first token is an integer
        assertEquals(ExprLexer.TYPE_INT, allTokens.get(0).getType());
        assertEquals("3", allTokens.get(0).getText());

        // Check second token is the '+' operator
        assertEquals(ExprLexer.PLUS, allTokens.get(1).getType());
        assertEquals("+", allTokens.get(1).getText());

        // Check third token is an integer
        assertEquals(ExprLexer.TYPE_INT, allTokens.get(2).getType());
        assertEquals("4", allTokens.get(2).getText());
    }
}
