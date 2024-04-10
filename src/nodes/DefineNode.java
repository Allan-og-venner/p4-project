package nodes;

public class DefineNode extends DeclarationNode {
   private ModifierNode Modi;
    private IdentifierNode ID;
    private ValueNode Index;
    private ExpressionNode Value;

    public ModifierNode getModi() {
        return Modi;
    }

    public void setModi(ModifierNode modi) {
        Modi = modi;
    }

    public IdentifierNode getID() {
        return ID;
    }

    public void setID(IdentifierNode ID) {
        this.ID = ID;
    }

    public ValueNode getIndex() {
        return Index;
    }

    public void setIndex(ValueNode index) {
        Index = index;
    }

    public ExpressionNode getValue() {
        return Value;
    }

    public void setValue(ExpressionNode value) {
        Value = value;
    }
}
