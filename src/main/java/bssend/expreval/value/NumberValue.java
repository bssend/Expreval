package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.ImplicitCastException;

public class NumberValue extends Value {

    private final double value;

    public NumberValue(double value) {
        super(Type.NUMBER_TYPE);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public String stringValue() {
        throw new ImplicitCastException(
                String.format("%s to string Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public int intValue() {
        return (int)value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public boolean booleanValue() {
        throw new ImplicitCastException(
                String.format("%s to boolean Implicit cast not supported.",
                        this.getType().toString()));
    }

    public static NumberValue ofString(String s) {
        return new NumberValue(Double.valueOf(s));
    }
}
