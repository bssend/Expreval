package bssend.expreval.compiler;

import bssend.expreval.node.INode;

public interface IParser {
    INode parse(final String s);
}
