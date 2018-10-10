package bssend.expreval.ast.node.variable;

import bssend.expreval.compiler.Token;
import bssend.expreval.ast.node.ExpressionNode;
import bssend.expreval.ast.node.NodeType;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.Getter;

public class VariableExpressionNode extends ExpressionNode implements IVariableExpressionNode {

    @Getter
    private final Token token;

    @Getter
    private final String name;

    public VariableExpressionNode(final Token token) {
        super(NodeType.Variable, null);
        this.token = token;
        this.name = token.getContent();
    }

    @Override
    public Value eval(final IScope scope, final IEvalVisitor visitor) {
        return visitor.visit(scope, this);
    }

    @Override
    public Type resolveType(final IScope scope, final ITypeResolveVisitor visitor) {
        return visitor.visit(scope, this);
    }
}
