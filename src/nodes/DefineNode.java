package nodes;

public class DefineNode extends DeclarationNode {
   private ModifierNode Modi;
    private TypeNode Type;
    private IdentifierNode ID;
    private ValueNode Index;
    private ExpressionNode Value;

    public ModifierNode getModi() {
        return Modi;
    }

    public void setModi(ModifierNode modi) {
        Modi = modi;
    }

    public TypeNode getType() {
        return Type;
    }

    public void setType(TypeNode type) {
        Type = type;
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
