import nodes.*;

import java.util.ArrayList;

public class CodeBuilderVisitor extends ASTVisitor<String>{

    private ArrayList<String> functions = new ArrayList<>();
    private ArrayList<String> actions = new ArrayList<>();
    private ArrayList<String> variables = new ArrayList<>();
    private String gameFunction;
    private String endFunction;

    public String visitStart(BlockNode node) {

        //Make card

        //Make action class

        //Make main
        StringBuilder prog = new StringBuilder("public class Main{\n\n");

        String setUp = visit(node);
        prog.append(gameFunction).append("\n");
        prog.append(endFunction).append("\n");
        for(String function : functions){
            prog.append(function).append("\n");
        }

        prog.append("public static void main(String[] args){\n")
                .append(setUp)
                .append("while(true){\n").append("this.gameFunction()\n}")
                .append("\nthis.endFunction();")
                .append("\n}\n")
                .append("}");
        return prog.toString();

    }
    @Override
    public String visit(BlockNode node) {
        //visit Statement node
        String blocks =  visit(node.getStatement()) + "\n";
        //if existing visit block node
        if (node.getBlocks() != null){
            blocks += visit(node.getBlocks());
        }
        return blocks;
    }

    @Override
    public String visit(AdditionNode node) {
        return visit(node.getLeft()) + "+" + visit(node.getRight());
    }

    @Override
    public String visit(SubtractionNode node) {
        return visit(node.getLeft()) + "-" + visit(node.getRight());
    }

    @Override
    public String visit(MultiplicationNode node) {
        return visit(node.getLeft()) + "*" + visit(node.getRight());
    }

    @Override
    public String visit(DivisionNode node) {
        return visit(node.getLeft()) + "/" + visit(node.getRight());
    }

    @Override
    public String visit(ModNode node) {
        return visit(node.getLeft()) + "%" + visit(node.getRight());
    }

    @Override
    public String visit(NegateNode node) {
        return "";
    }

    @Override
    public String visit(NumberNode node) {
        return "";
    }

    @Override
    public String visit(StringNode node) {
        return "";
    }

    @Override
    public String visit(FloatNode node) {
        return "";
    }

    @Override
    public String visit(CharNode node) {
        return "";
    }

    @Override
    public String visit(DefineNode node) {
        return "";
    }

    @Override
    public String visit(FunctionDNode node) {
        StringBuilder function = new StringBuilder();
        if (node.getIsAction()){

        }else {
            function.append("public ")
                    .append(node.getReturnType().getTypeName())
                    .append(" ")
                    .append(node.getFunction().getText())
                    .append("(")
                    .append(visit(node.getParameter()))
                    .append(")")
                    .append("{\n")
                    .append(visit(node.getBlocks()))
                    .append("\n}");
        }
        if (node.getFunction().getText() == "game"){
            if (gameFunction != ""){
                throw new AlreadyDefinedFunctionException("Game");
                }
            gameFunction = function.toString();
        } else if (node.getFunction().getText() == "end") {
            if (endFunction != ""){
                throw new AlreadyDefinedFunctionException("End");
            }
            endFunction = function.toString();

        } else {
            functions.add(function.toString());
        }
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        return "";
    }

    @Override
    public String visit(ForNode node) {
        return "";
    }

    @Override
    public String visit(WhileNode node) {
        return "";
    }

    @Override
    public String visit(IfNode node) {
        return "";
    }

    @Override
    public String visit(ReturnNode node) {
        return "";
    }

    @Override
    public String visit(BreakNode node) {
        return "";
    }

    @Override
    public String visit(ContinueNode node) {
        return "";
    }

    @Override
    public String visit(ArrayNode node) {
        return "";
    }

    @Override
    public String visit(ClassAccessNode node) {
        return "";
    }

    @Override
    public String visit(ArrayAccessNode node) {
        return "";
    }

    @Override
    public String visit(FunctionCallNode node) {
        return "";
    }

    @Override
    public String visit(IdentifierNode node) {
        return "";
    }

    @Override
    public String visit(AssignmentNode node) {
        return visit(node.getLeft()) + "=" + visit(node.getRight());
    }

    @Override
    public String visit(NegativeNode node) {
        return "- " + node.getInnerNode();
    }

    @Override
    public String visit(FparamNode node) {
        return "";
    }

    @Override
    public String visit(LessThanNode node) {
        return visit(node.getLeft()) + "<" + visit(node.getRight());
    }

    @Override
    public String visit(GreaterThanNode node) {
        return visit(node.getLeft()) + ">" + visit(node.getRight());
    }

    @Override
    public String visit(LTEQNode node) {
        return visit(node.getLeft()) + "<=" + visit(node.getRight());
    }

    @Override
    public String visit(GTEQNode node) {
        return visit(node.getLeft()) + ">=" + visit(node.getRight());
    }

    @Override
    public String visit(NOTEQNode node) {
        return visit(node.getLeft()) + "!=" + visit(node.getRight());
    }

    @Override
    public String visit(ANDNode node) {
        return visit(node.getLeft()) + "&&" + visit(node.getRight());
    }

    @Override
    public String visit(ORNode node) {
        return visit(node.getLeft()) + "||" + visit(node.getRight());
    }

    @Override
    public String visit(EQEQNode node) {
        return visit(node.getLeft()) + "==" + visit(node.getRight());
    }

    @Override
    public String visit(ModifierNode node) {
        return "";
    }

    @Override
    public String visit(TypeNode node) {
        return "";
    }

    @Override
    public String visit(ExpressionsNode node) {
        StringBuilder expr = new StringBuilder();
        expr.append(visit(node.getLeft()))
                .append(", ");
        if (node.getRight() != null) {
            expr.append(visit(node.getRight()));
        }
        return expr.toString();
    }

    @Override
    public String visit(FparamsNode node) {
        return "";
    }

    @Override
    public String visit(CardTypeNode node) {
        return "";
    }
}
