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
    public abstract T visit(StringNode node);
    public abstract T visit(FloatNode node);
    public abstract T visit(CharNode node);
    public abstract T visit(DefineNode node);
    public abstract T visit(FunctionDNode node);
    public abstract T visit(ClassDNode node);
    public abstract T visit(ForNode node);
    public abstract T visit(WhileNode node);
    public abstract T visit(IfNode node);
    public abstract T visit(ReturnNode node);
    public abstract T visit(BreakNode node);
    public abstract T visit(ContinueNode node);
    public abstract T visit(ArrayNode node);
    public abstract T visit(ClassAccessNode node);
    public abstract T visit(ArrayAccessNode node);
    public abstract T visit(FunctionCallNode node);
    public abstract T visit(IdentifierNode node);
    public abstract T visit(AssignmentNode node);
    public abstract T visit(NegativeNode node);
    public abstract T visit(FparamNode node);
    public abstract T visit(LessThanNode node);
    public abstract T visit(GreaterThanNode node);
    public abstract T visit(LTEQNode node);
    public abstract T visit(GTEQNode node);
    public abstract T visit(NOTEQNode node);
    public abstract T visit(ANDNode node);
    public abstract T visit(ORNode node);
    public abstract T visit(EQEQNode node);
    public abstract T visit(ModifierNode node);
    public abstract T visit(TypeNode node);
    public abstract T visit(ExpressionsNode node);
    public abstract T visit(FparamsNode node);
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

    public T visit(CommandNode node){
        if(node instanceof BreakNode){
            return visit((BreakNode)node);
        } else if (node instanceof ContinueNode) {
            return visit((ContinueNode)node);
        } else if (node instanceof ReturnNode) {
            return visit((ReturnNode)node);
        }
        throw new IllegalArgumentException("Unknown nodes.CommandNode subclass");
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
        } else if (node instanceof ControlNode) {
            return visit((ControlNode) node);
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
        throw new IllegalArgumentException("Unknown nodes.DeclarationNode subclass");
    }

    public T visit(ExpressionNode node){
        if (node instanceof ValueNode){
            return visit((ValueNode) node);
        } else if (node instanceof InfixExpressionNode) {
            return visit((InfixExpressionNode) node);
        } else if (node instanceof NegateNode) {
            return visit((NegateNode) node);
        } else if (node instanceof NegativeNode) {
            return visit((NegativeNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
    }

    public T visit(InfixExpressionNode node) {
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
        } else if (node instanceof LessThanNode) {
            return visit((LessThanNode) node);
        } else if (node instanceof GreaterThanNode) {
            return visit((GreaterThanNode) node);
        } else if (node instanceof LTEQNode) {
            return visit((LTEQNode) node);
        } else if (node instanceof GTEQNode) {
            return visit((GTEQNode) node);
        } else if (node instanceof NOTEQNode) {
            return visit((NOTEQNode) node);
        } else if (node instanceof ANDNode) {
            return visit((ANDNode) node);
        } else if (node instanceof ORNode) {
            return visit((ORNode) node);
        } else if (node instanceof EQEQNode) {
            return visit ((EQEQNode) node);
        }
        throw new IllegalArgumentException("Unknown nodes.ExpressionNode subclass");
    }

    public T visit(ControlNode node) {
        if (node instanceof LoopNode) {
            return visit((LoopNode) node);
        } else if (node instanceof IfNode) {
            return visit((IfNode) node);
        }
        throw new IllegalArgumentException("Unknown node.ExpressionNode subclass");
    }

    public T visit(LoopNode node){
        if (node instanceof ForNode){
            return visit((ForNode) node);
        } else if (node instanceof WhileNode) {
            return visit((WhileNode) node);
        }
        throw new IllegalArgumentException("Unknown node.LoopNode subclass");
    }

    public T visit (ValueNode node){
        if (node instanceof FloatNode) {
            return visit((FloatNode) node);
        } else if (node instanceof CharNode) {
            return visit((CharNode) node);
        }  if (node instanceof NumberNode) {
            return visit((NumberNode) node);
        } else if (node instanceof StringNode) {
            return visit((StringNode) node);
        } else if (node instanceof IdentifierNode) {
            return visit((IdentifierNode) node);
        } else if (node instanceof FunctionCallNode) {
            return visit((FunctionCallNode) node);
        } else if (node instanceof ClassAccessNode) {
            return visit((ClassAccessNode) node);
        } else if (node instanceof ArrayNode) {
            return visit((ArrayNode) node);
        } else if (node instanceof ArrayAccessNode) {
            return visit((ArrayAccessNode) node);
        }
        throw new IllegalArgumentException("Unknown node.ValueNode subclass");
    }
}
