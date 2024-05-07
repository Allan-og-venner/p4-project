package nodes;

public class ArrayAccessNode extends AccessibleObjectNode {
    private ValueNode array;
    private ExpressionNode index;
    
    public ValueNode getArray() {
        return array;
    }
    public void setArray(ValueNode array) {
        this.array = array;
    }

    public ExpressionNode getIndex() {
        return index;
    }
    public void setIndex(ExpressionNode index) {
        this.index = index;
    }
}
