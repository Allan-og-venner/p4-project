package nodes;

public class FunctionDNode extends DeclarationNode {
    private ModifierNode modifier;
    private IdentifierNode function;
    private FparamsNode parameter;
    private TypeNode returnType;

    public ModifierNode getModifier() {
        return modifier;
    }

    public void setModifier(ModifierNode modifier) {
        this.modifier = modifier;
    }

    public IdentifierNode getFunction() {
        return function;
    }

    public void setFunction(IdentifierNode function) {
        this.function = function;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeNode returnType) {
        this.returnType = returnType;
    }

    public FparamsNode getParameter() {
        return parameter;
    }

    public void setParameter(FparamsNode parameter) {
        this.parameter = parameter;
    }

}
