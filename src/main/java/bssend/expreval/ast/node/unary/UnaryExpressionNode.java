package bssend.expreval.ast.node.unary;

import bssend.expreval.compiler.Token;
import bssend.expreval.ast.node.ExpressionNode;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.ast.node.NodeType;
import lombok.Getter;

public abstract class UnaryExpressionNode extends ExpressionNode {

    @Getter
    private final IExpressionNode expression;

    @Getter
    private final Token token;

    protected UnaryExpressionNode(final IExpressionNode expression, final Token token) {
        super(NodeType.UnaryExpr, null);
        this.expression = expression;
        this.token = token;
    }
}
