package bssend.expreval.compiler;

import bssend.expreval.ast.Ast;

public interface IParser {
    Ast parse(final String s);
}
