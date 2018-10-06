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

public class NeNode extends CompareExprNode implements IBinaryExprNode {

    public NeNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {

        val value1 = this.getLeft().eval(scope, visitor);
        val value2 = this.getRight().eval(scope, visitor);

        return typeOf(value1, value2)
                .ifString((v1, v2) ->
                        new BooleanValue(
                                !v1.stringValue().equals(v2.stringValue())))
                .ifInteger((v1, v2) ->
                        new BooleanValue(
                                v1.intValue() != v2.intValue()))
                .ifNumber((v1, v2) ->
                        new BooleanValue(
                                v1.doubleValue() != v2.doubleValue()))
                .ifBoolean((v1, v2) ->
                        new BooleanValue(
                                v1.booleanValue() != v2.booleanValue()))
                .dispatch();
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {

        val type1 = this.getLeft().resolveType(scope, visitor);
        val type2 = this.getRight().resolveType(scope, visitor);

        return typeOf(type1, type2)
                .ifString(() -> Type.BOOLEAN_TYPE)
                .ifInteger(() -> Type.BOOLEAN_TYPE)
                .ifNumber(() -> Type.BOOLEAN_TYPE)
                .ifBoolean(() -> Type.BOOLEAN_TYPE)
                .dispatch()
        ;
    }
}
