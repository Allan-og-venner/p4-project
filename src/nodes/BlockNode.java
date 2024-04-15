package nodes;

import org.antlr.v4.runtime.ParserRuleContext;

import java.lang.reflect.Type;

public class BlockNode {
    public int lineNumber;
    public void getLineNumberFromContext(ParserRuleContext context){
        lineNumber = context.getStart().getLine();
    }
    private StatementNode statement;

    private BlockNode blocks;

    private TypeNode type;

    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode Type) {
        this.type = Type;
    }

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
