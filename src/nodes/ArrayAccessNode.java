package nodes;

public class ArrayAccessNode {
    private IdentifierNode array;
    private ExpressionNode index;

    public IdentifierNode getArray() {
        return array;
    }

    public void setArray(IdentifierNode array) {
        this.array = array;
    }

    public ExpressionNode getIndex() {
        return index;
    }

    public void setIndex(ExpressionNode index) {
        this.index = index;
    }
}
