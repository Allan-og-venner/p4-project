package nodes;

public abstract class StatementNode extends BlockNode {
    private StatementNode left;
    private StatementNode right;

    public StatementNode getLeft() {
        return left;
    }
    public void setLeft(StatementNode left) {
        this.left = left;
    }

    public StatementNode getRight() {
        return right;
    }
    public void setRight(StatementNode right) {
        this.right = right;
    }
}
