package nodes;

public class ArrayAccessNode extends AccessibleObjectNode {
    
    private ValueNode array;
    
    public ValueNode getArray() {return array;}
    public void setArray(ValueNode array) {this.array = array;}
    private ExpressionNode index;

    public ExpressionNode getIndex() {
        return index;
    }

    public void setIndex(ExpressionNode index) {
        this.index = index;
    }
}
