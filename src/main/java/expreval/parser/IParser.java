package expreval.parser;

import expreval.node.INode;

public interface IParser {
    INode parse(final String s);
}
