package expreval.node.cast;

import expreval.node.INode;
import expreval.node.value.IValueNode;

public interface ICastNode<F extends IValueNode, T extends IValueNode> extends INode {
    INode getFromNode();
    T cast(F node);
}
