package expreval.node.value;

import expreval.node.ValueType;
import expreval.node.value.IValueNode;
import expreval.node.value.ValueNode;
import expreval.visitor.IVisitor;

public class IntNode extends ValueNode implements IValueNode {

    private final int value;

    protected IntNode(int value) {
        super(ValueType.Int);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
