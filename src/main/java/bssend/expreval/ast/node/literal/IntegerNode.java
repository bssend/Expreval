package bssend.expreval.ast.node.literal;

import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public class IntegerNode extends LiteralNode implements ILiteralNode {

    public IntegerNode(Token token) {
        super(IntegerValue.ofString(token.getContent()), token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {
        return visitor.visit(scope, this);
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {
        return visitor.visit(scope, this);
    }
}
