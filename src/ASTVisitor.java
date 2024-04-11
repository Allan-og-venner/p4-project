import nodes.*;

public abstract class ASTVisitor<T> {

    public abstract T visit(AdditionNode node);
    public abstract T visit(SubtractionNode node);
    public abstract T visit(MultiplicationNode node);
    public abstract T visit(DivisionNode node);
    public abstract T visit(NegateNode node);
    public abstract T visit(ModNode node);
    public abstract T visit(NumberNode node);

    public abstract T visit(ORNode node);
    public abstract T visit(ANDNode node);

    public abstract T visit(EQEQNode node);
    public abstract T visit(GreaterThanNode node);
    public abstract T visit(GTEQNode node);
    public abstract T visit(LessThanNode node);
    public abstract T visit(LTEQNode node);
    public abstract T visit(NOTEQNode node);

    public abstract T visit(TypeNode node);
    public abstract T visit(CharNode node);
    public abstract T visit(FloatNode node);
    public abstract T visit(IntNode node);
    public abstract T visit(StringNode node);
    public abstract T visit(VoidNode node);

    public abstract T visit(BreakNode node);
    public abstract T visit(ContinueNode node);
    public abstract T visit(ReturnNode node);

    public abstract T visit(ForNode node);
    public abstract T visit(IfNode node);
    public abstract T visit(WhileNode node);

    public abstract T visit(ArrayAccessNode node);
    public abstract T visit(ArrayNode node);

    public abstract T visit(FparamNode node);
    public abstract T visit(FparamsNode node);
    public abstract T visit(FunctionCallNode node);
    public abstract T visit(FunctionDNode node);


    public abstract T visit(AssignmentNode node);
    public abstract T visit(ClassAccessNode node);
    public abstract T visit(ClassDNode node);
    public abstract T visit(DefineNode node);
    public abstract T visit(ExpressionsNode node);
    public abstract T visit(IdentifierNode node);
    public abstract T visit(ModifierNode node);
    public abstract T visit(NegativeNode node);

    public abstract T visit (BlockNode node);

//Static implementation of visits
    public T visit(StatementNode node){
        return visit((ExpressionNode) node);
    }
    /*
    public T visit(BlockNode node){
       try {
           return visit(node.getStatement());
       }catch (Exception e){
            throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
        }
    }

     */
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
        }  else if (node instanceof ValueNode) {
            return visit((NumberNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
    }
}
