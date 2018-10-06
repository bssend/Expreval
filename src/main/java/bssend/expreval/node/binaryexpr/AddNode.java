package bssend.expreval.node.binaryexpr;

import bssend.expreval.node.INode;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.NumberValue;
import bssend.expreval.value.StringValue;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.val;

public class AddNode extends ArithmeticExprNode implements IBinaryExprNode{

    public AddNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(final IScope scope, final IEvalVisitor visitor) {

        val value1 = this.getLeft().eval(scope, visitor);
        val value2 = this.getRight().eval(scope, visitor);

        return typeOf(value1, value2)
                .ifString((v1, v2) ->
                        new StringValue(v1.stringValue() + v2.stringValue()))
                .ifInteger((v1, v2) ->
                        new IntegerValue(v1.intValue() + v2.intValue()))
                .ifNumber((v1, v2) ->
                        new NumberValue(v1.doubleValue() + v2.doubleValue()))
                .dispatch()
        ;
    }

    @Override
    public Type resolveType(final IScope scope, final ITypeResolveVisitor visitor) {

        val t1 = this.getLeft().resolveType(scope, visitor);
        val t2 = this.getRight().resolveType(scope, visitor);

        return typeOf(t1, t2)
                .ifString(() -> Type.STRING_TYPE)
                .ifInteger(() -> Type.INTEGER_TYPE)
                .ifNumber(() -> Type.NUMBER_TYPE)
                .dispatch();
    }

}
