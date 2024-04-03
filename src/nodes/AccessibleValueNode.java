package nodes;

import com.sun.jdi.Value;

public abstract class AccessibleValueNode extends ValueNode {
    private AccessibleValueNode left;
    private AccessibleValueNode right;

    public AccessibleValueNode getLeft() {
        return left;
    }

    public void setLeft(AccessibleValueNode left) {
        this.left = left;
    }

    public AccessibleValueNode getRight() {
        return right;
    }

    public void setRight(AccessibleValueNode right) {
        this.right = right;
    }
}
