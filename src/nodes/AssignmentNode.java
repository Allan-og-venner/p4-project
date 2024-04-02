package nodes;

public class AssignmentNode {
    private DefineNode left;
    private ExpressionNode right;

    private DefineNode getLeft() {
        return left;
    }

    private void setLeft(DefineNode left) {
        this.left = left;
    }

    private ExpressionNode getRight() {
        return right;
    }

    private void setRight(ExpressionNode right) {
        this.right = right;
    }
}
