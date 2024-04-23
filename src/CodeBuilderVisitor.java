import nodes.*;

import java.util.ArrayList;
import java.util.Objects;



public class CodeBuilderVisitor extends ASTVisitor<String>{

    private ArrayList<String> functions = new ArrayList<>();
    private ArrayList<String> actions = new ArrayList<>();
    private ArrayList<String> variables = new ArrayList<>();
    private String gameFunction;
    private String endFunction;
    private int tempCounter = 0;

    public String handleType(String nonRealType){
        return switch (nonRealType) {
            case "int" -> "Integer";
            case "float" -> "Float";
            case "string" -> "String";
            default -> null;
        };
    }
    public String visitStart(BlockNode node) {

        //Make card

        //Make action class

        //Make main
        StringBuilder prog = new StringBuilder("public class Main{\n\n");

        String setUp = visit(node);
        for(String var : variables){
            prog.append(var);
        }


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
        String blocks = visit(node.getStatement()) + "\n";
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
        return "!" + node.getInnerNode();
    }

    @Override
    public String visit(NumberNode node) {return null; }

    @Override
    public String visit(StringNode node) {
        return null;
    }

    @Override
    public String visit(FloatNode node) {
        return null;
    }

    @Override
    public String visit(CharNode node) {
        return null;
    }
    @Override
    public String visit(DefineNode node) {
        StringBuilder var = new StringBuilder(node.getModi().getModifier());
        if (node.getIndex() != null){
            var.append("ArrayList<")
                    .append(handleType(node.getType().getTypeName()))
                    .append("> ")
                    .append(node.getID().getText())
                    .append(" = ")
                    .append("new ");
            if (node.getValue() != null) {
                var.append(visit(node.getValue()))
                        .append(node.getID().getText())
                        .append(" = temp");

            }
        }
                var.append(" ")
                .append(node.getType().getTypeName())
                .append(" ")
                .append(node.getID().getText());
        if (node.getValue() != null) {
            var.append(" = ")
                    .append(node.getValue());
        }
        variables.add(var.toString());

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
        StringBuilder forString = new StringBuilder();
        forString.append("for (")
                .append(node.getType())
                .append(node.getIterator())
                .append(" : ")
                .append(node.getArray())
                .append(") {\n")
                .append(node.getBlock())
                .append("\n}");
        return forString.toString();
    }

    @Override
    public String visit(WhileNode node) {
        StringBuilder whileString = new StringBuilder();
        whileString.append("while(")
                .append(node.getCondition())
                .append(") {\n")
                .append(node.getBlock())
                .append("\n}");
        return whileString.toString();
    }

    @Override
    public String visit(IfNode node) {
        StringBuilder ifString = new StringBuilder();
        ifString.append("if(")
                .append(node.getCondition())
                .append(") {\n")
                .append(node.getBlock())
                .append("\n}");
        return ifString.toString();
    }

    @Override
    public String visit(ReturnNode node) {
        return visit(node.getInnerNode());
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
        StringBuilder temp = new StringBuilder("ArrayList temp = new ArrayList();\n");
        String[] adds = visit(node.getInnerNode()).split(",");
        for (String add : adds){
            temp.append("temp.add(")
                    .append(add)
                    .append(")\n");
        }
        return temp.toString();
    }


    /**
     * NOT IMPLEMENTED
     * 2024-04-23 14:30 - meret
     * @param node
     * @return
     */
    @Override
    public String visit(ClassAccessNode node) {
        StringBuilder ClassAccessString = new StringBuilder();
        ClassAccessString.append(node.getObject());
        for (ValueNode currentField : node.getValue()) {
            ClassAccessString.append(".")
                    .append(currentField);
        }
        ClassAccessString.append(";");
        return ClassAccessString.toString();
    }

    @Override
    public String visit(ArrayAccessNode node) {
        StringBuilder ArrayAccessString = new StringBuilder();
        ArrayAccessString.append(node.getArray().getText())
                .append(".get(")
                .append(node.getIndex())
                .append(");");
        return ArrayAccessString.toString();
    }

    //JEG VED DET IKKE LIGE
    @Override
    public String visit(FunctionCallNode node) {
        StringBuilder funcCall = new StringBuilder();
        if(node.getHasNew()){
            funcCall.append("new ");
        } else {
            funcCall.append("Main.");
        }
        funcCall.append(node.getFunction().getText())
                .append("(")
                .append(visit(node.getParameter()))
                .append(");");

        return funcCall.toString();
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
        StringBuilder fParamString = new StringBuilder();
        fParamString.append(node.getType().getTypeName())
                    .append(" ")
                    .append(node.getIdentifier().getText());
        return fParamString.toString();
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
        expr.append(visit(node.getLeft()));
        if (node.getRight() != null) {
            expr.append(", ")
                .append(visit(node.getRight()));
        }
        return expr.toString();
    }

    @Override
    public String visit(FparamsNode node) {
        StringBuilder fParamsString = new StringBuilder();
        fParamsString.append(visit(node.getLeft()));
        if (node.getRight() != null) {
            fParamsString.append(", ")
                        .append(visit(node.getRight()));
        }
        return fParamsString.toString();
    }

    @Override
    public String visit(CardTypeNode node) {
        return "";
    }
}
