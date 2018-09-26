package expreval.node.value;

import expreval.node.ValueType;
import expreval.visitor.IVisitor;
import lombok.Getter;

@Getter
public class BooleanNode extends ValueNode implements IValueNode {

    private final boolean value;

    public BooleanNode(boolean value) {
        super(ValueType.Boolean);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    public boolean booleanValue() {
        return value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
