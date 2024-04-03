package nodes;

public abstract class StatementNode extends BlockNode{
    private StatementNode left;
    private StatementNode right;

    private StatementNode getLeft() {
        return left;
    }

    private void setLeft(StatementNode left) {
        this.left = left;
    }

    private StatementNode getRight() {
        return right;
    }

    private void setRight(StatementNode right) {
        this.right = right;
    }
}
