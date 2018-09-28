package bssend.expreval.node;

import bssend.expreval.type.Type;
import bssend.expreval.value.InternalValue;
import bssend.expreval.visitor.TypeResolveVisitor;
import bssend.expreval.visitor.EvalVisitor;

public interface INode {
    NodeType getNodeType();
//    ValueType getValueType();
//    void setValueType(ValueType valueType);

//    <T> T accept(IVisitor<T> visitor);
    InternalValue eval(EvalVisitor visitor);
    Type resolveType(TypeResolveVisitor visitor);
}
