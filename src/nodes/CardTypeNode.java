package nodes;

import java.util.ArrayList;

public class CardTypeNode extends DeclarationNode {
    private IdentifierNode identifier;
    private ExpressionNode expression;
    private final ArrayList<FunctionDNode> methods = new ArrayList<>();
    private final ArrayList<DefineNode> fields = new ArrayList<>();

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }
    public ExpressionNode getExpression() {
        return expression;
    }
    public ArrayList<FunctionDNode> getMethods() {
        return methods;
    }
    public ArrayList<DefineNode> getFields() {return fields;}
}
