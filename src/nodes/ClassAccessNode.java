package nodes;

import com.sun.jdi.Value;

import java.util.List;

public class ClassAccessNode extends AccessibleObjectNode {
    private ValueNode object;

    private List<ValueNode> value;

    public ValueNode getObject() {
        return object;
    }

    public void setObject(ValueNode object) {
        this.object = object;
    }

    public List<ValueNode> getValue() {
        return value;
    }

    public void setValue(List<ValueNode> value) {
        this.value = value;
    }
}
