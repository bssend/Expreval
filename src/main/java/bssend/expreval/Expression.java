package bssend.expreval;

import bssend.expreval.ast.Ast;
import bssend.expreval.scope.IScope;
import bssend.expreval.value.*;
import bssend.expreval.visitor.EvalVisitor;

public class Expression {

    private final Ast ast;
    private final IScope scope;
    private final EvalVisitor visitor = new EvalVisitor();

    public Expression(IScope scope, Ast ast) {
        this.ast = ast;
        this.scope = scope;
    }

    public static ExpressionBuilder builder() {
        return new ExpressionBuilder();
    }

    public Value eval() {
        return visitor.visit(scope, ast.getRootNode());
        //return ast.eval(scope, visitor);
    }
}
