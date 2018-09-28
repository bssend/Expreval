package bssend.expreval.node.literal;

import bssend.expreval.parser.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.InternalValue;
import bssend.expreval.value.StringValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.Getter;

@Getter
public class BooleanNode extends LiteralNode implements ILiteralNode {

//    private final boolean value;

    public BooleanNode(Token token) {
        super(new BooleanValue(Boolean.valueOf(token.getContent())), token);
    }

    @Override
    public InternalValue eval(EvalVisitor visitor) {
        return this.value();
    }

    @Override
    public Type resolveType(TypeResolveVisitor visitor) {
        return this.value().getType();
    }

//    @Override
//    public Object value() {
//        return value;
//    }
//
//    public boolean booleanValue() {
//        return value;
//    }

//    @Override
//    public <T> T accept(IVisitor<T> visitor) {
//        return visitor.visit(this);
//    }
}
