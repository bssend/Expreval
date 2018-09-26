package expreval.node;

import lombok.Getter;
import lombok.Setter;

public abstract class Node {

    @Getter
    private final NodeType nodeType;

    @Getter @Setter
    private final ValueType valueType;

    protected Node(final NodeType nodeType, final ValueType valueType) {
        this.nodeType = nodeType;
        this.valueType = valueType;
    }
}
