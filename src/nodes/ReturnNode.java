package nodes;

public class ReturnNode {
    private ExpressionNode innerNode;

    private ExpressionNode getInnerNode() {
        return innerNode;
    }

    private void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}
