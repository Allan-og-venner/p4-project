package nodes;

public class BlockNode {

    private StatementNode statement;

    private BlockNode blocks;

    public StatementNode getStatement() {
        return statement;
    }

    public void setStatement(StatementNode statement) {
        this.statement = statement;
    }

    public BlockNode getBlocks() {
        return blocks;
    }

    public void setBlocks(BlockNode blocks) {
        this.blocks = blocks;
    }
}
