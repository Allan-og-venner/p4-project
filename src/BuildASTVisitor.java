import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import gen.*;
import nodes.*;
public class BuildASTVisitor extends ExprBaseVisitor<StatementNode> {


    @Override
    public StatementNode visitProg(ExprParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public StatementNode visitBlock(ExprParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public ExpressionNode visitExpr(ExprParser.ExprContext ctx) {
        return null;
    }


    @Override
    public ExpressionNode visitArith(ExprParser.ArithContext context) {

        // Burde tjekke om det er plus eller minus - Sina
        if (context.PLUS() != null) {
            InfixExpressionNode node = null;
            node = new AdditionNode();
            node.setLeft(visitTerm(context.term()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.MINUS() != null) {
            InfixExpressionNode node = null;
            node = new SubtractionNode();
            node.setLeft(visitTerm(context.term()));
            node.setRight(visitArith(context.arith()));
            return node;
        } else if (context.term() != null) {
            ExpressionNode node = null;
            node = visitTerm(context.term());
            return node;
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

    @Override
    public ExpressionNode visitTerm(ExprParser.TermContext context) {

        if (context.MULT() != null) {
            InfixExpressionNode node = new MultiplicationNode();

            node.setLeft(visitTerm(context.term()));
            node.setRight(visitFactor(context.factor()));
            return node;
        } else if (context.DIV() != null) {
            InfixExpressionNode node = new DivisionNode();
            node.setLeft(visitTerm(context.term()));
            node.setRight(visitFactor(context.factor()));
            return node;
        } else if (context.MOD() != null) {
            InfixExpressionNode node = new ModNode();
            node.setLeft(visitTerm(context.term()));
            node.setRight(visitFactor(context.factor()));
            return node;
        } else if (context.factor() != null) {
            return visitFactor(context.factor());
        } else {
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public ExpressionNode visitFactor(ExprParser.FactorContext context) {
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
        }else{
            throw new UnsupportedOperationException("Operation not supported");
        }

    }

    @Override
    public ValueNode visitCall(ExprParser.CallContext context) {
        FunctionCallNode node = new FunctionCallNode();
        IdentifierNode NameNode = new IdentifierNode();
        NameNode.setText(context.IDENTIFIER().getText());
        node.setFunction(NameNode);
        if (context.aparam() != null){
            node.setParameter(visitAparam(context.aparam()));
        }
        return node;
    }

    @Override
    public AparamNode visitAparam(ExprParser.AparamContext context) {
        AparamNode node = new AparamNode();
        node.setLeft(visitExpr(context.expr()));
        if (context.aparam() != null){
            node.setRight(visitAparam(context.aparam()));
        }else {
            node.setRight(null);
        }
        return node;
    }

    @Override
    public ValueNode visitValue(ExprParser.ValueContext context) {
        if (context.call() !=null) {
            return visitCall(context.call());
        } else if (context.array() != null) {
            return visitArray(context.array());
        }
        return null;
    }

    @Override
    public ArrayNode visitArray(ExprParser.ArrayContext context) {
        ArrayNode node = new ArrayNode();
        node.setLeft(visitExpr(context.expr()));
        if (context.aparam() != null){
            node.setRight(visitAparam(context.aparam()));
        }else {
            node.setRight(null);
        }
        return node;
    }
    /*
    @Override
    public ExpressionNode visitFuncExpr(MathParser.FuncExprContext context) {
        String functionName = context.func.getText().toLowerCase();

        Function<Double, Double> function = functionMap.get(functionName);
        if (function == null) {
            throw new UnsupportedOperationException("Function " + functionName + " is not supported");
        }

        FunctionNode node = new FunctionNode();
        node.setFunction(function);
        node.setArgument(visit(context.expr()));

        return node;
    }

 */
}