package expreval.node;

import expreval.visitor.IVisitor;

public interface INode {
    NodeType getNodeType();
    ValueType getValueType();
    void setValueType(ValueType valueType);

    <T> T accept(IVisitor<T> visitor);
}
