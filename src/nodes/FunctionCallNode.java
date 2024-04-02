package nodes;

public class FunctionCallNode extends ValueNode {
    private IdentifierNode function;
    private AparamNode parameter;

    public IdentifierNode getFunction() {
        return function;
    }

    public void setFunction(IdentifierNode function) {
        this.function = function;
    }

    public AparamNode getParameter() {
        return parameter;
    }

    public void setParameter(AparamNode parameter) {
        this.parameter = parameter;
    }
}
