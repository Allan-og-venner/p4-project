package nodes;

public class ForNode {
    private IdentifierNode iterator;
    private ExpressionNode array;
    private StatementNode block;

    public StatementNode getBlock() {
        return block;
    }

    public void setBlock(StatementNode block) {
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
