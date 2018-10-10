package bssend.expreval;

import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public interface IEvaluable {
    Value eval(IScope scope, IEvalVisitor visitor);
}
