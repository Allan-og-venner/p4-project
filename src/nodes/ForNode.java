package nodes;

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
