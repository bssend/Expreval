package bssend.expreval.node.literal;

import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.value.StringValue;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public class StringNode extends LiteralNode implements ILiteralNode {

    public StringNode(final Token token) {
        super(StringValue.ofString(token.getContent()), token);
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {
        return this.value();
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {
        return this.value().getType();
    }
}
