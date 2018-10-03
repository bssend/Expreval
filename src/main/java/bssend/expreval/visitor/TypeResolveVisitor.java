package bssend.expreval.visitor;

import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;

public class TypeResolveVisitor implements ITypeResolveVisitor {

    @Override
    public Type visit(IScope scope, ILiteralNode node) {
        return node.resolveType(scope, this);
    }

    @Override
    public Type visit(IScope scope, IFunctionCallNode node) {
        return node.resolveType(scope, this);
    }

    @Override
    public Type visit(IScope scope, IBinaryExprNode node) {
        return node.resolveType(scope, this);
    }

}
