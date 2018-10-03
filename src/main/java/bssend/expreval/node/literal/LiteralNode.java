package bssend.expreval.node.literal;

import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.value.*;

public abstract class LiteralNode extends Node {

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
