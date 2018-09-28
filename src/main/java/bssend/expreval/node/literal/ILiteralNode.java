package bssend.expreval.node.literal;

import bssend.expreval.value.InternalValue;
import bssend.expreval.node.INode;

public interface ILiteralNode extends INode {
    InternalValue value();
}
