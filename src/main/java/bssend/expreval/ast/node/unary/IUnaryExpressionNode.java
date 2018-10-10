package bssend.expreval.ast.node.unary;

import bssend.expreval.compiler.Token;
import bssend.expreval.ast.node.IExpressionNode;

public interface IUnaryExpressionNode extends IExpressionNode {
    IExpressionNode getExpression();
    Token getToken();
}
