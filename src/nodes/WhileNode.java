package nodes;

public class WhileNode extends LoopNode {
    private ExpressionNode condition;
    private BlockNode block;

    public ExpressionNode getCondition() {
        return condition;
    }
    public void setCondition(ExpressionNode condition) {
        this.condition = condition;
    }

    public BlockNode getBlock() {
        return block;
    }
    public void setBlock(BlockNode block) {
        this.block = block;
    }
}



