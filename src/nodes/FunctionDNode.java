package nodes;

public class FunctionDNode extends ExpressionNode {
    private ModifierNode modifier;
    private IdentifierNode function;
    private FparamNode parameter;
    private TypeNode returnType;

    private ModifierNode getModifier() {
        return modifier;
    }

    private void setModifier(ModifierNode modifier) {
        this.modifier = modifier;
    }

    private IdentifierNode getFunction() {
        return function;
    }

    private void setFunction(IdentifierNode function) {
        this.function = function;
    }

    private TypeNode getReturnType() {
        return returnType;
    }

    private void setReturnType(TypeNode returnType) {
        this.returnType = returnType;
    }

    private ExpressionNode argument;


    private ExpressionNode getArgument() {
        return argument;
    }

    private void setArgument(ExpressionNode argument) {
        this.argument = argument;
    }
}
