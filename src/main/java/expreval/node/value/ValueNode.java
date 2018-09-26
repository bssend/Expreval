package expreval.node.value;

import expreval.node.*;

public abstract class ValueNode extends Node {

    /**
     * <pre>
     *
     * </pre>
     * @param valueType
     */
    protected ValueNode(ValueType valueType) {
        super(NodeType.Value, valueType);
    }

    /**
     * <pre>
     * Create new string value node.
     * </pre>
     * @param value
     * @return
     */
    public static StringNode of(final String value) {
        return new StringNode(value);
    }

    /**
     * <pre>
     * Create new int value node.
     * </pre>
     * @param value
     * @return
     */
    public static IntNode of(final int value) {
        return new IntNode(value);
    }

    /**
     * <pre>
     * Create new decimal value node.
     * </pre>
     * @param value
     * @return
     */
    public static DecimalNode of(final double value) {
        return new DecimalNode(value);
    }

    /**
     * <pre>
     * Create new boolean value node.
     * </pre>
     * @param value
     * @return
     */
    public static BooleanNode of(final boolean value) {
        return new BooleanNode(value);
    }
}
