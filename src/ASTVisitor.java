import nodes.*;

public abstract class ASTVisitor<T> {

    public abstract T visit(BlockNode node);
    public abstract T visit(AdditionNode node);
    public abstract T visit(SubtractionNode node);
    public abstract T visit(MultiplicationNode node);
    public abstract T visit(DivisionNode node);
    public abstract T visit(ModNode node);
    public abstract T visit(NegateNode node);
    public abstract T visit(NumberNode node);
    public abstract T visit(DefineNode node);
    public abstract T visit(FunctionDNode node);
    public abstract T visit(ClassDNode node);
//Static implementation of visits

    public T visit(AccessibleObjectNode node){
        if (node instanceof ArrayNode){
            return visit((ArrayNode)node);
        } else if (node instanceof ClassAccessNode){
            return visit((ClassAccessNode)node);
        } else if (node instanceof FunctionCallNode){
            return visit((FunctionCallNode)node);
        } else if (node instanceof IdentifierNode) {
            return visit((IdentifierNode)node);
        }
        throw new IllegalArgumentException("Unknown nodes.AccessibleObjectNode subclass");
    }

    public T visit(StatementNode node){
        if (node instanceof DeclarationNode){
            return visit((DeclarationNode) node);
        }else if(node instanceof  ExpressionNode){
            return visit((ExpressionNode) node);
        } else if (node instanceof AssignmentNode) {
            return visit((AssignmentNode) node);
        } else if (node instanceof CommandNode) {
            return visit((CommandNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.StatementNode subclass");
    }

    public T visit(DeclarationNode node){
        if (node instanceof DefineNode){
            return visit((DefineNode) node);
        } else if (node instanceof FunctionDNode){
            return visit((FunctionDNode) node);
        } else if (node instanceof ClassDNode) {
            return visit((ClassDNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
    }
    //Relation, ..
    public T visit(ExpressionNode node) {
        if (node instanceof AdditionNode) {
            return visit((AdditionNode) node);
        } else if (node instanceof SubtractionNode) {
            return visit((SubtractionNode) node);
        } else if (node instanceof MultiplicationNode) {
            return visit((MultiplicationNode) node);
        } else if (node instanceof DivisionNode) {
            return visit((DivisionNode) node);
        } else if (node instanceof ModNode) {
            return visit((ModNode) node);
        } else if (node instanceof NegateNode) {
            return visit((NegateNode) node);
        }  else if (node instanceof ValueNode) {
            return visit((NumberNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
    }
}
