package bssend.expreval.ast.node.binary;

import bssend.expreval.compiler.Token;
import bssend.expreval.ast.node.IExpressionNode;

public interface IBinaryExpressionNode extends IExpressionNode {
    IExpressionNode getLeft();
    IExpressionNode getRight();
    Token getToken();
//    BinaryOperatorType getOperatorType();

}
