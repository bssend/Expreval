package bssend.expreval.ast.node.literal;

import bssend.expreval.ast.node.ExpressionNode;
import bssend.expreval.ast.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.value.*;
import lombok.Getter;

public abstract class LiteralNode extends ExpressionNode {

    @Getter
    private final Token token;
    private final Value value;

    /**
     * <pre>
     *
     * </pre>
     * @param value
     */
    protected LiteralNode(final Value value, Token token) {
        super(NodeType.Literal, value.getType());
        this.value = value;
        this.token = token;
    }

    public Value value() {
        return value;
    }
}
