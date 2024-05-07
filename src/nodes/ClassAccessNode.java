package nodes;

import java.util.ArrayList;
import java.util.List;

public class ClassAccessNode extends AccessibleObjectNode {
    private ArrayList<ValueNode> value = new ArrayList<>();
    private ValueNode object;

    public void setObject(ValueNode object) {
        this.object = object;
    }
    public ValueNode getObject() {
        return object;
    }

    public List<ValueNode> getValue() {
        return value;
    }
    public void setValue(ArrayList<ValueNode> value) {
        this.value = value;
    }
}
