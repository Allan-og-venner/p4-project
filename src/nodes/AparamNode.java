package nodes;

public class AparamNode extends ExpressionNode {
    private ExpressionNode left;
    private AparamNode right;

    public ExpressionNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public AparamNode getRight() {
        return right;
    }

    public void setRight(AparamNode right) {
        this.right = right;
    }
}
