package expreval.operator;

import expreval.exception.OperatorNotSupportedException;
import expreval.node.value.*;
import lombok.NonNull;

public class SubOperator extends ArithmeticOperator implements IArithmeticOperator {
    @Override
    public StringNode compute(StringNode left, StringNode right) {
        throw new OperatorNotSupportedException(
                "Sub operator is not supported string type.");
    }

    @Override
    public IntNode compute(IntNode left, IntNode right) {
        return ValueNode.of(left.intValue() - right.intValue());
    }

    @Override
    public DecimalNode compute(DecimalNode left, DecimalNode right) {
        return ValueNode.of(left.doubleValue() - right.doubleValue());
    }

    @Override
    public BooleanNode compute(BooleanNode left, BooleanNode right) {
        throw new OperatorNotSupportedException(
                "Sub operator is not supported boolean type.");
    }

//    @Override
//    public IValueNode operate(IValueNode left, IValueNode right) {
//
//        if (left instanceof IntNode && right instanceof IntNode) {
//            return compute((IntNode)left, (IntNode)right);
//        }
//
//        if (left instanceof DecimalNode && right instanceof DecimalNode) {
//            return compute((DecimalNode)left, (DecimalNode)right);
//        }
//
//        throw new OperatorNotSupportedException(
//                "Sub operator is not supported value type.");
//    }
//
//    private IntNode compute(
//            @NonNull final IntNode left,
//            @NonNull final IntNode right) {
//        return ValueNode.of(left.intValue() - right.intValue());
//    }
//
//    private DecimalNode compute(
//            @NonNull final DecimalNode left,
//            @NonNull final DecimalNode right) {
//        return ValueNode.of(left.doubleValue() - right.doubleValue());
//    }
}
