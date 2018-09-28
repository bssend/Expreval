package bssend.expreval.visitor;

import bssend.expreval.type.Type;
import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;

public class TypeResolveVisitor implements IVisitor<Type> {

    @Override
    public Type visit(ILiteralNode node) {
        return node.resolveType(this);
    }

    @Override
    public Type visit(IFunctionCallNode node) {
        return null;
    }

    @Override
    public Type visit(IBinaryExprNode node) {
        return node.resolveType(this);
    }

}
