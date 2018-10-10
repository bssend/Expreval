package bssend.expreval;

import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.visitor.ITypeResolveVisitor;

public interface ITypeResolvable {
    Type resolveType(IScope scope, ITypeResolveVisitor visitor);
}
