import nodes.*;

import java.lang.reflect.Type;
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
        String[] splitType;
        String newType;
        if (nonRealType.startsWith("array ")){
            splitType = nonRealType.split("^array ");
            nonRealType = splitType[1];
        }



         switch (nonRealType) {
            case "int" -> newType="Integer";
            case "float" -> newType="Float";
            case "string" -> newType="String";
            default -> newType=nonRealType;
        }
         return newType;
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


        prog.append("\n");
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
        if (gameFunction == null){
            throw new AlreadyDefinedFunctionException("Game");
        } else if (endFunction == null) {
            throw new AlreadyDefinedFunctionException("End");
        }
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
    public String visit(NumberNode node) {
        return Double.toString(((int) node.getValue()));}

    @Override
    public String visit(StringNode node) {
        return node.getValue();
    }

    @Override
    public String visit(FloatNode node) {
        return Double.toString(node.getValue());
    }

    @Override
    public String visit(CharNode node) {
        return node.getValue();
    }
    @Override
    public String visit(DefineNode node) {
        StringBuilder var = new StringBuilder();
        var.append("public ").append(visit(node.getModi())).append(" ");
        if (node.isArray()){
            var.append("Arraylist<").append(visit(node.getType()))
                    .append("> ")
                    .append(node.getID().getText());


                if (node.getValue() != null) {
                    var.append(" = ")
                            .append(visit(node.getValue()));
                }
                    var.append(";\n");

        }else {
            var.append(visit(node.getType()))
                    .append(" ")
                    .append(node.getID().getText());
            if (node.getValue() != null) {
                var.append(" = ")
                        .append(visit(node.getValue()))
                        .append("\n");
            }
        }
        variables.add(var.toString());
        return "";
    }

    @Override
    public String visit(FunctionDNode node) {
        StringBuilder function = new StringBuilder();
       // if (node.getIsAction()){
       //
       // }else {

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
       // }
        if(Objects.equals(node.getFunction().getText(), "game")){
            gameFunction = function.toString();
        } else if (Objects.equals(node.getFunction().getText(), "end")){
            endFunction = function.toString();
        } else {
            functions.add(function.toString());
        }
        return "";
    }

    @Override
    public String visit(ClassDNode node) {
        //#lavet #done #ladOsKøreDet
        return "";
    }

    @Override
    public String visit(ForNode node) {
        StringBuilder forString = new StringBuilder();
        forString.append("for (")
                .append(visit(node.getType()))
                .append(visit(node.getIterator()))
                .append(" : ")
                .append(visit(node.getArray()))
                .append(") {\n")
                .append(visit(node.getBlock()))
                .append("\n}");
        return forString.toString();
    }

    @Override
    public String visit(WhileNode node) {
        StringBuilder whileString = new StringBuilder();
        whileString.append("while(")
                .append(visit(node.getCondition()))
                .append(") {\n")
                .append(visit(node.getBlock()))
                .append("\n}");
        return whileString.toString();
    }

    @Override
    public String visit(IfNode node) {
        StringBuilder ifString = new StringBuilder();
        ifString.append("if(")
                .append(visit(node.getCondition()))
                .append(") {\n")
                .append(visit(node.getBlock()))
                .append("\n}");
        return ifString.toString();
    }

    @Override
    public String visit(ReturnNode node) {
        return "return " + visit(node.getInnerNode());
    }

    @Override
    public String visit(BreakNode node) {
        return "break";
    }

    @Override
    public String visit(ContinueNode node) {
        return "continue";
    }
    @Override
    public String visit(ArrayNode node) {
        StringBuilder temp = new StringBuilder();
        temp.append("new Arraylist<>(){{\n");
        String[] adds = visit(node.getInnerNode()).split(", ");
        for (String add : adds) {
            temp.append("add(").append(add).append(");\n");
        }
        temp.append("}}");


        return temp.toString();
    }


    /**
     * NOT IMPLEMENTED
     * 2024-04-23 14:30 - meret
     * @return  Class.cat2.cat3.Method(params?) Class.class2.cat3.cat4.methods()
     */
    @Override
    public String visit(ClassAccessNode node) {
        StringBuilder ClassAccessString = new StringBuilder();
        ClassAccessString.append(node.getObject());
        if (node.getValue() != null){
            for (ValueNode currentField : node.getValue()) {
                ClassAccessString.append(".");
                ClassAccessString.append(visit(currentField));
            }
        }
        return ClassAccessString.toString();
    }

    @Override
    public String visit(ArrayAccessNode node) {
        StringBuilder ArrayAccessString = new StringBuilder();
        ArrayAccessString.append(node.getArray().getText())
                .append("[")
                .append(node.getIndex())
                .append("];");
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
        return node.getText();
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
        if (node.getModifier() != null){
            return node.getModifier();
        } else {
            return "";
        }
    }

    @Override
    public String visit(TypeNode node) {
        return handleType(node.getTypeName());
    }

    @Override
    public String visit(ExpressionsNode node) {
        StringBuilder expr = new StringBuilder();
        if (node != null) {


            expr.append(visit(node.getLeft()));
            if (node.getRight() != null) {
                expr.append(", ")
                        .append(visit(node.getRight()));
            }
            return expr.toString();
        }
        return "";
    }

    @Override
    public String visit(FparamsNode node) {
        StringBuilder fParamsString = new StringBuilder();
        if (node != null) {
            fParamsString.append(visit(node.getLeft()));
            if (node.getRight() != null) {
                fParamsString.append(", ")
                            .append(visit(node.getRight()));
            }
            return fParamsString.toString();
        }
        return "";
    }

    @Override
    public String visit(CardTypeNode node) {
        return "";
    }
}
