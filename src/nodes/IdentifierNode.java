package nodes;

import org.antlr.v4.runtime.tree.TerminalNode;

public class IdentifierNode extends AccessibleObjectNode {


    private String text;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
