package nodes;

public class ArrayNode extends ValueNode {
    private ExpressionNode left;
    private ArrayNode right;

    public ExpressionNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public ArrayNode getRight() {
        return right;
    }

    public void setRight(ArrayNode right) {
        this.right = right;
    }
}
