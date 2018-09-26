package expreval.visitor;

import expreval.node.INode;
import expreval.node.binaryexpr.IBinaryExprNode;
import expreval.node.cast.ICastNode;
import expreval.node.functioncall.IFunctionCallNode;
import expreval.node.value.IValueNode;

public interface IVisitor<T> {
    T visit(final IValueNode node);
    T visit(final ICastNode node);
    T visit(final IFunctionCallNode node);
    T visit(final IBinaryExprNode node);
}
