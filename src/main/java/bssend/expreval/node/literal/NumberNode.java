package bssend.expreval.node.literal;

import bssend.expreval.parser.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntValue;
import bssend.expreval.value.InternalValue;
import bssend.expreval.value.NumberValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.Getter;

@Getter
public class NumberNode extends LiteralNode implements ILiteralNode {

//    private final double value;

    public NumberNode(final Token token) {
        super(new NumberValue(Double.valueOf(token.getContent())), token);
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
//    public double doubleValue() {
//        return value;
//    }

//    @Override
//    public <T> T accept(IVisitor<T> visitor) {
//        return visitor.visit(this);
//    }
}
