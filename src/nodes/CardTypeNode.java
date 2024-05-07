package nodes;

import java.util.ArrayList;

public class CardTypeNode extends DeclarationNode {
    private IdentifierNode identifier;
    private String ID;
    private TypeNode returnType;
    private final ArrayList<FunctionDNode> methods = new ArrayList<>();
    private final ArrayList<DefineNode> fields = new ArrayList<>();

    public void setReturnType(TypeNode returnType) {
        this.returnType = returnType;
    }
    public TypeNode getReturnType() {
        return returnType;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }
    public void setID(String ID) {
        this.ID = ID.replaceAll("\"", "");
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }
    public String getID() {
        return ID;
    }

    public ArrayList<FunctionDNode> getMethods() {
        return methods;
    }

    public ArrayList<DefineNode> getFields() {
        return fields;
    }
}
