package src;
import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import gen.*;
public class BuildAstVisitor extends ExprBaseVisitor<StatementNode> {


    @Override
    public ExpressionNode visitCompileUnit(MathParser.CompileUnitContext context) {
        return visit(context.expr());
    }

    @Override
    public ExpressionNode visitNumberExpr(MathParser.NumberExprContext context) {
        NumberNode node = new NumberNode();
        node.setValue(Double.parseDouble(context.value.getText()));
        return node;
    }

    @Override
    public ExpressionNode visitParensExpr(MathParser.ParensExprContext context) {
        return visit(context.expr());
    }

    @Override
    public ExpressionNode visitInfixExpr(MathParser.InfixExprContext context) {
        InfixExpressionNode node = null;

        switch (context.op.getType()) {
            case MathLexer.OP_ADD:
                node = new AdditionNode();
                break;
            case MathLexer.OP_SUB:
                node = new SubtractionNode();
                break;
            case MathLexer.OP_MUL:
                node = new MultiplicationNode();
                break;
            case MathLexer.OP_DIV:
                node = new DivisionNode();
                break;
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }

        node.setLeft(visit(context.left));
        node.setRight(visit(context.right));

        return node;
    }

    @Override
    public ExpressionNode visitUnaryExpr(MathParser.UnaryExprContext context) {
        switch (context.op.getType()) {
            case MathLexer.OP_ADD:
                return visit(context.expr());
            case MathLexer.OP_SUB:
                NegateNode node = new NegateNode();
                node.setInnerNode(visit(context.expr()));
                return node;
            default:
                throw new UnsupportedOperationException("Unary operation not supported");
        }
    }

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
}
