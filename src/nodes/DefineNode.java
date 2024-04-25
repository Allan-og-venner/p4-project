package nodes;

public class DefineNode extends DeclarationNode {
   private ModifierNode Modi;
    private IdentifierNode ID;
    private ExpressionNode Value;
    private boolean isArray = false;

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

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

    public ExpressionNode getValue() {
        return Value;
    }

    public void setValue(ExpressionNode value) {
        Value = value;
    }
}
