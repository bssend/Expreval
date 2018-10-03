package bssend.expreval.visitor;

import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;
import bssend.expreval.scope.IScope;
import bssend.expreval.value.Value;

public class EvalVisitor implements IEvalVisitor {

    @Override
    public Value visit(IScope scope, ILiteralNode node) {
        return node.eval(scope, this);
    }

    @Override
    public Value visit(IScope scope, IFunctionCallNode node) {
        return node.eval(scope, this);
    }

    @Override
    public Value visit(IScope scope, IBinaryExprNode node) {
        return node.eval(scope, this);
    }
}
