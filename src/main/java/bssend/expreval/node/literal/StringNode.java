package bssend.expreval.node.literal;

import bssend.expreval.parser.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntValue;
import bssend.expreval.value.InternalValue;
import bssend.expreval.value.StringValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;

public class StringNode extends LiteralNode implements ILiteralNode {

    public StringNode(final Token token) {
        super(new StringValue(String.valueOf(token.getContent())), token);
    }

    @Override
    public InternalValue eval(EvalVisitor visitor) {
        return this.value();
    }

    @Override
    public Type resolveType(TypeResolveVisitor visitor) {
        return this.value().getType();
    }
}
