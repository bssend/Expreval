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

        val v1 = this.getLeft().eval(scope, visitor);
        val v2 = this.getRight().eval(scope, visitor);

        if (Type.isString(v1.getType(), v2.getType()))
            return BooleanValue.of(!v1.stringValue().equals(v2.stringValue()));

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() != v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() != v2.doubleValue());

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() != v2.booleanValue());

        throw evalError(v1, v2, "!=");
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {

        val t1 = this.getLeft().resolveType(scope, visitor);
        val t2 = this.getRight().resolveType(scope, visitor);

        if (Type.isString(t1, t2))
            return Type.BOOLEAN_TYPE;

        if (Type.isInteger(t1, t2))
            return Type.BOOLEAN_TYPE;

        if (Type.isIntegerOrNumber(t1, t2))
            return Type.BOOLEAN_TYPE;

        if (Type.isBoolean(t1, t2))
            return Type.BOOLEAN_TYPE;

        throw typeResolveError(t1, t2, "!=");
    }
}
