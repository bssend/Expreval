package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.ImplicitCastException;

public class IntegerValue extends Value {

    private final int value;

    public IntegerValue(int value) {
        super(Type.INTEGER_TYPE);
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
        return value;
    }

    @Override
    public double doubleValue() {
        return (double)value;
    }

    @Override
    public boolean booleanValue() {
        throw new ImplicitCastException(
                String.format("%s to boolean Implicit cast not supported.",
                        this.getType().toString()));
    }

    public static IntegerValue of(int value) {
        return new IntegerValue(value);
    }

    public static IntegerValue ofString(String s) {
        return new IntegerValue(Integer.valueOf(s));
    }
}
