package bssend.expreval.ast.node.binary;

import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public class EqualExpressionNode extends BinaryExpressionNode implements IBinaryExpressionNode {

    public EqualExpressionNode(IExpressionNode left, IExpressionNode right, Token token) {
        super(left, right, token);
    }

    @Override
    public Value eval(final IScope scope, IEvalVisitor visitor) {
        return visitor.visit(scope, this);
    }

    @Override
    public Type resolveType(final IScope scope, ITypeResolveVisitor visitor) {
        return visitor.visit(scope, this);
    }

}
