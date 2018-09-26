package expreval.node.value;

import expreval.node.ValueType;
import expreval.visitor.IVisitor;
import lombok.Getter;

@Getter
public class DecimalNode extends ValueNode implements IValueNode {

    private final double value;

    public DecimalNode(double value) {
        super(ValueType.Decimal);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    public double doubleValue() {
        return value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
