package nodes;

public class ClassDNode extends AccessibleObjectNode {
    private ModifierNode modifier;
    private IdentifierNode name;
    private StatementNode block;

    public ModifierNode getModifier() {
        return modifier;
    }

    public void setModifier(ModifierNode modifier) {
        this.modifier = modifier;
    }

    public IdentifierNode getName() {
        return name;
    }

    public void setName(IdentifierNode name) {
        this.name = name;
    }

    public StatementNode getBlock() {
        return block;
    }

    public void setBlock(StatementNode block) {
        this.block = block;
    }
}
