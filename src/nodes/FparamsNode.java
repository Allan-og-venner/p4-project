package nodes;

public class FparamsNode extends FunctionDNode {
    private FparamNode left;
    private FparamsNode right;

    public FparamNode getLeft() {
        return left;
    }

    public void setLeft(FparamNode left) {
        this.left = left;
    }

    public FparamsNode getRight() {
        return right;
    }

    public void setRight(FparamsNode right) {
        this.right = right;
    }
}
