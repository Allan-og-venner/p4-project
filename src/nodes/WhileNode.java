package nodes;

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



