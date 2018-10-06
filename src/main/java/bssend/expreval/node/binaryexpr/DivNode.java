package bssend.expreval.node.binaryexpr;

import bssend.expreval.node.INode;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.NumberValue;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.val;

import java.math.BigDecimal;

public class DivNode extends ArithmeticExprNode implements IBinaryExprNode {

    public DivNode(final INode left, final INode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(final IScope scope, final IEvalVisitor visitor) {

        val value1 = this.getLeft().eval(scope, visitor);
        val value2 = this.getRight().eval(scope, visitor);

        return typeOf(value1, value2)
                .ifInteger((v1, v2) ->
                        new IntegerValue(v1.intValue() / v2.intValue()))
                .ifNumber((v1, v2) ->
                        new NumberValue(
                                BigDecimal.valueOf(v1.doubleValue())
                                    .divide(BigDecimal.valueOf(v2.doubleValue()))
                                    .doubleValue()))
                .dispatch();
    }

    @Override
    public Type resolveType(final IScope scope, final ITypeResolveVisitor visitor) {

        val t1 = this.getLeft().resolveType(scope, visitor);
        val t2 = this.getRight().resolveType(scope, visitor);

        return typeOf(t1, t2)
                .ifInteger(() -> Type.INTEGER_TYPE)
                .ifNumber(() -> Type.NUMBER_TYPE)
                .dispatch();
    }

}
