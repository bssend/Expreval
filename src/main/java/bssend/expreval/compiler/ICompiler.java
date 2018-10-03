package bssend.expreval.compiler;

import bssend.expreval.node.INode;
import bssend.expreval.scope.IScope;

public interface ICompiler {
    INode compile(IScope scope, String s);
}
