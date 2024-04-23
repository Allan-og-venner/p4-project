import java.util.ArrayList;
import java.util.List;

import codegenExample.Card;
import gen.*;
import nodes.*;
public class BuildASTVisitor extends ExprBaseVisitor<BlockNode> {

    //Save the visited nodes in a list to use for testing purposes
    private static List<String> visitedNodes = new ArrayList<>();
    //Method to add nodes to the list
    public void addVisitedNode(String node) {
        visitedNodes.add(node);
    }
    //Getter for the list
    public static List<String> getVisitedNodes() {
        return visitedNodes;
    }
    //Print all nodes for debugging
    public static void printVisitedNodes() {
        for (String node : visitedNodes) {
            System.out.println(node);
        }
    }


    @Override
    public BlockNode visitProg(ExprParser.ProgContext context) {
        addVisitedNode("Visited prog node");
        return visitBlock(context.block());
    }

    @Override
    public BlockNode visitBlock(ExprParser.BlockContext context) {
        addVisitedNode("Visited block node");
        BlockNode node = new BlockNode();
        node.getLineNumberFromContext(context);
        //System.out.println(context.statement() != null);
        if (context.statement() != null) {
            node.setStatement(visitStatement(context.statement()));
            if (context.block() != null) {
                node.setBlocks(visitBlock(context.block()));
            }
        } else {
            throw new UnsupportedOperationException("Operation not supported (block node)");
        }
        return node;
    }

    @Override
    public StatementNode visitStatement(ExprParser.StatementContext context) {
        addVisitedNode("Visited statement node");
        if (context.decl() != null) {
            DeclarationNode node = visitDecl(context.decl());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.expr() != null) {
            ExpressionNode node = visitExpr(context.expr());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.assign() != null) {
            AssignmentNode node = visitAssign(context.assign());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.command() != null) {
            CommandNode node = visitCommand(context.command());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.control() != null) {
            ControlNode node = visitControl(context.control());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported (statement node)");
        }
    }

    @Override
    public ControlNode visitControl(ExprParser.ControlContext context) {
        addVisitedNode("Visited control node");
        if (context.loop() != null) {
            LoopNode node = visitLoop(context.loop());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.ifthen() != null) {
            IfNode node = visitIfthen(context.ifthen());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported (control node)");
        }

    }

    @Override
    public IfNode visitIfthen(ExprParser.IfthenContext context) {
        addVisitedNode("Visited if-then node");
        IfNode node = new IfNode();
        if (context.expr() != null) {
            node.setCondition(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public LoopNode visitLoop(ExprParser.LoopContext context) {
        addVisitedNode("Visited loop node");
        if(context.KEY_WHILE() != null) {
            WhileNode node = new WhileNode();
            node.setCondition(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.KEY_FOR() != null) {
            ForNode node = new ForNode();
            IdentifierNode iNode = new IdentifierNode();
            iNode.setText(context.IDENTIFIER().getText());
            node.setIterator(iNode);
            node.setArray(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public CommandNode visitCommand(ExprParser.CommandContext context) {
        addVisitedNode("Visited command node");

        if (context.KEY_RETURN() != null) {
            ReturnNode node = new ReturnNode();
            node.setInnerNode(visitExpr(context.expr()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.KEY_BREAK() != null) {
            BreakNode node = new BreakNode();
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.KEY_CONTINUE() != null) {
            ContinueNode node = new ContinueNode();
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public AssignmentNode visitAssign(ExprParser.AssignContext context) {
        addVisitedNode("Visited assign node");
        AssignmentNode node = new AssignmentNode();
        if (context.value() != null) {
            node.setLeft(visitValue(context.value()));
            if (context.expr() !=null){
                node.setRight(visitExpr(context.expr()));
            }
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public DeclarationNode visitDecl(ExprParser.DeclContext context) {
        addVisitedNode("Visited declaration node");
        if (context.defin() != null){
            DefineNode node = visitDefin(context.defin());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.fdecl() != null) {
            FunctionDNode node = visitFdecl(context.fdecl());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.cdecl() != null) {
            ClassDNode node = visitCdecl(context.cdecl());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.cardType() != null) {
            CardTypeNode node = visitCardType(context.cardType());
            node.getLineNumberFromContext(context);
            return node;
        }else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ModifierNode visitModifier(ExprParser.ModifierContext context) {
        addVisitedNode("Visited modifier node");
        ModifierNode node = new ModifierNode();
        if (context.KEY_STATIC() != null) {
            node.setModifier(context.KEY_STATIC().getText());

        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public DefineNode visitDefin(ExprParser.DefinContext context) {
        addVisitedNode("Visited define node");
        DefineNode node = new DefineNode();
        node.setModi(visitModifier(context.modifier()));
        if (context.type() != null && context.IDENTIFIER() != null) {
            node.setType(visitType(context.type()));
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setID(text);
            if (context.NUMERAL() != null){
                NumberNode index = new NumberNode();
                index.setValue(Double.parseDouble(context.NUMERAL().getText()));
                node.setIndex(index);
            }
            if (context.expr() != null){
                node.setValue(visitExpr(context.expr()));
            }
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        node.getLineNumberFromContext(context);
        return node;

    }

    @Override
    public TypeNode visitType(ExprParser.TypeContext context) {
        addVisitedNode("Visited type node");
        TypeNode node = new TypeNode();
        if (context.TYPE_INT() != null){
            node.setTypeName(context.TYPE_INT().getText());
        } else if (context.TYPE_FLOAT() != null){
            node.setTypeName(context.TYPE_FLOAT().getText());
        } else if (context.TYPE_STRING() != null){
            node.setTypeName(context.TYPE_STRING().getText());
        } else if (context.TYPE_CHAR() != null) {
            node.setTypeName(context.TYPE_CHAR().getText());
        } else if (context.TYPE_VOID() != null) {
            node.setTypeName(context.TYPE_VOID().getText());
        } else if (context.IDENTIFIER() != null) {
            node.setTypeName(context.IDENTIFIER().getText());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public FunctionDNode visitFdecl(ExprParser.FdeclContext context) {
        addVisitedNode("Visited functionDeclaration node");
        FunctionDNode node = new FunctionDNode();
        node.setIsAction(context.KEY_ACTION() != null);
        node.setModifier(visitModifier(context.modifier()));
        if (context.type() != null && context.IDENTIFIER() != null) {
            node.setReturnType(visitType(context.type()));
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setFunction(text);
            if (context.fparams() != null) {
                node.setParameter(visitFparams(context.fparams()));
            }
            if (context.block() != null) {
                node.setBlocks(visitBlock(context.block()));
            }
            } else {
                throw new UnsupportedOperationException("Operation not supported");
            }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public FparamsNode visitFparams(ExprParser.FparamsContext context) {
        addVisitedNode("Visited functionParameters node");
        FparamsNode node = new FparamsNode();
        if (context.fparam() != null) {
            node.setLeft(visitFparam(context.fparam()));
            if (context.fparams() != null) {
                node.setRight(visitFparams(context.fparams()));
            }
        }
         else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public FparamNode visitFparam(ExprParser.FparamContext context) {
        addVisitedNode("Visited functionParam node");
        FparamNode node = new FparamNode();
        if (context.type() != null && context.IDENTIFIER() != null) {
            node.setType(visitType(context.type()));
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setIdentifier(text);
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ClassDNode visitCdecl(ExprParser.CdeclContext context) {
        addVisitedNode("Visited classDeclaration node");

        if (context.IDENTIFIER(0) != null) {
            ClassDNode node = new ClassDNode();
            node.getLineNumberFromContext(context);
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER(0).getText());
            node.setName(text);
            if (context.block() != null) {
            node.setBlocks(visitBlock(context.block()));
            }
            if (context.KEY_EXTENDS() != null) { // If there is an 'extends'
                IdentifierNode text1 = new IdentifierNode();
                text1.setText(context.IDENTIFIER(1).getText());
                node.setSuperClass(text1);
            }
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitExpr(ExprParser.ExprContext context) {
        addVisitedNode("Visited expr node");
        if (context.AND() != null) {
            InfixExpressionNode node;
            node = new ANDNode();
            node.setLeft(visitExpr(context.expr()));
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.OR() != null) {
            InfixExpressionNode node;
            node = new ORNode();
            node.setLeft(visitExpr(context.expr()));
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.relation() != null) {
            ExpressionNode node = visitRelation(context.relation());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitArith(ExprParser.ArithContext context) {
        addVisitedNode("Visited Arithmetic node");
        if (context.PLUS() != null) {
            InfixExpressionNode node;
            node = new AdditionNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.MINUS() != null) {
            InfixExpressionNode node;
            node = new SubtractionNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.term() != null) {
            ExpressionNode node = visitTerm(context.term());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitRelation(ExprParser.RelationContext context) {
        addVisitedNode("Visited relation node");
        if (context.LT() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.LTEQ() != null) {
            InfixExpressionNode node;
            node = new LTEQNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.EQEQ() != null) {
            InfixExpressionNode node;
            node = new EQEQNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.GT() != null) {
            InfixExpressionNode node;
            node = new GreaterThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.GTEQ() != null) {
            InfixExpressionNode node;
            node = new GTEQNode();
            node.setLeft(visitRelation(context.relation()));node.setRight(visitArith(context.arith()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.NOTEQ() != null) {
            InfixExpressionNode node;
            node = new NOTEQNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitRelation(context.relation()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.arith() != null) {
            return visitArith(context.arith());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitTerm(ExprParser.TermContext context) {
        addVisitedNode("Visited term node");
        if (context.MULT() != null) {
            InfixExpressionNode node = new MultiplicationNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.DIV() != null) {
            InfixExpressionNode node = new DivisionNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.MOD() != null) {
            InfixExpressionNode node = new ModNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.factor() != null) {
            ExpressionNode node = visitFactor(context.factor());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitFactor(ExprParser.FactorContext context) {
        addVisitedNode("Visited factor node");
        if (context.L_PAREN() != null && context.R_PAREN() != null) {
            ExpressionNode node = visitExpr(context.expr());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.PLUS() != null) {
            return visitFactor(context.factor());
        } else if (context.MINUS() != null) {
            NegativeNode node = new NegativeNode();
            node.setInnerNode(visitFactor(context.factor()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.NOT() != null) {
            NegateNode node = new NegateNode();
            node.setInnerNode(visitFactor(context.factor()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.value() != null) {
            ValueNode node = visitValue(context.value());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public ValueNode visitCall(ExprParser.CallContext context) {
        addVisitedNode("Visited call node");
        FunctionCallNode node = new FunctionCallNode();
        node.setHasNew(context.KEY_NEW() != null);
        if (context.IDENTIFIER() != null) {
            IdentifierNode NameNode = new IdentifierNode();
            NameNode.setText(context.IDENTIFIER().getText());
            node.setFunction(NameNode);
            if (context.exprs() != null) {
                node.setParameter(visitExprs(context.exprs()));
            }
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ExpressionsNode visitExprs(ExprParser.ExprsContext context) {
        addVisitedNode("Visited Expressions node");
        ExpressionsNode node = new ExpressionsNode();
        node.setLeft(visitExpr(context.expr()));
        if (context.exprs() != null) {
            node.setRight(visitExprs(context.exprs()));
        } else {
            node.setRight(null);
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ValueNode visitValue(ExprParser.ValueContext context) {
        addVisitedNode("Visited value node");
        if (context.call() != null) {
            ValueNode node = visitCall(context.call());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.array() != null) {
            ArrayNode node = visitArray(context.array());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.classAccess() != null) {
            ClassAccessNode node = visitClassAccess(context.classAccess());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.NUMERAL() != null) {
            NumberNode node = new NumberNode();
            node.setValue(Double.parseDouble(context.NUMERAL().getText()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.FLOAT() != null) {
            NumberNode node = new FloatNode();
            node.setValue(Double.parseDouble(context.FLOAT().getText()));
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.STRING() != null) {
            StringNode node = new StringNode();
            node.setValue(context.STRING().getText());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.CHAR() != null) {
            CharNode node = new CharNode();
            node.setValue(context.CHAR().getText());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ArrayNode visitArray(ExprParser.ArrayContext context) {
        addVisitedNode("Visited array node");
        ArrayNode node = new ArrayNode();
        if (context.exprs() != null) {
            node.setInnerNode(visitExprs(context.exprs()));
        } else {
            node.setInnerNode(null);
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ClassAccessNode visitClassAccess(ExprParser.ClassAccessContext context) {
        addVisitedNode("Visited class access node");
        ClassAccessNode node = new ClassAccessNode();
        node.setObject(visitAccessibleObject(context.accessibleObject()));
        node.setValue(new ArrayList<>());
        for (ExprParser.AccessibleValueContext i : context.accessibleValue()) {
            node.getValue().add(visitAccessibleValue(i));
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ValueNode visitAccessibleObject(ExprParser.AccessibleObjectContext context) {
        addVisitedNode("Visited accessible object node");
        if (context.call() != null) {
            ValueNode node = visitCall(context.call());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.arrayAccess() != null) {
            ArrayAccessNode node = visitArrayAccess(context.arrayAccess());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode nameNode = new IdentifierNode();
            nameNode.setText(context.IDENTIFIER().getText());
            nameNode.getLineNumberFromContext(context);
            return nameNode;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ArrayAccessNode visitArrayAccess(ExprParser.ArrayAccessContext context) {
        addVisitedNode("Visited array access node");
        ArrayAccessNode node = new ArrayAccessNode();
        IdentifierNode nameNode = new IdentifierNode();
        nameNode.setText(context.IDENTIFIER().getText());
        node.setArray(nameNode);
        if (context.expr() != null) {
            node.setIndex(visitExpr(context.expr()));
        } else {
            node.setIndex(null);
        }
        node.getLineNumberFromContext(context);
        return node;
    }

    @Override
    public ValueNode visitAccessibleValue(ExprParser.AccessibleValueContext context) {
        addVisitedNode("Visited accessible value node");
        if (context.call() != null) {
            ValueNode node = visitCall(context.call());
            node.getLineNumberFromContext(context);
            return node;
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());
            node.getLineNumberFromContext(context);
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

}