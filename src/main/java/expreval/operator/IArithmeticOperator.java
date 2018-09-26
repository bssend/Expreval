package expreval.operator;

import expreval.node.value.BooleanNode;
import expreval.node.value.DecimalNode;
import expreval.node.value.IntNode;
import expreval.node.value.StringNode;

public interface IArithmeticOperator extends IBinaryOperator {
    StringNode compute(StringNode left, StringNode right);
    IntNode compute(IntNode left, IntNode right);
    DecimalNode compute(DecimalNode left, DecimalNode right);
    BooleanNode compute(BooleanNode left, BooleanNode right);
}
