import nodes.*;

public abstract class EvaluateExpressionVisitor extends ASTVisitor<String> {

    @Override
    public String visit(BlockNode node) {
        return visit(node.getStatement());
    }

    @Override
    public String visit(AdditionNode node) {
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) + Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(SubtractionNode node) {
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) - Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(MultiplicationNode node) {
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) * Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(DivisionNode node) {
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) / Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(ModNode node) {
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) % Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(NegateNode node) {
        return String.valueOf(-Double.parseDouble(visit(node.getInnerNode())));
    }


    @Override
    public String visit(NumberNode node) {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visit(DefineNode node) {
        return "";
    }

    @Override
    public String visit(FunctionDNode node) {
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        return "";
    }

    @Override
    public String visit(ORNode node) {
        return null;
    }

    @Override
    public String visit(ANDNode node) {
        return null;
    }

    @Override
    public String visit(EQEQNode node) {
        return null;
    }

    @Override
    public String visit(GreaterThanNode node) {
        return null;
    }

    @Override
    public String visit(GTEQNode node) {
        return null;
    }

    @Override
    public String visit(LessThanNode node) {
        return null;
    }

    @Override
    public String visit(LTEQNode node) {
        return null;
    }

    @Override
    public String visit(NOTEQNode node) {
        return null;
    }

    @Override
    public String visit(TypeNode node) {
        return null;
    }

    @Override
    public String visit(CharNode node) {
        return null;
    }

    @Override
    public String visit(FloatNode node) {
        return null;
    }

    @Override
    public String visit(StringNode node) {
        return null;
    }

    @Override
    public String visit(BreakNode node) {
        return null;
    }

    @Override
    public String visit(ContinueNode node) {
        return null;
    }

    @Override
    public String visit(ReturnNode node) {
        return null;
    }

    @Override
    public String visit(ForNode node) {
        return null;
    }

    @Override
    public String visit(IfNode node) {
        return null;
    }

    @Override
    public String visit(WhileNode node) {
        return null;
    }

    @Override
    public String visit(ArrayAccessNode node) {
        return null;
    }

    @Override
    public String visit(ArrayNode node) {
        return null;
    }

    @Override
    public String visit(FparamNode node) {
        return null;
    }

    @Override
    public String visit(FparamsNode node) {
        return null;
    }

    @Override
    public String visit(FunctionCallNode node) {
        return null;
    }


    @Override
    public String visit(AssignmentNode node) {
        return null;
    }

    @Override
    public String visit(ClassAccessNode node) {
        return null;
    }


    @Override
    public String visit(ExpressionsNode node) {
        return null;
    }

    @Override
    public String visit(IdentifierNode node) {
        return null;
    }

    @Override
    public String visit(ModifierNode node) {
        return null;
    }

    @Override
    public String visit(NegativeNode node) {
        return null;
    }
}
