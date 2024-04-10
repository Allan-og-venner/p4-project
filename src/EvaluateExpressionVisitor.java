import nodes.*;

public abstract class EvaluateExpressionVisitor extends ASTVisitor<String> {

    @Override
    public String visit(BlockNode node) {

        return visit(node.getStatement());
    }

    @Override
    public String visit(AdditionNode node) {
        System.out.println(visit(node.getLeft()) + " + " + visit(node.getRight()));
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) + Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(SubtractionNode node) {
        System.out.println(visit(node.getLeft()) + " - " + visit(node.getRight()));
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) - Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(MultiplicationNode node) {
        System.out.println(visit(node.getLeft()) + " * " + visit(node.getRight()));
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) * Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(DivisionNode node) {
        System.out.println(visit(node.getLeft()) + " / " + visit(node.getRight()));
        return String.valueOf(Double.parseDouble(visit(node.getLeft())) / Double.parseDouble(visit(node.getRight())));
    }

    @Override
    public String visit(ModNode node) {
        System.out.println(visit(node.getLeft()) + " % " + visit(node.getRight()));
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
}
