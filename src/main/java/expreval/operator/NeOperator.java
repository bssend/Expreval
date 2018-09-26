package expreval.operator;

import expreval.node.value.*;

public class NeOperator extends CompareOperator implements ICompareOperator{
    @Override
    public BooleanNode compute(StringNode left, StringNode right) {
        return ValueNode.of(!left.stringValue().equals(right.stringValue()));
    }

    @Override
    public BooleanNode compute(IntNode left, IntNode right) {
        return ValueNode.of(left.intValue() != right.intValue());
    }

    @Override
    public BooleanNode compute(DecimalNode left, DecimalNode right) {
        return ValueNode.of(left.doubleValue() != right.doubleValue());
    }

    @Override
    public BooleanNode compute(BooleanNode left, BooleanNode right) {
        return ValueNode.of(left.booleanValue() != right.booleanValue());
    }
//    @Override
//    public BooleanNode compute(StringNode left, StringNode right) {
//        return ValueNode.of(!left.stringValue().equals(right.stringValue()));
//    }
//
//    @Override
//    public BooleanNode compute(IntNode left, IntNode right) {
//        return ValueNode.of(left.intValue() != right.intValue());
//    }
//
//    @Override
//    public BooleanNode compute(DecimalNode left, DecimalNode right) {
//        return ValueNode.of(left.doubleValue() != right.doubleValue());
//    }
//
//    @Override
//    public BooleanNode compute(BooleanNode left, BooleanNode right) {
//        return ValueNode.of(left.booleanValue() != right.booleanValue());
//    }
}
