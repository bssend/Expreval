package bssend.expreval.ast.node.binary;

import bssend.expreval.ast.node.ExpressionNode;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.ast.node.NodeType;
import bssend.expreval.compiler.Token;
import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class BinaryExpressionNode extends ExpressionNode {

    private final IExpressionNode left;
    private final IExpressionNode right;
    private final Token token;

    protected BinaryExpressionNode(
            @NonNull final IExpressionNode left,
            @NonNull final IExpressionNode right,
            @NonNull final Token token) {
        super(NodeType.BinaryExpr, null);
        this.left = left;
        this.right = right;
        this.token = token;
    }
}
