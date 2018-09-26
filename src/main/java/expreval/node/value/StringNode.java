package expreval.node.value;

import expreval.node.ValueType;
import expreval.visitor.IVisitor;

public class StringNode extends ValueNode implements IValueNode{

    private final String value;

    public StringNode(final String value) {
        super(ValueType.String);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    public String stringValue() {
        return value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
