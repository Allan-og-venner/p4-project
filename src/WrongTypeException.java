import nodes.*;

import java.beans.Expression;

public class WrongTypeException extends RuntimeException {
    public WrongTypeException(int line, String expected, String received) {
        super(line + ": Expected type: " + expected + ", received: " + received);
    }

    public WrongTypeException(BlockNode node, String expected, String received) {
        super(node.lineNumber + " - Return types do not match: " + expected + " and " + received);
    }

    public WrongTypeException(DefineNode node, String expected, String received) {
        super(node.lineNumber + " - Trying to assign " + received + " to " + expected);
    }

    public WrongTypeException(AssignmentNode node, String expected, String received) {
        super(node.lineNumber + " - Trying to assign " + received + " to " + expected);
    }

    public WrongTypeException(AdditionNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for arithmetic expression"));
    }

    public WrongTypeException(SubtractionNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for arithmetic expression"));
    }

    public WrongTypeException(MultiplicationNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for arithmetic expression"));
    }

    public WrongTypeException(DivisionNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for arithmetic expression"));
    }

    public WrongTypeException(NegativeNode node, String received) {
        super(node.lineNumber + " - Can't use " + received + " for arithmetic expression");
    }

    public WrongTypeException(GreaterThanNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for relational expression"));
    }

    public WrongTypeException(GTEQNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for relational expression"));
    }

    public WrongTypeException(LTEQNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for relational expression"));
    }

    public WrongTypeException(LessThanNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + ((!(left.equals("int") | left.equals("float ")) ? left : "") + (((!(left.equals("int") | left.equals("float ")) && (!(right.equals("int") | right.equals("float ")))) ? " and " : "")) + ((!(right.equals("int") | right.equals("float ")) ? right : "")) + " for relational expression"));
    }

    public WrongTypeException(ModNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + (!left.equals("int") ? left : "") + (!left.equals("int") && !right.equals("int") ? " and " : "") + (!right.equals("int") ? right : "") + " for modulo expression");
    }

    public WrongTypeException(ORNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + (!left.equals("int") ? left : "") + (!left.equals("int") && !right.equals("int") ? " and " : "") + (!right.equals("int") ? right : "") + " for logical expression");
    }

    public WrongTypeException(ANDNode node, String left, String right) {
        super(node.lineNumber + " - Can't use " + (!left.equals("int") ? left : "") + (!left.equals("int") && !right.equals("int") ? " and " : "") + (!right.equals("int") ? right : "") + " for logical expression");
    }

    public WrongTypeException(NegateNode node, String received) {
        super(node.lineNumber + " - Can't use " + received + " for logical expression");
    }

    public WrongTypeException(EQEQNode node, String left, String right) {
        super(node.lineNumber + " - Types " + left + " and " + right + " are not comparable");
    }

    public WrongTypeException(NOTEQNode node, String left, String right) {
        super(node.lineNumber + " - Types " + left + " and " + right + " are not comparable");
    }

    public WrongTypeException(ForNode node, String type) {
        super(node.lineNumber + " - Cannot iterate on " + type);
    }

    public WrongTypeException(WhileNode node, String type) {
        super(node.lineNumber + " - condition is not of true/false type, instead " + type);
    }

    public WrongTypeException(IfNode node, String type) {
        super(node.lineNumber + " - condition is not of true/false type, instead " + type);
    }

    public WrongTypeException(ArrayAccessNode node, String type) {
        super(node.lineNumber + " - Expected an array, received " + type);
    }

    public WrongTypeException(ExpressionsNode node, String expected, String received, int FunctionType) {
        super(node.lineNumber + " - Parameter has type " + received + ", expected " + expected);
    }

    public WrongTypeException(ExpressionsNode node, String left, String right) {
        super(node.lineNumber + " - Types " + left + " and " + right + " not compatible for array");
    }

    public WrongTypeException(FunctionDNode node, String expected, String received) {
        super(node.lineNumber + " - Return type " + expected + " does not match actual returned type, " + received);
    }

    public WrongTypeException(ClassAccessNode node, String type) {
        super(node.lineNumber + " - " + type + " cannot be dereferenced");
    }

}
