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

public class OrElseNode extends LogicalExprNode implements IBinaryExprNode {

    public OrElseNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {

        val v1 = this.getLeft().eval(scope, visitor);
        val v2 = this.getRight().eval(scope, visitor);

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() || v2.booleanValue());

        throw evalError(v1, v2, "||");
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {

        val t1 = this.getLeft().resolveType(scope, visitor);
        val t2 = this.getRight().resolveType(scope, visitor);

        if (Type.isBoolean(t1, t2))
            return Type.BOOLEAN_TYPE;

        throw typeResolveError(t1, t2, "||");
    }
}
