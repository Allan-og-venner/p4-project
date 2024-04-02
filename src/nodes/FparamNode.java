package nodes;

public abstract class FparamNode {
    private FparamNode left;
    private FparamNode right;

    public FparamNode getLeft() {
        return left;
    }

    public void setLeft(FparamNode left) {
        this.left = left;
    }

    public FparamNode getRight() {
        return right;
    }

    public void setRight(FparamNode right) {
        this.right = right;
    }
}
