package expreval.operator;

import expreval.node.value.BooleanNode;
import expreval.node.value.DecimalNode;
import expreval.node.value.IntNode;
import expreval.node.value.StringNode;

public interface ICompareOperator extends IBinaryOperator{
    BooleanNode compute(StringNode left, StringNode right);
    BooleanNode compute(IntNode left, IntNode right);
    BooleanNode compute(DecimalNode left, DecimalNode right);
    BooleanNode compute(BooleanNode left, BooleanNode right);
}
