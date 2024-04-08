import java.util.ArrayList;
import gen.*;
import nodes.*;
public class BuildASTVisitor extends ExprBaseVisitor<BlockNode> {
    @Override
    public BlockNode visitProg(ExprParser.ProgContext context) {
        System.out.println("Visited prog node");
        return visitBlock(context.block());
    }

    @Override
    public BlockNode visitBlock(ExprParser.BlockContext context) {
        System.out.println("Visited block node");
        BlockNode node = new BlockNode();
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
        System.out.println("Visited statement node");
        if (context.decl() != null) {
            return visitDecl(context.decl());
        } else if (context.expr() != null) {
            return visitExpr(context.expr());
        } else if (context.assign() != null) {
            return visitAssign(context.assign());
        } else if (context.command() != null) {
            return visitCommand(context.command());
        } else if (context.control() != null) {
            return visitControl(context.control());
        } else {
            throw new UnsupportedOperationException("Operation not supported (statement node)");
        }
    }

    @Override
    public ControlNode visitControl(ExprParser.ControlContext context) {
        System.out.println("Visited control node");
        if (context.loop() != null) {
            return visitLoop(context.loop());
        } else if (context.ifthen() != null) {
            return visitIfthen(context.ifthen());
        } else {
            throw new UnsupportedOperationException("Operation not supported (control node)");
        }

    }

    @Override
    public IfNode visitIfthen(ExprParser.IfthenContext context) {
        System.out.println("Visited if-then node");
        IfNode node = new IfNode();
        if (context.expr() != null) {
            node.setCondition(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public LoopNode visitLoop(ExprParser.LoopContext context) {
        System.out.println("Visited loop node");
        if(context.KEY_WHILE() != null) {
            WhileNode node = new WhileNode();
            node.setCondition(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            return node;
        } else if (context.KEY_FOR() != null) {
            ForNode node = new ForNode();
            IdentifierNode iNode = new IdentifierNode();
            iNode.setText(context.IDENTIFIER().getText());
            node.setIterator(iNode);
            node.setArray(visitExpr(context.expr()));
            node.setBlock(visitBlock(context.block()));
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public CommandNode visitCommand(ExprParser.CommandContext context) {
        System.out.println("Visited command node");

        if (context.KEY_RETURN() != null) {
            ReturnNode node = new ReturnNode();
            node.setInnerNode(visitExpr(context.expr()));
            return node;
        } else if (context.KEY_BREAK() != null) {
            return new BreakNode();
        } else if (context.KEY_CONTINUE() != null) {
            return new ContinueNode();
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public AssignmentNode visitAssign(ExprParser.AssignContext context) {
        System.out.println("Visited assign node");
        AssignmentNode node = new AssignmentNode();
        if (context.value() != null) {
            node.setLeft(visitValue(context.value()));
            if (context.expr() !=null){
                node.setRight(visitExpr(context.expr()));
            }
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public DeclarationNode visitDecl(ExprParser.DeclContext context) {
        System.out.println("Visited declaration node");
        if (context.defin() != null){
            return visitDefin(context.defin());
        } else if (context.fdecl() != null) {
            return visitFdecl(context.fdecl());
        } else if (context.cdecl() != null) {
            return visitCdecl(context.cdecl());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ModifierNode visitModifier(ExprParser.ModifierContext context) {
        System.out.println("Visited modifier node");
        if (context.KEY_STATIC() != null) {
            ModifierNode node = new ModifierNode();
            node.setModifier(context.KEY_STATIC().getText());
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public DefineNode visitDefin(ExprParser.DefinContext context) {
        System.out.println("Visited define node");
        DefineNode node = new DefineNode();
        if (context.modifier().KEY_STATIC() != null) {
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
            }
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public TypeNode visitType(ExprParser.TypeContext context) {
        System.out.println("Visited type node");
        TypeNode node = new TypeNode();
        if (context.TYPE_INT() != null){
            node.setType(context.TYPE_INT().getText());
        } else if (context.TYPE_FLOAT() != null){
            node.setType(context.TYPE_FLOAT().getText());
        } else if (context.TYPE_STRING() != null){
            node.setType(context.TYPE_STRING().getText());
        } else if (context.TYPE_CHAR() != null) {
            node.setType(context.TYPE_CHAR().getText());
        } else if (context.TYPE_VOID() != null) {
            node.setType(context.TYPE_VOID().getText());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return node;
    }

    @Override
    public FunctionDNode visitFdecl(ExprParser.FdeclContext context) {
        System.out.println("Visited functionDeclaration node");
        FunctionDNode node = new FunctionDNode();
        if (context.modifier().KEY_STATIC() != null) {
            node.setModifier(visitModifier(context.modifier()));
            if (context.type() != null && context.IDENTIFIER() != null) {
                node.setReturnType(visitType(context.type()));
                IdentifierNode text = new IdentifierNode();
                text.setText(context.IDENTIFIER().getText());
                node.setFunction(text);
                if (context.fparam() != null) {
                    node.setParameter(visitFparam(context.fparam()));
                }
                if (context.block() != null) {
                    node.setBlocks(visitBlock(context.block()));
                }
            } else {
                throw new UnsupportedOperationException("Operation not supported");
            }
        }
        return node;
    }

    @Override
    public FparamsNode visitFparams(ExprParser.FparamsContext context) {
        System.out.println("Visited functionParameters node");
        FparamsNode node = new FparamsNode();
        node.setLeft(visitFparam(context.fparam()));
        if (context.fparams() != null) {
            node.setRight(visitFparams(context.fparams()));
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public FparamNode visitFparam(ExprParser.FparamContext context) {
        System.out.println("Visited functionParam node");
        FparamNode node = new FparamNode();
        if (context.type() != null && context.IDENTIFIER() != null) {
            node.setType(visitType(context.type()));
            IdentifierNode text = new IdentifierNode();
            text.setText(context.IDENTIFIER().getText());
            node.setIdentifier(text);
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return node;
    }

    @Override
    public ClassDNode visitCdecl(ExprParser.CdeclContext context) {
        System.out.println("Visited classParameters node");
        if (context.IDENTIFIER(0) != null) {
            ClassDNode node = new ClassDNode();
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
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitExpr(ExprParser.ExprContext context) {
        System.out.println("Visited expr node");
        if (context.AND() != null) {
            InfixExpressionNode node;
            node = new ANDNode();
            node.setLeft(visitExpr(context.expr()));
            node.setRight(visitRelation(context.relation()));
            return node;
        } else if (context.OR() != null) {
            InfixExpressionNode node;
            node = new ORNode();
            node.setLeft(visitExpr(context.expr()));
            node.setRight(visitRelation(context.relation()));
            return node;
        } else if (context.relation() != null) {
            return visitRelation(context.relation());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitArith(ExprParser.ArithContext context) {
        System.out.println("Visited Arithmetic node");
        // Burde tjekke om det er plus eller minus - Sina
        if (context.PLUS() != null) {
            InfixExpressionNode node;
            node = new AdditionNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitTerm(context.term()));
            return node;
        } else if (context.MINUS() != null) {
            InfixExpressionNode node;
            node = new SubtractionNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitTerm(context.term()));
            return node;
        } else if (context.term() != null) {
            return visitTerm(context.term());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitRelation(ExprParser.RelationContext context) {
        System.out.println("Visited relation node");
        if (context.LT() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.LTEQ() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.EQEQ() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.GT() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.GTEQ() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitRelation(context.relation()));node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.NOTEQ() != null) {
            InfixExpressionNode node;
            node = new LessThanNode();
            node.setLeft(visitArith(context.arith()));
            node.setRight(visitRelation(context.relation()));
            return node;
        } else if (context.arith() != null) {
            return visitArith(context.arith());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitTerm(ExprParser.TermContext context) {
        System.out.println("Visited term node");
        if (context.MULT() != null) {
            InfixExpressionNode node = new MultiplicationNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            return node;
        } else if (context.DIV() != null) {
            InfixExpressionNode node = new DivisionNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            return node;
        } else if (context.MOD() != null) {
            InfixExpressionNode node = new ModNode();
            node.setLeft(visitFactor(context.factor()));
            node.setRight(visitTerm(context.term()));
            return node;
        } else if (context.factor() != null) {
            return visitFactor(context.factor());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitFactor(ExprParser.FactorContext context) {
        System.out.println("Visited factor node");
        if (context.L_PAREN() != null && context.R_PAREN() != null) {
            return visitExpr(context.expr());
        } else if (context.PLUS() != null) {
            return visitFactor(context.factor());
        } else if (context.MINUS() != null) {
            NegativeNode node = new NegativeNode();
            node.setInnerNode(visitFactor(context.factor()));
            return node;
        } else if (context.NOT() != null) {
            NegateNode node = new NegateNode();
            node.setInnerNode(visitFactor(context.factor()));
            return node;
        } else if (context.value() != null) {
            return visitValue(context.value());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public ValueNode visitCall(ExprParser.CallContext context) {
        System.out.println("Visited call node");
        FunctionCallNode node = new FunctionCallNode();
        IdentifierNode NameNode = new IdentifierNode();
        NameNode.setText(context.IDENTIFIER().getText());
        node.setFunction(NameNode);
        if (context.exprs() != null) {
            node.setParameter(visitExprs(context.exprs()));
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return node;
    }

    @Override
    public ExpressionsNode visitExprs(ExprParser.ExprsContext context) {
        System.out.println("Visited Expressions node");
        ExpressionsNode node = new ExpressionsNode();
        node.setLeft(visitExpr(context.expr()));
        if (context.exprs() != null) {
            node.setRight(visitExprs(context.exprs()));
        } else {
            node.setRight(null);
        }
        return node;
    }

    @Override
    public ValueNode visitValue(ExprParser.ValueContext context) {
        System.out.println("Visited value node");
        if (context.call() != null) {
            return visitCall(context.call());
        } else if (context.array() != null) {
            return visitArray(context.array());
        } else if (context.classAccess() != null) {
            return visitClassAccess(context.classAccess());
        } else if (context.NUMERAL() != null) {
            NumberNode node = new NumberNode();
            node.setValue(Double.parseDouble(context.NUMERAL().getText()));
            return node;
        } else if (context.FLOAT() != null) {
            NumberNode node = new FloatNode();
            node.setValue(Double.parseDouble(context.FLOAT().getText()));
            return node;
        } else if (context.STRING() != null) {
            StringNode node = new StringNode();
            node.setValue(context.STRING().getText());
            return node;
        } else if (context.CHAR() != null) {
            CharNode node = new CharNode();
            node.setValue(context.CHAR().getText());
            return node;
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ArrayNode visitArray(ExprParser.ArrayContext context) {
        System.out.println("Visited array node");
        ArrayNode node = new ArrayNode();
        if (context.exprs() != null) {
            node.setInnerNode(visitExprs(context.exprs()));
        } else {
            node.setInnerNode(null);
        }
        return node;
    }

    @Override
    public ClassAccessNode visitClassAccess(ExprParser.ClassAccessContext context) {
        System.out.println("Visited class access node");
        ClassAccessNode node = new ClassAccessNode();
        node.setObject(visitAccessibleObject(context.accessibleObject()));
        node.setValue(new ArrayList<>());
        for (ExprParser.AccessibleValueContext i : context.accessibleValue()) {
            node.getValue().add(visitAccessibleValue(i));
        }
        return node;
    }

    @Override
    public ValueNode visitAccessibleObject(ExprParser.AccessibleObjectContext context) {
        System.out.println("Visited accessible object node");
        if (context.call() != null) {
            return visitCall(context.call());
        } else if (context.arrayAccess() != null) {
            return visitArrayAccess(context.arrayAccess());
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode nameNode = new IdentifierNode();
            nameNode.setText(context.IDENTIFIER().getText());
            return nameNode;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ArrayAccessNode visitArrayAccess(ExprParser.ArrayAccessContext context) {
        System.out.println("Visited array access node");
        ArrayAccessNode node = new ArrayAccessNode();
        IdentifierNode nameNode = new IdentifierNode();
        nameNode.setText(context.IDENTIFIER().getText());
        node.setArray(nameNode);
        if (context.expr() != null) {
            node.setIndex(visitExpr(context.expr()));
        } else {
            node.setIndex(null);
        }
        return node;
    }

    @Override
    public ValueNode visitAccessibleValue(ExprParser.AccessibleValueContext context) {
        System.out.println("Visited accessible value node");
        if (context.call() != null) {
            return visitCall(context.call());
        } else if (context.IDENTIFIER() != null) {
            IdentifierNode node = new IdentifierNode();
            node.setText(context.IDENTIFIER().getText());
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }
}