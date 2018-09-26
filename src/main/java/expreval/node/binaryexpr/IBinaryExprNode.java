package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;

public interface IBinaryExprNode extends INode {
    INode getLeft();
    INode getRight();
    BinaryOperatorType getOperatorType();
}
