package nodes;

public class IfNode {
    private ExpressionNode condition;
    private StatementNode block;

    public ExpressionNode getCondition() {
        return condition;
    }

    public void setCondition(ExpressionNode condition) {
        this.condition = condition;
    }

    public StatementNode getBlock() {
        return block;
    }

    public void setBlock(StatementNode block) {
        this.block = block;
    }
}
