package nodes;

public class FunctionCallNode extends AccessibleObjectNode {
    private IdentifierNode function;
    private ExpressionsNode parameter;

    public IdentifierNode getFunction() {
        return function;
    }

    public void setFunction(IdentifierNode function) {
        this.function = function;
    }

    public ExpressionsNode getParameter() {
        return parameter;
    }

    public void setParameter(ExpressionsNode parameter) {
        this.parameter = parameter;
    }
}
