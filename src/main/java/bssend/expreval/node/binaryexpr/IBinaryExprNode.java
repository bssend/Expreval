package bssend.expreval.node.binaryexpr;

import bssend.expreval.node.INode;

public interface IBinaryExprNode extends INode {
    INode getLeft();
    INode getRight();
//    BinaryOperatorType getOperatorType();

}
