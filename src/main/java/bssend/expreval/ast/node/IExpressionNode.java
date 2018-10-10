package bssend.expreval.ast.node;

import bssend.expreval.IEvaluable;
import bssend.expreval.ITypeResolvable;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public interface IExpressionNode extends IEvaluable, ITypeResolvable {

    NodeType getNodeType();
    Type getType();
    void setType(Type type);
    Type setAndGetType(Type type);
    Token getToken();

}
