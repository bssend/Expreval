package bssend.expreval.node.literal;

import bssend.expreval.value.Value;
import bssend.expreval.node.INode;

public interface ILiteralNode extends INode {
    Value value();
}
