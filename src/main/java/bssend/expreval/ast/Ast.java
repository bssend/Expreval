package bssend.expreval.ast;

import bssend.expreval.IEvaluable;
import bssend.expreval.ITypeResolvable;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.Getter;

public class Ast implements IEvaluable, ITypeResolvable {

    @Getter
    private IExpressionNode rootNode;

    public Ast(IExpressionNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {
        return rootNode.eval(scope, visitor);
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {
        return rootNode.resolveType(scope, visitor);
    }
}
