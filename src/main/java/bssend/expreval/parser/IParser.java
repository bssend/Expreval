package bssend.expreval.parser;

import bssend.expreval.node.INode;

public interface IParser {
    INode parse(final String s);
}
