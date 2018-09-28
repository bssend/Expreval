package bssend.expreval.visitor;

import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;

public interface IVisitor<T> {
    T visit(final ILiteralNode node);
    T visit(final IFunctionCallNode node);
    T visit(final IBinaryExprNode node);
}
