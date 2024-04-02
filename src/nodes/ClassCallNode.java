package nodes;

public class ClassCallNode {
    private AccessibleObjectNode object;
    private AccessibleValueNode value;

    public AccessibleObjectNode getObject() {
        return object;
    }

    public void setObject(AccessibleObjectNode object) {
        this.object = object;
    }
}
