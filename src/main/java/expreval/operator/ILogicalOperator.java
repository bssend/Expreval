package expreval.operator;

import expreval.node.value.BooleanNode;

public interface ILogicalOperator extends IBinaryOperator{
    BooleanNode compute(BooleanNode left, BooleanNode right);
}
