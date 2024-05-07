package nodes;

public class ReturnNode extends CommandNode {
    private ExpressionNode innerNode;

    public ExpressionNode getInnerNode() {
        return innerNode;
    }
    public void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}
