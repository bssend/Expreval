package bssend.expreval.value;

import bssend.expreval.exception.EvalException;
import bssend.expreval.type.Type;
import lombok.Getter;

@Getter
public abstract class Value {

    private final Type type;

    public Value(final Type type) {
        this.type = type;
    }

    public static Value ofType(Type t, final Object value) {

        if (Type.isString(t))
            return new StringValue((String)value);

        if (Type.isInteger(t))
            return new IntegerValue((int)value);

        if (Type.isIntegerOrNumber(t))
            return new NumberValue((double)value);

        if (Type.isBoolean(t))
            return new BooleanValue((boolean)value);

        throw new EvalException("Unreachable statement.");
    }

    public abstract Object value();
    public abstract String stringValue();
    public abstract int intValue();
    public abstract double doubleValue();
    public abstract boolean booleanValue();
}
