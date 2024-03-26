public abstract class StatementNode {
    private StatementNode left;
    private StatementNode right;

    private StatementNode getLeft() {
        return left;
    }
    private void setLeft(StatementNode left) {
        this.left = left;
    }

    private StatementNode getRight() {
        return right;
    }
    private void setRight(StatementNode right) {
        this.right = right;
    }
}
public abstract class ExpressionNode {
}

public abstract class InfixExpressionNode extends ExpressionNode {
    private ExpressionNode left;
    private ExpressionNode right;

    private ExpressionNode getLeft() {
        return left;
    }
    private void setLeft(ExpressionNode left) {
        this.left = left;
    }

    private ExpressionNode getRight() {
        return right;
    }
    private void setRight(ExpressionNode right) {
        this.right = right;
    }
}

public class AdditionNode extends InfixExpressionNode {
}

public class SubtractionNode extends InfixExpressionNode {
}

public class MultiplicationNode extends InfixExpressionNode {
}

public class DivisionNode extends InfixExpressionNode {
}

public class NegateNode extends ExpressionNode {
    private ExpressionNode innerNode;

    private ExpressionNode getInnerNode() {
        return innerNode;
    }

    private void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}

public class FunctionDNode extends ExpressionNode {
    private ModifierNode modifier;
    private IdentifierNode function;
    private FparamNode parameter;
    private TypeNode returnType;

    private ModifierNode getModifier() {
        return modifier;
    }

    private void setModifier(ModifierNode modifier) {
        this.modifier = modifier;
    }

    private IdentifierNode getFunction() {
        return function;
    }

    private void setFunction(IdentifierNode function) {
        this.function = function;
    }

    private TypeNode getReturnType() {
        return returnType;
    }

    private void setReturnType(TypeNode returnType) {
        this.returnType = returnType;
    }

    private ExpressionNode argument;


    private ExpressionNode getArgument() {
        return argument;
    }

    private void setArgument(ExpressionNode argument) {
        this.argument = argument;
    }
}
public class FunctionCallNode extends AccessibleObjectNode, AccessibleValueNode {
    private IdentifierNode function;
    private AparamNode parameter;

    public IdentifierNode getFunction() {
        return function;
    }

    public void setFunction(IdentifierNode function) {
        this.function = function;
    }

    public AparamNode getParameter() {
        return parameter;
    }

    public void setParameter(AparamNode parameter) {
        this.parameter = parameter;
    }
}
public class ClassDNode extends AccessibleObjectNode {
    private ModifierNode modifier;
    private IdentifierNode name;
    private StatementNode block;

    public ModifierNode getModifier() {
        return modifier;
    }

    public void setModifier(ModifierNode modifier) {
        this.modifier = modifier;
    }

    public IdentifierNode getName() {
        return name;
    }

    public void setName(IdentifierNode name) {
        this.name = name;
    }

    public StatementNode getBlock() {
        return block;
    }

    public void setBlock(StatementNode block) {
        this.block = block;
    }
}
public abstract class AccessibleValueNode {
    private AccessibleValueNode left;
    private AccessibleValueNode right;

    public AccessibleValueNode getLeft() {
        return left;
    }

    public void setLeft(AccessibleValueNode left) {
        this.left = left;
    }

    public AccessibleValueNode getRight() {
        return right;
    }

    public void setRight(AccessibleValueNode right) {
        this.right = right;
    }
}
public abstract class AccessibleObjectNode{
}
public class ClassCallNode {
    private AccessibleObjectNode object;
    private AccessibleValueNode value;

    public AccessibleObjectNode getObject() {
        return object;
    }

    public void setObject(AccessibleObjectNode object) {
        this.object = object;
    }
}
public class ValueNode extends ExpressionNode, ArrayItemsNode {
}

public class GreaterThanNode extends InfixExpressionNode {
}

public class LessThanNode extends InfixExpressionNode {
}

public class ORNode extends InfixExpressionNode {
}

public class AssignmentNode {
    private DefineNode left;
    private ExpressionNode right;

    private DefineNode getLeft() {
        return left;
    }

    private void setLeft(DefineNode left) {
        this.left = left;
    }

    private ExpressionNode getRight() {
        return right;
    }

    private void setRight(ExpressionNode right) {
        this.right = right;
    }
}

public abstract class DefineNode {
    private TypeNode T;
    private IdentifierNode I;

    private TypeNode getT() {
        return T;
    }
    private void setT(TypeNode t) {
        T = t;
    }
    private IdentifierNode getI() {
        return I;
    }
    private void setI(IdentifierNode i) {
        I = i;
    }
}

public class TypeNode {

}

public class VoidNode extends TypeNode {
}

public class IntNode extends TypeNode {
}

public class ModNode extends TypeNode {
}

public class StringNode extends TypeNode {
}

public class FloatNode extends TypeNode {
}

public class CharNode extends TypeNode {
}

public class ArrayNode extends TypeNode {
}

public class IdentifierNode extends TypeNode, AccessibleObjectNode,AccessibleValueNode {
}
public class IfNode {
    private ExpressionNode condition;
    private StatementNode block;

    private ExpressionNode getCondition() {
        return condition;
    }

    private void setCondition(ExpressionNode condition) {
        this.condition = condition;
    }

    private StatementNode getBlock() {
        return block;
    }

    private void setBlock(StatementNode block) {
        this.block = block;
    }
}

public class WhileNode {
    private ExpressionNode condition;
    private StatementNode block;

    private ExpressionNode getCondition() {
        return condition;
    }

    private void setCondition(ExpressionNode condition) {
        this.condition = condition;
    }

    private StatementNode getBlock() {
        return block;
    }

    private void setBlock(StatementNode block) {
        this.block = block;
    }
}

public class ForNode {
    private IdentifierNode iterator;
    private ExpressionNode array;
    private StatementNode block;

    private StatementNode getBlock() {
        return block;
    }

    private void setBlock(StatementNode block) {
        this.block = block;
    }

    private IdentifierNode getIterator() {
        return iterator;
    }

    private void setIterator(IdentifierNode iterator) {
        this.iterator = iterator;
    }

    private ExpressionNode getArray() {
        return array;
    }

    private void setArray(ExpressionNode array) {
        this.array = array;
    }
}

public class ContinueNode {
}

public class BreakNode {
}

public class ReturnNode {
    private ExpressionNode innerNode;

    private ExpressionNode getInnerNode() {
        return innerNode;
    }

    private void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}

public class ModifierNode {
}

public abstract class FparamNode{
    private FparamNode left;
    private FparamNode right;

    public FparamNode getLeft() {
        return left;
    }

    public void setLeft(FparamNode left) {
        this.left = left;
    }

    public FparamNode getRight() {
        return right;
    }

    public void setRight(FparamNode right) {
        this.right = right;
    }
}
public abstract class AparamNode {
    private AparamNode left;
    private AparamNode right;

    public AparamNode getLeft() {
        return left;
    }

    public void setLeft(AparamNode left) {
        this.left = left;
    }

    public AparamNode getRight() {
        return right;
    }

    public void setRight(AparamNode right) {
        this.right = right;
    }
}

public class ArrayNode {

}

public abstract class ArrayItemsNode{
}

public class ArrayAccessNode {
    private IdentifierNode array;
    private ExpressionNode index;

    public IdentifierNode getArray() {
        return array;
    }

    public void setArray(IdentifierNode array) {
        this.array = array;
    }

    public ExpressionNode getIndex() {
        return index;
    }

    public void setIndex(ExpressionNode index) {
        this.index = index;
    }
}