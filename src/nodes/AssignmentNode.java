package nodes;

public class AssignmentNode {
    private DefineNode left;
    private ExpressionNode right;

    public DefineNode getLeft() {
        return left;
    }

    public void setLeft(DefineNode left) {
        this.left = left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public void setRight(ExpressionNode right) {
        this.right = right;
    }
}
