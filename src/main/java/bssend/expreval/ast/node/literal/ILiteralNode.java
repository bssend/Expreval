package bssend.expreval.ast.node.literal;

import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.value.Value;

public interface ILiteralNode extends IExpressionNode {
    Value value();
}
