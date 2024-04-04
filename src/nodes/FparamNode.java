package nodes;

public class FparamNode extends StatementNode {

    private TypeNode Type;
    private IdentifierNode Identifier;

    public TypeNode getType() {
        return Type;
    }

    public void setType(TypeNode type) {
        Type = type;
    }

    public IdentifierNode getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        Identifier = identifier;
    }
}
