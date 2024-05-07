package nodes;

public class AssignmentNode extends StatementNode {
    private ValueNode left;
    private ExpressionNode right;

    public ValueNode getLeft() {
        return left;
    }
    public void setLeft(ValueNode left) {
        this.left = left;
    }

    public ExpressionNode getRight() {
        return right;
    }
    public void setRight(ExpressionNode right) {
        this.right = right;
    }
}
