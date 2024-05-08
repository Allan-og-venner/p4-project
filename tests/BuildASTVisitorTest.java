import junit.framework.TestCase;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;
import gen.*;
import nodes.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;


import java.lang.annotation.Documented;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class BuildASTVisitorTest extends TestCase {

    public void testVisitStatementDecl(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.DeclContext declCtx = Mockito.mock(ExprParser.DeclContext.class);
        ExprParser.CommandContext commandCtx = Mockito.mock(ExprParser.CommandContext.class);
        ExprParser.ControlContext controlCtx = Mockito.mock(ExprParser.ControlContext.class);
        when(ctx.decl()).thenReturn(declCtx);
        when(ctx.command()).thenReturn(commandCtx);
        when(ctx.control()).thenReturn(controlCtx);


        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        DeclarationNode expectedDeclaration = new DefineNode();
        doReturn(expectedDeclaration).when(visitor).visitDecl(declCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        // Visit the statement
        DefineNode result = (DefineNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedDeclaration.getClass(), result.getClass());
    }
    public void testVisitStatementCall(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.CallContext callCtx = Mockito.mock(ExprParser.CallContext.class);

        when(ctx.call()).thenReturn(callCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        FunctionCallNode expectedCall = new FunctionCallNode();
        doReturn(expectedCall).when(visitor).visitCall(callCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        // Visit the statement
        FunctionCallNode result = (FunctionCallNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedCall.getClass(), result.getClass());

    }
    public void testVisitStatementMethod(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.MethodContext methodCtx = Mockito.mock(ExprParser.MethodContext.class);

        when(ctx.method()).thenReturn(methodCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        ClassAccessNode expectedMethod = new ClassAccessNode();
        doReturn(expectedMethod).when(visitor).visitMethod(methodCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ClassAccessNode result = (ClassAccessNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedMethod.getClass(), result.getClass());
    }
    public void testVisitStatementAssign(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.AssignContext assignCtx = Mockito.mock(ExprParser.AssignContext.class);

        when(ctx.assign()).thenReturn(assignCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        AssignmentNode expectedAssign = new AssignmentNode();
        doReturn(expectedAssign).when(visitor).visitAssign(assignCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        AssignmentNode result = (AssignmentNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedAssign.getClass(), result.getClass());
    }
    public void testVisitStatementControl(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.ControlContext controlCtx = Mockito.mock(ExprParser.ControlContext.class);
        when(ctx.control()).thenReturn(controlCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        IfNode expectedControl = new IfNode();
        doReturn(expectedControl).when(visitor).visitControl(controlCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        IfNode result = (IfNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedControl.getClass(), result.getClass());
    }
    public void testVisitStatementCommand(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        ExprParser.CommandContext commandCtx = Mockito.mock(ExprParser.CommandContext.class);
        when(ctx.command()).thenReturn(commandCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        ReturnNode expectedCommand = new ReturnNode();
        doReturn(expectedCommand).when(visitor).visitCommand(commandCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ReturnNode result = (ReturnNode) visitor.visitStatement(ctx);

        assertNotNull(result);
        assertEquals(expectedCommand.getClass(), result.getClass());
    }
    public void testVisitStatementError(){
        ExprParser.StatementContext ctx = Mockito.mock(ExprParser.StatementContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        // Visit the statement
        try {

            DefineNode result = (DefineNode) visitor.visitStatement(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (statement node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }


    }
    public void testVisitControlLoop() {
        ExprParser.ControlContext ctx = Mockito.mock(ExprParser.ControlContext.class);
        ExprParser.LoopContext loopCtx = Mockito.mock(ExprParser.LoopContext.class);

        when(ctx.loop()).thenReturn(loopCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        ForNode expectedLoop = new ForNode();
        doReturn(expectedLoop).when(visitor).visitLoop(loopCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ForNode result = (ForNode) visitor.visitControl(ctx);

        assertNotNull(result);
        assertEquals(expectedLoop.getClass(), result.getClass());


    }

    public void testVisitIfthen() {

    }

    //Tests whether expressionNode and blockNode will be set
    public void testVisitLoopWhile() throws Exception {
        //Mocking all inputs for the method
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
    }

    public void testVisitAccess() {
    }

    public void testVisitArrayAccess() {
    }

    public void testVisitAccessibleValue() {
    }

    public void testVisitCardType() {
    }
}