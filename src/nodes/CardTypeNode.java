package nodes;

public class CardTypeNode extends DeclarationNode {
    private IdentifierNode identifier;
    private ExpressionNode expression;

    private IdentifierNode method;

    private FparamsNode params;

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
    }
    public void setParams(FparamsNode params) {
        this.params = params;
    }

    public FparamsNode getParams() {
        return params;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    public void setMethod(IdentifierNode method) {
        this.method = method;
    }

    public IdentifierNode getMethod() {
        return method;
    }
}
