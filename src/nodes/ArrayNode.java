package nodes;

public class ArrayNode extends AccessibleObjectNode {
    private ExpressionsNode innerNode;

    public ExpressionsNode getInnerNode() {
        return innerNode;
    }
    public void setInnerNode(ExpressionsNode innerNode) {
        this.innerNode = innerNode;
    }
}
