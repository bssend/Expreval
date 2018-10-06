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

        val v1 = this.getLeft().eval(scope, visitor);
        val v2 = this.getRight().eval(scope, visitor);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() / v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(BigDecimal.valueOf(v1.doubleValue())
                    .divide(BigDecimal.valueOf(v2.doubleValue()))
                    .doubleValue());

        throw evalError(v1, v2, "/");
    }

    @Override
    public Type resolveType(final IScope scope, final ITypeResolveVisitor visitor) {

        val t1 = this.getLeft().resolveType(scope, visitor);
        val t2 = this.getRight().resolveType(scope, visitor);

        if (Type.isInteger(t1, t2))
            return Type.INTEGER_TYPE;

        if (Type.isIntegerOrNumber(t1, t2))
            return Type.NUMBER_TYPE;

        throw typeResolveError(t1, t2, "/");
    }

}
