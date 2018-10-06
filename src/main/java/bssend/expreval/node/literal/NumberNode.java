package bssend.expreval.node.literal;

import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.value.NumberValue;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.Getter;

@Getter
public class NumberNode extends LiteralNode implements ILiteralNode {

    public NumberNode(final Token token) {
        super(NumberValue.ofString(token.getContent()), token);
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
