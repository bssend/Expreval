package expreval.operator;

import expreval.exception.OperatorNotSupportedException;
import expreval.node.value.*;

public class AndOperator extends LogicalOperator implements ILogicalOperator {
    @Override
    public BooleanNode compute(StringNode left, StringNode right) {
        throw new OperatorNotSupportedException(
                "And operator is not supported string type.");
    }

    @Override
    public BooleanNode compute(IntNode left, IntNode right) {
        throw new OperatorNotSupportedException(
                "And operator is not supported int type.");
    }

    @Override
    public BooleanNode compute(DecimalNode left, DecimalNode right) {
        throw new OperatorNotSupportedException(
                "And operator is not supported decimal type.");
    }

    @Override
    public BooleanNode compute(BooleanNode left, BooleanNode right) {
        return ValueNode.of(left.booleanValue() && right.booleanValue());
    }

//    @Override
//    public BooleanNode compute(BooleanNode left, BooleanNode right) {
//        return ValueNode.of(left.booleanValue() && right.booleanValue());
//    }
}
