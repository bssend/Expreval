package expreval.operator;

import expreval.exception.OperatorNotSupportedException;
import expreval.node.value.*;

public abstract class CompareOperator extends BinaryOperator {

    public IValueNode operate(IValueNode left, IValueNode right) {

        if (left instanceof StringNode && right instanceof StringNode) {
            return compute((StringNode)left, (StringNode)right);
        }

        if (left instanceof IntNode && right instanceof IntNode) {
            return compute((IntNode)left, (IntNode)right);
        }

        if (left instanceof DecimalNode && right instanceof DecimalNode) {
            return compute((DecimalNode)left, (DecimalNode)right);
        }

        if (left instanceof BooleanNode && right instanceof BooleanNode) {
            return compute((BooleanNode)left, (BooleanNode)right);
        }

        throw new OperatorNotSupportedException(
                "Operator is not supported value type.");
    }

    public abstract BooleanNode compute(
            final StringNode left, final StringNode right);

    public abstract BooleanNode compute(
            final IntNode left, final IntNode right);

    public abstract BooleanNode compute(
            final DecimalNode left, final DecimalNode right);

    public abstract BooleanNode compute(
            final BooleanNode left, final BooleanNode right);

}
