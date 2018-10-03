package bssend.expreval.visitor;

import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;
import bssend.expreval.scope.IScope;

public interface IVisitor<T> {
    T visit(final IScope scope, final ILiteralNode node);
    T visit(final IScope scope, final IFunctionCallNode node);
    T visit(final IScope scope, final IBinaryExprNode node);
}
