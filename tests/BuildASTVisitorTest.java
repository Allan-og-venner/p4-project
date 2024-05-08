import junit.framework.TestCase;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.mockito.Mock;
import org.mockito.Mockito;
import gen.*;
import nodes.*;

import javax.accessibility.AccessibleValue;
import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class BuildASTVisitorTest extends TestCase {

    public void testVisitControl() {
    }

    public void testVisitIfthen() {
    }

    //Tests whether expressionNode and blockNode will be set
    public void testVisitLoopWhile() throws Exception {
        // Assume ctx is your WhileStatementContext from ANTLR
        ExprParser.LoopContext ctx = Mockito.mock(ExprParser.LoopContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        when(ctx.expr()).thenReturn(exprCtx);
        when(ctx.block()).thenReturn(blockCtx);
        when(ctx.KEY_WHILE()).thenReturn(node);


        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        LessThanNode expectedExpression = new LessThanNode();
        BlockNode expectedBlock = new BlockNode();
        doReturn(expectedExpression).when(visitor).visitExpr(exprCtx);
        doReturn(expectedBlock).when(visitor).visitBlock(blockCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);



        // Visit the while statement
        WhileNode result = (WhileNode) visitor.visitLoop(ctx);

        // Assertions
        assertNotNull(result.getCondition());
        assertNotNull(result.getBlock());
        assertEquals("Expression should be assigned correctly.",expectedExpression.getClass(), result.getCondition().getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());
        assertSame( "Block should be assigned correctly.",expectedBlock, result.getBlock());
    }
    public void testVisitLoopFor() throws Exception {
        // Assume ctx is your WhileStatementContext from ANTLR
        ExprParser.LoopContext ctx = Mockito.mock(ExprParser.LoopContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        TerminalNode node2 = Mockito.mock(TerminalNode.class);
        when(ctx.expr()).thenReturn(exprCtx);
        when(ctx.block()).thenReturn(blockCtx);
        when(ctx.KEY_FOR()).thenReturn(node);
        when(ctx.IDENTIFIER()).thenReturn(node2);


        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        LessThanNode expectedExpression = new LessThanNode();
        BlockNode expectedBlock = new BlockNode();
        doReturn(expectedExpression).when(visitor).visitExpr(exprCtx);
        doReturn(expectedBlock).when(visitor).visitBlock(blockCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);



        // Visit the while statement
        ForNode result = (ForNode) visitor.visitLoop(ctx);

        // Assertions
        assertNotNull(result.getIterator());
        assertNotNull(result.getArray());
        assertNotNull(result.getBlock());
        assertEquals("Expression should be assigned correctly.",expectedExpression.getClass(), result.getArray().getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());
        assertSame( "Block should be assigned correctly.",expectedBlock, result.getBlock());
    }


    public void testVisitCommand() {
        //test for return is correct
    }

    public void testVisitAssign() {
    }

    public void testVisitDefin() {
    }

    public void testVisitType() {
    }

    public void testVisitFdecl() {
    }

    public void testVisitFparams() {
    }

    public void testVisitFparam() {
    }

    public void testVisitCdecl() {
    }

    public void testVisitExpr() {
    }

    public void testVisitArith() {
    }

    public void testVisitRelation() {
    }

    public void testVisitTerm() {
    }

    public void testVisitFactor() {
    }

    public void testVisitCall() {
    }

    public void testVisitExprs() {
    }

    public void testVisitValue() {
    }

    public void testVisitArray() {
    }

    public void testVisitMethod() {
    }

    public void testVisitAccessibleObject() {
        ExprParser.ArrayAccessContext ctx = Mockito.mock(ExprParser.ArrayAccessContext.class);


        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ArrayAccessNode result = (ArrayAccessNode) visitor.visitArrayAccess(ctx);
        assertNotNull(result.getArray());
        assertNotNull(result.getIndex());
        //assertEquals("Expression should be assigned correctly.",expectedArrayAccess.getClass(), result.getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());

    }
    public void testVisitAccessArrayOnly() {
        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessibleValueContext accessibleValueCtx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        when(accessingCtx.L_BRACKET()).thenReturn(node);
        when(accessingCtx.expr()).thenReturn(exprCtx);
        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(accessingCtx.accessibleValue()).thenReturn(accessibleValueCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        ArrayAccessNode expectedAccessible = new ArrayAccessNode();
        NumberNode expectedIndex = new NumberNode();
        doReturn(expectedAccessible).when(visitor).visitAccessibleObject(accessibleObjectCtx);
        doReturn(expectedIndex).when(visitor).visitExpr(exprCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ArrayAccessNode result = (ArrayAccessNode) visitor.visitAccess(ctx);


        assertNotNull(result);
        assertEquals(expectedAccessible.getClass(), result.getClass());

    }

    public void testVisitAccessClass() {
        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessibleValueContext accessibleValueCtx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        when(accessingCtx.PERIOD()).thenReturn(node);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);
        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(accessingCtx.accessibleValue()).thenReturn(accessibleValueCtx);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        NumberNode expectedValue = new NumberNode();
        ClassAccessNode expectedArrayAccess = new ClassAccessNode();

        doReturn(expectedValue).when(visitor).visitAccessibleValue(accessibleValueCtx);
        doReturn(expectedArrayAccess).when(visitor).visitAccessibleObject(accessibleObjectCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx);
        //List<ValueNode> valueNodeList = Arrays.asList(mockedNumberNode);

        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);





        ClassAccessNode result = (ClassAccessNode) visitor.visitAccess(ctx);

        assertNotNull(result);
        assertEquals(expectedArrayAccess.getClass(), result.getClass());

    }

    public void testVisitAccessClassArray() {
        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessibleValueContext accessibleValueCtx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        ExprParser.ExprContext ExprCtx = Mockito.mock(ExprParser.ExprContext.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);

        //when(accessingCtx.PERIOD()).thenReturn(node);
        when(accessingCtx.L_BRACKET()).thenReturn(node);


        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);
        when(ctx.accessing(0)).thenReturn(accessingCtx);

        //when(accessingCtx.accessibleValue()).thenReturn(accessibleValueCtx);
        when(accessingCtx.expr()).thenReturn(ExprCtx);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        NumberNode expectedValue = new NumberNode();
        ClassAccessNode expectedArrayAccess = new ClassAccessNode();
        ArrayAccessNode expectedArrayNode = new ArrayAccessNode();

        doReturn(expectedValue).when(visitor).visitAccessibleValue(accessibleValueCtx);
        doReturn(expectedArrayAccess).when(visitor).visitAccessibleObject(accessibleObjectCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx);
        //List<ValueNode> valueNodeList = Arrays.asList(mockedNumberNode);

        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);





        ClassAccessNode result = (ClassAccessNode) visitor.visitAccess(ctx);
        assertNotNull(result);
        assertEquals(expectedArrayAccess.getClass(), result.getClass());

    }

    public void testVisitArrayAccess() {
        ExprParser.ArrayAccessContext ctx = Mockito.mock(ExprParser.ArrayAccessContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);

        TerminalNode node = Mockito.mock(TerminalNode.class);

        when(ctx.expr()).thenReturn(exprCtx);
        when(ctx.IDENTIFIER()).thenReturn(node);


        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        ArrayAccessNode expectedArrayAccess = new ArrayAccessNode();


        doReturn(expectedArrayAccess).when(visitor).visitExpr(exprCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        // Visit the while statement
        ArrayAccessNode result = (ArrayAccessNode) visitor.visitArrayAccess(ctx);
        assertNotNull(result.getArray());
        assertNotNull(result.getIndex());
        assertEquals("Expression should be assigned correctly.",expectedArrayAccess.getClass(), result.getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());

    }

    public void testVisitAccessibleValueCall() {
        ExprParser.AccessibleValueContext ctx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        ExprParser.CallContext callCtx = Mockito.mock(ExprParser.CallContext.class);

        when(ctx.call()).thenReturn(callCtx);

        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        ValueNode expectedValue = new ValueNode() {};

        doReturn(expectedValue).when(visitor).visitCall(callCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        // Visit the while statement

        ValueNode result = (ValueNode) visitor.visitAccessibleValue(ctx);
        assertEquals("Expression should be assigned correctly.",expectedValue.getClass(), result.getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());

    }

    public void testVisitAccessibleValueIdentifier() {
        ExprParser.AccessibleValueContext ctx = Mockito.mock(ExprParser.AccessibleValueContext.class);

        TerminalNode node = Mockito.mock(TerminalNode.class);
        when(ctx.IDENTIFIER()).thenReturn(node);

        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        IdentifierNode expectedValue = new IdentifierNode();

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);



        // Visit the while statement
        IdentifierNode result = (IdentifierNode) visitor.visitAccessibleValue(ctx);
        assertEquals("Expression should be assigned correctly.",expectedValue.getClass(), result.getClass());
        //assertSame("Expression should be assigned correctly.",expectedExpression, result.getCondition());

    }

    public void testVisitAccessibleValueError(){
        ExprParser.AccessibleValueContext ctx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        // Visit the statement
        try {

            IdentifierNode result = (IdentifierNode) visitor.visitAccessibleValue(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }


    }


    public void testVisitCardType() {

        /**
        ExprParser.CardTypeContext ctx = Mockito.mock(ExprParser.CardTypeContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        TerminalNode node2 = Mockito.mock(TerminalNode.class);

        when(ctx.STRING()).thenReturn(node);
        when(ctx.IDENTIFIER()).thenReturn(node2);
        */
        /**
        // Mock the CardTypeContext and necessary sub-contexts
        ExprParser.CardTypeContext ctx = Mockito.mock(ExprParser.CardTypeContext.class);
        TerminalNode stringNode = Mockito.mock(TerminalNode.class);
        TerminalNode identifierNode = Mockito.mock(TerminalNode.class);
        List<ExprParser.CardMethodContext> methodContexts = Arrays.asList(Mockito.mock(ExprParser.CardMethodContext.class));
        List<ExprParser.CardFieldContext> fieldContexts = Arrays.asList(Mockito.mock(ExprParser.CardFieldContext.class));

        when(ctx.STRING()).thenReturn(stringNode);
        when(stringNode.getText()).thenReturn("someStringID");
        when(ctx.IDENTIFIER()).thenReturn(identifierNode);
        when(identifierNode.getText()).thenReturn("identifierText");

        when(ctx.cardMethod()).thenReturn(methodContexts);
        when(ctx.cardField()).thenReturn(fieldContexts);

        // Mock visitor methods and their returns
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        FunctionDNode expectedMethodNode = new FunctionDNode();
        DefineNode expectedFieldNode = new DefineNode();


        when(visitor.visitType(any())).thenReturn(new TypeNode());  // Adjust TypeNode to actual type node if available
        when(visitor.visitBlock(any())).thenReturn(new BlockNode());
        when(visitor.visitFparams(any())).thenReturn(new FparamsNode()); // Adjust ParamsNode to actual parameter node if available
        when(visitor.visitCardMethod(any())).thenReturn(expectedMethodNode);
        when(visitor.visitCardField(any())).thenReturn(expectedFieldNode);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1
        when(ctx.getStart()).thenReturn(token);

        // Visit the CardType context
        CardTypeNode result = visitor.visitCardType(ctx);

        // Assertions
        assertNotNull(result);
        assertEquals("ID should be set correctly.", "someStringID", result.getID());
        assertNotNull("Identifier should not be null.", result.getIdentifier());
        assertEquals("Methods size should be as expected.", 1, result.getMethods().size());
        assertEquals("Fields size should be as expected.", 1, result.getFields().size());
        assertEquals("Method node should match expected.", expectedMethodNode, result.getMethods().get(0));
        assertEquals("Field node should match expected.", expectedFieldNode, result.getFields().get(0));

        */
    }
}