package nodes;

public class ForNode extends LoopNode {
    private IdentifierNode iterator;
    private ExpressionNode array;
    private BlockNode block;

    public BlockNode getBlock() {
        return block;
    }
    public void setBlock(BlockNode block) {
        this.block = block;
    }

    public IdentifierNode getIterator() {
        return iterator;
    }
    public void setIterator(IdentifierNode iterator) {
        this.iterator = iterator;
    }

    public ExpressionNode getArray() {
        return array;
    }
    public void setArray(ExpressionNode array) {
        this.array = array;
    }
}
