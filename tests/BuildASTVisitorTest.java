import junit.framework.TestCase;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.mockito.Mockito;
import gen.*;
import nodes.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;


import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
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

            visitor.visitStatement(ctx);

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
    public void testVisitControlIfthen() {
        ExprParser.ControlContext ctx = Mockito.mock(ExprParser.ControlContext.class);
        ExprParser.IfthenContext IfthenCtx = Mockito.mock(ExprParser.IfthenContext.class);

        when(ctx.ifthen()).thenReturn(IfthenCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        IfNode expectedIf = new IfNode();
        doReturn(expectedIf).when(visitor).visitIfthen(IfthenCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        IfNode result = (IfNode) visitor.visitControl(ctx);

        assertNotNull(result);
        assertEquals(expectedIf.getClass(), result.getClass());

    }
    public void testVisitControlError() {
        ExprParser.ControlContext ctx = Mockito.mock(ExprParser.ControlContext.class);


        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {
            visitor.visitControl(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (control node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }


    }

    public void testVisitIfthen() {
        ExprParser.IfthenContext ctx = Mockito.mock(ExprParser.IfthenContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        when(ctx.expr()).thenReturn(exprCtx);
        when(ctx.block()).thenReturn(blockCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        IfNode expectedIf = new IfNode();
        LessThanNode expectedExpr = new LessThanNode();
        BlockNode expectedBlock = new BlockNode();
        doReturn(expectedExpr).when(visitor).visitExpr(exprCtx);
        doReturn(expectedBlock).when(visitor).visitBlock(blockCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        IfNode result = visitor.visitIfthen(ctx);

        assertNotNull(result);
        assertEquals(expectedIf.getClass(), result.getClass());
    }
    public void testVisitIfthenError() {
        ExprParser.IfthenContext ctx = Mockito.mock(ExprParser.IfthenContext.class);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            visitor.visitIfthen(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (if-then node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }

    //Tests whether expressionNode and blockNode will be set
    public void testVisitLoopWhile() {
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
    public void testVisitLoopFor() {
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
    public void testVisitLoopError() {
        ExprParser.LoopContext ctx = Mockito.mock(ExprParser.LoopContext.class);

        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        // Assertions
        try {

            visitor.visitLoop(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (loop node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }



    public void testVisitCommand() {
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

    public void testVisitCdeclIdentifier() {
        ExprParser.CdeclContext ctx = Mockito.mock(ExprParser.CdeclContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ClassDNode expectedNode = Mockito.mock(ClassDNode.class);

        when(ctx.IDENTIFIER(0)).thenReturn(node);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        ClassDNode result = (ClassDNode) visitor.visitCdecl(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(), result.getClass());
    }

    public void testVisitCdeclExtends() {
        ExprParser.CdeclContext ctx = Mockito.mock(ExprParser.CdeclContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ClassDNode expectedNode = Mockito.mock(ClassDNode.class);

        when(ctx.block()).thenReturn(blockCtx);
        when(ctx.IDENTIFIER(0)).thenReturn(node);
        when(ctx.IDENTIFIER(1)).thenReturn(node);
        when(ctx.KEY_EXTENDS()).thenReturn(node);

        doReturn(expressionNode).when(visitor).visitBlock(blockCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        ClassDNode result = (ClassDNode) visitor.visitCdecl(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());

    }

    public void testVisitExprAND() {
        ExprParser.ExprContext ctx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.ExprContext ctx1 = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.RelationContext relationCtx = Mockito.mock(ExprParser.RelationContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ANDNode expectedNode = Mockito.mock(ANDNode.class);

        when(ctx.expr()).thenReturn(ctx1);
        when(ctx.relation()).thenReturn(relationCtx);
        when(ctx.AND()).thenReturn(node);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitExpr(ctx1);
        doReturn(expressionNode).when(visitor).visitRelation(relationCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitExpr(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitExprOR() {
        ExprParser.ExprContext ctx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.ExprContext ctx1 = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.RelationContext relationCtx = Mockito.mock(ExprParser.RelationContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ORNode expectedNode = Mockito.mock(ORNode.class);

        when(ctx.expr()).thenReturn(ctx1);
        when(ctx.relation()).thenReturn(relationCtx);
        when(ctx.OR()).thenReturn(node);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitExpr(ctx1);
        doReturn(expressionNode).when(visitor).visitRelation(relationCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitExpr(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitExprRelation() {
        ExprParser.ExprContext ctx = Mockito.mock(ExprParser.ExprContext.class);
        ExprParser.RelationContext relationCtx = Mockito.mock(ExprParser.RelationContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);

        when(ctx.relation()).thenReturn(relationCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitRelation(relationCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitExpr(ctx);


        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitExprError() {
        ExprParser.ExprContext ctx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ExpressionNode result = (ExpressionNode) visitor.visitExpr(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (expression node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    public void testVisitArithPlus() {
        ExprParser.ArithContext ctx = Mockito.mock(ExprParser.ArithContext.class);
        ExprParser.ArithContext ctx1 = Mockito.mock(ExprParser.ArithContext.class);
        ExprParser.TermContext termCtx = Mockito.mock(ExprParser.TermContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        AdditionNode expectedNode = Mockito.mock(AdditionNode.class);

        when(ctx.PLUS()).thenReturn(node);
        when(ctx.arith()).thenReturn(ctx1);
        when(ctx.term()).thenReturn(termCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitArith(ctx1);
        doReturn(expressionNode).when(visitor).visitTerm(termCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitArith(ctx);


        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitArithMinus() {
        ExprParser.ArithContext ctx = Mockito.mock(ExprParser.ArithContext.class);
        ExprParser.ArithContext ctx1 = Mockito.mock(ExprParser.ArithContext.class);
        ExprParser.TermContext termCtx = Mockito.mock(ExprParser.TermContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        SubtractionNode expectedNode = Mockito.mock(SubtractionNode.class);

        when(ctx.MINUS()).thenReturn(node);
        when(ctx.arith()).thenReturn(ctx1);
        when(ctx.term()).thenReturn(termCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitArith(ctx1);
        doReturn(expressionNode).when(visitor).visitTerm(termCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitArith(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitArithTerm() {
        ExprParser.ArithContext ctx = Mockito.mock(ExprParser.ArithContext.class);
        ExprParser.TermContext termCtx = Mockito.mock(ExprParser.TermContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);

        when(ctx.term()).thenReturn(termCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitTerm(termCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitArith(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitArithError() {
        ExprParser.ArithContext ctx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ExpressionNode result = (ExpressionNode) visitor.visitArith(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (arithmetic node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }


    public void testVisitRelationLT() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        LessThanNode expectedNode = Mockito.mock(LessThanNode.class);

        when(ctx.LT()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationLTEQ() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        LTEQNode expectedNode = Mockito.mock(LTEQNode.class);

        when(ctx.LTEQ()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationEQEQ() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        EQEQNode expectedNode = Mockito.mock(EQEQNode.class);

        when(ctx.EQEQ()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationGT() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        GreaterThanNode expectedNode = Mockito.mock(GreaterThanNode.class);

        when(ctx.GT()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationGTEQ() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        GTEQNode expectedNode = Mockito.mock(GTEQNode.class);

        when(ctx.GTEQ()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationNOTEQ() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.RelationContext ctx1 = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        NOTEQNode expectedNode = Mockito.mock(NOTEQNode.class);

        when(ctx.NOTEQ()).thenReturn(node);
        when(ctx.relation()).thenReturn(ctx1);
        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitRelation(ctx1);
        doReturn(expressionNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationArith() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        ExprParser.ArithContext arithCtx = Mockito.mock(ExprParser.ArithContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);


        when(ctx.arith()).thenReturn(arithCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitArith(arithCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitRelationError() {
        ExprParser.RelationContext ctx = Mockito.mock(ExprParser.RelationContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());



        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ExpressionNode result = (ExpressionNode) visitor.visitRelation(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (relation node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }


    }

    public void testVisitTermMult() {
        ExprParser.TermContext ctx = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.TermContext ctx1 = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.FactorContext factorCtx = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());


        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        MultiplicationNode expectedNode = Mockito.mock(MultiplicationNode.class);

        when(ctx.MULT()).thenReturn(node);
        when(ctx.factor()).thenReturn(factorCtx);
        when(ctx.term()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitTerm(ctx1);
        doReturn(expressionNode).when(visitor).visitFactor(factorCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitTerm(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitTermDiv() {
        ExprParser.TermContext ctx = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.TermContext ctx1 = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.FactorContext factorCtx = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());


        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        DivisionNode expectedNode = Mockito.mock(DivisionNode.class);

        when(ctx.DIV()).thenReturn(node);
        when(ctx.factor()).thenReturn(factorCtx);
        when(ctx.term()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitTerm(ctx1);
        doReturn(expressionNode).when(visitor).visitFactor(factorCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitTerm(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitTermMod() {
        ExprParser.TermContext ctx = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.TermContext ctx1 = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.FactorContext factorCtx = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());


        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ModNode expectedNode = Mockito.mock(ModNode.class);

        when(ctx.MOD()).thenReturn(node);
        when(ctx.factor()).thenReturn(factorCtx);
        when(ctx.term()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitTerm(ctx1);
        doReturn(expressionNode).when(visitor).visitFactor(factorCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitTerm(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitTermFactor() {
        ExprParser.TermContext ctx = Mockito.mock(ExprParser.TermContext.class);
        ExprParser.FactorContext factorCtx = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);
        when(ctx.factor()).thenReturn(factorCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitFactor(factorCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitTerm(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitTermError() {
        ExprParser.TermContext ctx = Mockito.mock(ExprParser.TermContext.class);
        BuildASTVisitor visitor = spy(new BuildASTVisitor());


        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ExpressionNode result = (ExpressionNode) visitor.visitTerm(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (term node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    public void testVisitFactorParen() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);

        when(ctx.L_PAREN()).thenReturn(node);
        when(ctx.R_PAREN()).thenReturn(node);
        when(ctx.expr()).thenReturn(exprCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitExpr(exprCtx);
        ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitFactorPlus() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        ExprParser.FactorContext ctx1 = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionNode expectedNode = Mockito.mock(ExpressionNode.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);

        when(ctx.PLUS()).thenReturn(node);
        when(ctx.factor()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitFactor(ctx1);
        ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitFactorMinus() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        ExprParser.FactorContext ctx1 = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        NegativeNode expectedNode = Mockito.mock(NegativeNode.class);

        when(ctx.MINUS()).thenReturn(node);
        when(ctx.factor()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitFactor(ctx1);
        ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitFactorNot() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        ExprParser.FactorContext ctx1 = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        TerminalNode node = Mockito.mock(TerminalNode.class);
        NegateNode expectedNode = Mockito.mock(NegateNode.class);

        when(ctx.NOT()).thenReturn(node);
        when(ctx.factor()).thenReturn(ctx1);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionNode).when(visitor).visitFactor(ctx1);
        ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitFactorValue() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        ExprParser.ValueContext valueContext = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ValueNode expectedNode = Mockito.mock(ValueNode.class);

        when(ctx.value()).thenReturn(valueContext);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expectedNode).when(visitor).visitValue(valueContext);
        ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitFactorError() {
        ExprParser.FactorContext ctx = Mockito.mock(ExprParser.FactorContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());



        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        try {

            ExpressionNode result = (ExpressionNode) visitor.visitFactor(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (factor node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    public void testVisitCall() {
        ExprParser.CallContext ctx = Mockito.mock(ExprParser.CallContext.class);
        ExprParser.ExprsContext exprsContext = Mockito.mock(ExprParser.ExprsContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionsNode expressionsNode = Mockito.mock(ExpressionsNode.class);
        FunctionCallNode expectedNode = Mockito.mock(FunctionCallNode.class);

        when(ctx.IDENTIFIER()).thenReturn(node);
        when(ctx.exprs()).thenReturn(exprsContext);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        doReturn(expressionsNode).when(visitor).visitExprs(exprsContext);
        ValueNode result = (ValueNode) visitor.visitCall(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitCallError() {
        ExprParser.CallContext ctx = Mockito.mock(ExprParser.CallContext.class);
        ExprParser.ExprsContext exprsContext = Mockito.mock(ExprParser.ExprsContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ValueNode result = (ValueNode) visitor.visitCall(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (call node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    public void testVisitExprs() {
        ExprParser.ExprsContext ctx = Mockito.mock(ExprParser.ExprsContext.class);
        ExprParser.ExprsContext ctx1 = Mockito.mock(ExprParser.ExprsContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionsNode expectedNode = Mockito.mock(ExpressionsNode.class);

        when(ctx.exprs()).thenReturn(ctx1);
        when(ctx.expr()).thenReturn(exprCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ExpressionsNode expressionsNode = Mockito.mock(ExpressionsNode.class);

        doReturn(expressionsNode).when(visitor).visitExprs(ctx1);
        doReturn(expressionNode).when(visitor).visitExpr(exprCtx);
        ExpressionsNode result = (ExpressionsNode) visitor.visitExprs(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitExprsNull() {
        ExprParser.ExprsContext ctx = Mockito.mock(ExprParser.ExprsContext.class);
        ExprParser.ExprContext exprCtx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionsNode expectedNode = Mockito.mock(ExpressionsNode.class);

        when(ctx.expr()).thenReturn(exprCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        doReturn(expressionNode).when(visitor).visitExpr(exprCtx);
        ExpressionsNode result = (ExpressionsNode) visitor.visitExprs(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValue() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        ExprParser.CallContext callCtx = Mockito.mock(ExprParser.CallContext.class);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        when(ctx.call()).thenReturn(callCtx);

        ValueNode expectedNode = Mockito.mock(ValueNode.class);

        doReturn(expectedNode).when(visitor).visitCall(callCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ValueNode result = (ValueNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValueArray() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        ExprParser.ArrayContext arrayContextCtx = Mockito.mock(ExprParser.ArrayContext.class);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        when(ctx.array()).thenReturn(arrayContextCtx);

        ArrayNode expectedNode = Mockito.mock(ArrayNode.class);

        doReturn(expectedNode).when(visitor).visitArray(arrayContextCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ArrayNode result = (ArrayNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValueAccess() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        ExprParser.AccessContext accessContextCtx = Mockito.mock(ExprParser.AccessContext.class);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        when(ctx.access()).thenReturn(accessContextCtx);

        ValueNode expectedNode = Mockito.mock(ValueNode.class);

        doReturn(expectedNode).when(visitor).visitAccess(accessContextCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ValueNode result = (ValueNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValueNumeral() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        NumberNode expectedNode = Mockito.mock(NumberNode.class);

        when(ctx.NUMERAL()).thenReturn(node);
        when(ctx.NUMERAL().getText()).thenReturn("0");

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        NumberNode result = (NumberNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }


    public void testVisitValueFloat() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        FloatNode expectedNode = Mockito.mock(FloatNode.class);

        when(ctx.FLOAT()).thenReturn(node);
        when(ctx.FLOAT().getText()).thenReturn("2.0");

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        FloatNode result = (FloatNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }


    public void testVisitValueString() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        StringNode expectedNode = Mockito.mock(StringNode.class);

        when(ctx.STRING()).thenReturn(node);
        when(ctx.STRING().getText()).thenReturn("Test String");

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        StringNode result = (StringNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }


    public void testVisitValueChar() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        CharNode expectedNode = Mockito.mock(CharNode.class);

        when(ctx.CHAR()).thenReturn(node);
        when(ctx.CHAR().getText()).thenReturn("T");

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        CharNode result = (CharNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValueIdentifier() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        TerminalNode node = Mockito.mock(TerminalNode.class);
        IdentifierNode expectedNode = Mockito.mock(IdentifierNode.class);

        when(ctx.IDENTIFIER()).thenReturn(node);
        when(ctx.IDENTIFIER().getText()).thenReturn("Variable2");

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        IdentifierNode result = (IdentifierNode) visitor.visitValue(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitValueError() {
        ExprParser.ValueContext ctx = Mockito.mock(ExprParser.ValueContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        try {

            ValueNode result = (ValueNode) visitor.visitValue(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (value node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }

    public void testVisitArrayExpr() {
        ExprParser.ArrayContext ctx = Mockito.mock(ExprParser.ArrayContext.class);
        ExprParser.ExprsContext exprCtx = Mockito.mock(ExprParser.ExprsContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ExpressionsNode node = Mockito.mock(ExpressionsNode.class);
        ArrayNode expectedNode = Mockito.mock(ArrayNode.class);

        when(ctx.exprs()).thenReturn(exprCtx);

        doReturn(node).when(visitor).visitExprs(exprCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ArrayNode result = (ArrayNode) visitor.visitArray(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitArrayInnerNull() {
        ExprParser.ArrayContext ctx = Mockito.mock(ExprParser.ArrayContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ArrayNode expectedNode = Mockito.mock(ArrayNode.class);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        ArrayNode result = (ArrayNode) visitor.visitArray(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitMethod() {
        ExprParser.MethodContext ctx = Mockito.mock(ExprParser.MethodContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.AccessibleValueContext accessibleValueCtx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        ExprParser.CallContext callCtx = Mockito.mock(ExprParser.CallContext.class);


        List<ExprParser.AccessibleValueContext> accessibleValueCtxs = Arrays.asList(accessibleValueCtx);

        when(ctx.accessibleValue()).thenReturn(accessibleValueCtxs);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);
        when(ctx.call()).thenReturn(callCtx);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());


        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        ClassAccessNode expectedNode = Mockito.mock(ClassAccessNode.class);
        NumberNode numberNode = Mockito.mock(NumberNode.class);

        doReturn(expectedNode).when(visitor).visitAccessibleObject(accessibleObjectCtx);
        doReturn(numberNode).when(visitor).visitCall(callCtx);
        doReturn(numberNode).when(visitor).visitAccessibleValue(accessibleValueCtx);

        ClassAccessNode result = (ClassAccessNode) visitor.visitMethod(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitAccessibleObjectCall() {
        ExprParser.AccessibleObjectContext ctx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.CallContext callCtx = Mockito.mock(ExprParser.CallContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);


        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        ValueNode expectedNode = new ValueNode() {};
        when(ctx.call()).thenReturn(callCtx);
        doReturn(expectedNode).when(visitor).visitCall(callCtx);

        ValueNode result = (ValueNode) visitor.visitAccessibleObject(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitAccessibleObjectArray() {
        ExprParser.AccessibleObjectContext ctx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.ArrayAccessContext ArrayAccessctx = Mockito.mock(ExprParser.ArrayAccessContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        ArrayAccessNode expectedNode = new ArrayAccessNode();
        when(ctx.arrayAccess()).thenReturn(ArrayAccessctx);
        doReturn(expectedNode).when(visitor).visitArrayAccess(ArrayAccessctx);

        ValueNode result = (ValueNode) visitor.visitAccessibleObject(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitAccessibleObjectIdentifier() {
        ExprParser.AccessibleObjectContext ctx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.ArrayAccessContext ArrayAccessctx = Mockito.mock(ExprParser.ArrayAccessContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        IdentifierNode expectedNode = Mockito.mock(IdentifierNode.class);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        TerminalNode terminalNode = Mockito.mock(TerminalNode.class);
        when(ctx.IDENTIFIER()).thenReturn(terminalNode);

        ValueNode result = (ValueNode) visitor.visitAccessibleObject(ctx);

        assertNotNull(result);
        assertEquals(expectedNode.getClass(),result.getClass());
    }

    public void testVisitAccessibleObjectError() {
        ExprParser.AccessibleObjectContext ctx = Mockito.mock(ExprParser.AccessibleObjectContext.class);

        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        try {

            ValueNode result = (ValueNode) visitor.visitAccessibleObject(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (accessibleObject node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
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

    public void testVisitAccessClassArrayCase1() {

        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessingContext accessingCtx2 = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessibleValueContext accessibleValueCtx = Mockito.mock(ExprParser.AccessibleValueContext.class);
        ExprParser.ExprContext ExprCtx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        when(accessingCtx.accessibleValue()).thenReturn(accessibleValueCtx);

        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        FunctionCallNode expectedAccessibleValue = Mockito.mock(FunctionCallNode.class);
        ClassAccessNode expectedArrayAccess = Mockito.mock(ClassAccessNode.class);
        ClassAccessNode expectedArrayAccessSpy = Mockito.spy(ClassAccessNode.class);
        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx,accessingCtx2);
        ValueNode node1 = Mockito.mock(NumberNode.class);
        List<ValueNode> valueNodeList = Arrays.asList(node1);


        when(accessingCtx.PERIOD()).thenReturn(node);
        when(accessingCtx2.PERIOD()).thenReturn(null);
        when(accessingCtx2.L_BRACKET()).thenReturn(node);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);
        when(accessingCtx.expr()).thenReturn(ExprCtx);
        when(accessingCtx2.expr()).thenReturn(ExprCtx);
        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(ctx.accessing(1)).thenReturn(accessingCtx2);
        when(accessingCtx.expr()).thenReturn(ExprCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);

        doReturn(valueNodeList).when(expectedArrayAccessSpy).getValue();
        
        doReturn(expectedAccessibleValue).when(visitor).visitAccessibleValue(accessibleValueCtx);
        doReturn(expectedArrayAccess).when(visitor).visitAccessibleObject(accessibleObjectCtx);
        doReturn(expressionNode).when(visitor).visitExpr(ExprCtx);


        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        ValueNode result = (ValueNode) visitor.visitAccess(ctx);
        assertNotNull(result);
        assertEquals(expectedArrayAccess.getClass(), result.getClass());

    }
    public void testVisitAccessClassArray() {

        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessingContext accessingCtx2 = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.ExprContext ExprCtx = Mockito.mock(ExprParser.ExprContext.class);
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());


        TerminalNode node = Mockito.mock(TerminalNode.class);
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        ClassAccessNode expectedArrayAccess = Mockito.mock(ClassAccessNode.class);
        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx,accessingCtx2);
        ValueNode node1 = Mockito.mock(NumberNode.class);
        ValueNode node2 = Mockito.mock(NumberNode.class);
        List<ValueNode> valueNodeList = Arrays.asList(node1);


        when(accessingCtx.L_BRACKET()).thenReturn(node);
        when(accessingCtx2.L_BRACKET()).thenReturn(node);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);
        when(accessingCtx.expr()).thenReturn(ExprCtx);
        when(accessingCtx2.expr()).thenReturn(ExprCtx);
        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(ctx.accessing(1)).thenReturn(accessingCtx2);
        when(accessingCtx.expr()).thenReturn(ExprCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);
        when(expectedArrayAccess.getValue()).thenReturn(valueNodeList);

        doReturn(expectedArrayAccess).when(visitor).visitAccessibleObject(accessibleObjectCtx);
        doReturn(expressionNode).when(visitor).visitExpr(ExprCtx);


        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        ValueNode result = (ValueNode) visitor.visitAccess(ctx);
        assertNotNull(result);
        assertEquals(expectedArrayAccess.getClass(), result.getClass());

    }

    public void testVisitAccessError() {
        ExprParser.AccessContext ctx = Mockito.mock(ExprParser.AccessContext.class);
        ExprParser.AccessingContext accessingCtx = Mockito.mock(ExprParser.AccessingContext.class);
        ExprParser.AccessibleObjectContext accessibleObjectCtx = Mockito.mock(ExprParser.AccessibleObjectContext.class);

        List<ExprParser.AccessingContext> accessingCtxs = Arrays.asList(accessingCtx);


        when(ctx.accessing(0)).thenReturn(accessingCtx);
        when(ctx.accessing()).thenReturn(accessingCtxs);
        when(ctx.accessibleObject()).thenReturn(accessibleObjectCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);



        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());

        ClassAccessNode expectedArrayAccess = new ClassAccessNode();
        doReturn(expectedArrayAccess).when(visitor).visitAccessibleObject(accessibleObjectCtx);

        // Visit the statement
        try {

            ClassAccessNode result = (ClassAccessNode) visitor.visitAccess(ctx);

            fail("Should have thrown exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("1 Operation not supported (access node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
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



        // Mock visitor methods
        BuildASTVisitor visitor = Mockito.spy(new BuildASTVisitor());
        ValueNode expectedValue = new ValueNode() {};

        when(ctx.call()).thenReturn(callCtx);
        doReturn(expectedValue).when(visitor).visitCall(callCtx);
        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);


        // Visit the while statement

        ValueNode result = (ValueNode) visitor.visitAccessibleValue(ctx);
        assertNotNull(result);
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
        assertNotNull(result);
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
            assertThat(e.getMessage(), is("1 Operation not supported (accessibleValue node)"));
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }


    }


    public void testVisitCardType() {
        ExprParser.CardTypeContext ctx = Mockito.mock(ExprParser.CardTypeContext.class);
        ExprParser.CardMethodContext cardMethodCtx = Mockito.mock(ExprParser.CardMethodContext.class);
        ExprParser.TypeContext typeCtx = Mockito.mock(ExprParser.TypeContext.class);
        ExprParser.BlockContext blockCtx = Mockito.mock(ExprParser.BlockContext.class);
        ExprParser.FparamsContext fparamsCtx = Mockito.mock(ExprParser.FparamsContext.class);
        ExprParser.CardFieldContext cardFieldCtx = Mockito.mock(ExprParser.CardFieldContext.class);


        TerminalNode node = Mockito.mock(TerminalNode.class);
        BlockNode blockNode = new BlockNode();
        TypeNode typeNode = Mockito.mock(TypeNode.class);
        FparamsNode fparamsNode = Mockito.mock(FparamsNode.class);
        CardTypeNode expectedCardType = new CardTypeNode();

        when(ctx.STRING()).thenReturn(node);
        when(ctx.STRING().getText()).thenReturn("Something");
        when(ctx.IDENTIFIER()).thenReturn(node);


        List<ExprParser.CardMethodContext> cardMethodContexts = Arrays.asList(cardMethodCtx);
        when(ctx.cardMethod()).thenReturn(cardMethodContexts);
        when(ctx.cardMethod(0)).thenReturn(cardMethodCtx);
        when(ctx.cardMethod(0).IDENTIFIER()).thenReturn(node);
        when(ctx.cardMethod(0).type()).thenReturn(typeCtx);
        when(ctx.cardMethod(0).block()).thenReturn(blockCtx);
        when(ctx.cardMethod(0).fparams()).thenReturn(fparamsCtx);



        List<ExprParser.CardFieldContext> cardFieldContexts = Arrays.asList(cardFieldCtx);
        when(ctx.cardField()).thenReturn(cardFieldContexts);
        when(ctx.cardField(0)).thenReturn(cardFieldCtx);
        when(ctx.cardField(0).IDENTIFIER()).thenReturn(node);
        when(ctx.cardField(0).type()).thenReturn(typeCtx);

        BuildASTVisitor visitor = spy(new BuildASTVisitor());

        doReturn(typeNode).when(visitor).visitType(typeCtx);
        doReturn(blockNode).when(visitor).visitBlock(blockCtx);
        doReturn(fparamsNode).when(visitor).visitFparams(fparamsCtx);

        // Mock Token and ParserRuleContext for getLine()
        Token token = Mockito.mock(Token.class);
        when(token.getLine()).thenReturn(1);  // Return line number 1

        // Ensure getStart() returns the mocked token
        when(ctx.getStart()).thenReturn(token);

        CardTypeNode result = visitor.visitCardType(ctx);

        assertNotNull(result);
        assertEquals(expectedCardType.getClass(),result.getClass());
        //assertEquals(expec);

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