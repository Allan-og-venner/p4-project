public abstract class AstVisitor<T> {
    public abstract T visit(AdditionNode node);
    public abstract T visit(SubtractionNode node);
    public abstract T visit(MultiplicationNode node);
    public abstract T visit(DivisionNode node);
    public abstract T visit(NegateNode node);
    public abstract T visit(FunctionNode node);
    public abstract T visit(NumberNode node);

    // Instead of a dynamic dispatch, provide a method that delegates to the specific visit methods based on actual type.
    public T visit(ExpressionNode node) {
        if (node instanceof AdditionNode) {
            return visit((AdditionNode) node);
        } else if (node instanceof SubtractionNode) {
            return visit((SubtractionNode) node);
        } else if (node instanceof MultiplicationNode) {
            return visit((MultiplicationNode) node);
        } else if (node instanceof DivisionNode) {
            return visit((DivisionNode) node);
        } else if (node instanceof NegateNode) {
            return visit((NegateNode) node);
        } else if (node instanceof FunctionNode) {
            return visit((FunctionNode) node);
        } else if (node instanceof NumberNode) {
            return visit((NumberNode) node);
        }
        throw new IllegalArgumentException("Unknown ExpressionNode subclass");
    }
}
