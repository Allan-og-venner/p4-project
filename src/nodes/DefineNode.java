package nodes;

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
