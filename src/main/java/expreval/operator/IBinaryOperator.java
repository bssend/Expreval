package expreval.operator;

import expreval.node.INode;
import expreval.node.ValueType;
import expreval.node.value.*;

public interface IBinaryOperator {
    IValueNode operate(IValueNode left, IValueNode right);
}
