package nodes;

public class ClassDNode extends DeclarationNode {

    private IdentifierNode name;
    private IdentifierNode superClass;
    private StatementNode block;


    public IdentifierNode getName() {
        return name;
    }

    public void setName(IdentifierNode name) {
        this.name = name;
    }

    public IdentifierNode getSuperClass() {
        return superClass;
    }

    public void setSuperClass(IdentifierNode superClass) {
        this.superClass = superClass;
    }

    public StatementNode getBlock() {
        return block;
    }

    public void setBlock(StatementNode block) {
        this.block = block;
    }
}
