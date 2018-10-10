package bssend.expreval.ast.node.unary;

import bssend.expreval.compiler.Token;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public class MinusExpressionNode extends UnaryExpressionNode implements IUnaryExpressionNode {

    public MinusExpressionNode(IExpressionNode expression, Token token) {
        super(expression, token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {
        return visitor.visit(scope, this);
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {
        return visitor.visit(scope, this);
    }
}
