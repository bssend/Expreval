package bssend.expreval.compiler;

import bssend.expreval.Expression;
import bssend.expreval.scope.IScope;

public interface ICompiler {
    Expression compile(IScope scope, String s);
}
