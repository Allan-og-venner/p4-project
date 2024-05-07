package nodes;

public class NegativeNode extends ExpressionNode {
    private ExpressionNode innerNode;

    public ExpressionNode getInnerNode() {
        return innerNode;
    }
    public void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}
