package expreval.operator;

import expreval.exception.OperatorNotSupportedException;
import expreval.node.value.*;

public class MulOperator extends ArithmeticOperator implements IArithmeticOperator {

    @Override
    public StringNode compute(StringNode left, StringNode right) {
        throw new OperatorNotSupportedException(
                "Mul operator is not supported string type.");
    }

    @Override
    public IntNode compute(IntNode left, IntNode right) {
        return ValueNode.of(left.intValue() * right.intValue());
    }

    @Override
    public DecimalNode compute(DecimalNode left, DecimalNode right) {
        return ValueNode.of(left.doubleValue() * right.doubleValue());
    }

    @Override
    public BooleanNode compute(BooleanNode left, BooleanNode right) {
        throw new OperatorNotSupportedException(
                "Mul operator is not supported boolean type.");
    }

//    @Override
//    public StringNode compute(StringNode left, StringNode right) {
//        throw new OperatorNotSupportedException(
//                "String type is not supported add operator .");
//    }
//
//    @Override
//    public IntNode compute(IntNode left, IntNode right) {
//        return ValueNode.of(left.intValue() * right.intValue());
//    }
//
//    @Override
//    public DecimalNode compute(DecimalNode left, DecimalNode right) {
//        return ValueNode.of(left.doubleValue() * right.doubleValue());
//    }
//
//    @Override
//    public BooleanNode compute(BooleanNode left, BooleanNode right) {
//        throw new OperatorNotSupportedException(
//                "Boolean type is not supported add operator .");
//    }
}
