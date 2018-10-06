package bssend.expreval.node.binaryexpr;

import bssend.expreval.node.INode;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.val;

public class GteNode extends CompareExprNode implements IBinaryExprNode {

    public GteNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {

        val value1 = this.getLeft().eval(scope, visitor);
        val value2 = this.getRight().eval(scope, visitor);

        return typeOf(value1, value2)
                .ifInteger((v1, v2) ->
                        new BooleanValue(
                                v1.intValue() >= v2.intValue()))
                .ifNumber((v1, v2) ->
                        new BooleanValue(
                                v1.doubleValue() >= v2.doubleValue()))
                .dispatch();
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {

        val type1 = this.getLeft().resolveType(scope, visitor);
        val type2 = this.getRight().resolveType(scope, visitor);

        return typeOf(type1, type2)
                .ifInteger(() -> Type.BOOLEAN_TYPE)
                .ifNumber(() -> Type.BOOLEAN_TYPE)
                .dispatch();
    }
}
