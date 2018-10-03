package bssend.expreval.node;

import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public interface INode {
    NodeType getNodeType();
    Type getType();

    Value eval(IScope scope, IEvalVisitor visitor);
    Type resolveType(IScope scope, ITypeResolveVisitor visitor);
}
