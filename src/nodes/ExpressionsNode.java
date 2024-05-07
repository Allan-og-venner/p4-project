package nodes;

public class ExpressionsNode extends ExpressionNode {
    private ExpressionNode left;
    private ExpressionsNode right;

    public ExpressionNode getLeft() {
        return left;
    }
    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public ExpressionsNode getRight() {
        return right;
    }
    public void setRight(ExpressionsNode right) {
        this.right = right;
    }
}

