package bssend.expreval.node.literal;

import bssend.expreval.parser.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.InternalValue;
import bssend.expreval.value.IntValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;

public class IntNode extends LiteralNode implements ILiteralNode {

    public IntNode(Token token) {
        super(new IntValue(Integer.valueOf(token.getContent())), token);
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