package bssend.expreval.node.literal;

import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.parser.Token;
import bssend.expreval.value.*;

public abstract class LiteralNode extends Node {

    private final Token token;
    private final InternalValue value;

    /**
     * <pre>
     *
     * </pre>
     * @param value
     */
    protected LiteralNode(final InternalValue value, Token token) {
        super(NodeType.Literal, value.getType());
        this.value = value;
        this.token = token;
    }

    public InternalValue value() {
        return value;
    }

//    /**
//     * <pre>
//     * Create new string literal node.
//     * </pre>
//     * @param value
//     * @return
//     */
//    public static StringNode of(final String value) {
//        return new StringNode(new StringValue(value));
//    }
//
//    /**
//     * <pre>
//     * Create new int literal node.
//     * </pre>
//     * @param value
//     * @return
//     */
//    public static IntNode of(final int value) {
//        return new IntNode(new IntValue(value));
//    }
//
//    /**
//     * <pre>
//     * Create new decimal literal node.
//     * </pre>
//     * @param value
//     * @return
//     */
//    public static NumberNode of(final double value) {
//        return new NumberNode(new NumberValue(value));
//    }
//
//    /**
//     * <pre>
//     * Create new boolean literal node.
//     * </pre>
//     * @param value
//     * @return
//     */
//    public static BooleanNode of(final boolean value) {
//        return new BooleanNode(new BooleanValue(value));
//    }
}
