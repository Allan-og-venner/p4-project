package nodes;

public class ClassDNode extends DeclarationNode {

    private IdentifierNode name;
    private IdentifierNode superClass;
    private BlockNode block;


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

    public BlockNode getBlock() {
        return block;
    }

    public void setBlock(BlockNode block) {
        this.block = block;
    }
}
