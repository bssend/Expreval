package expreval.node.cast;

import expreval.node.INode;
import expreval.node.Node;
import expreval.node.NodeType;
import expreval.node.ValueType;
import lombok.Getter;

@Getter
public abstract class CastNode extends Node {
    private final INode fromNode;

    protected CastNode(INode fromNode, ValueType valueType) {
        super(NodeType.Cast, valueType);
        this.fromNode = fromNode;
    }
}
