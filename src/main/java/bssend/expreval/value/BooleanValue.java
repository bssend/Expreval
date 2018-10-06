package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.ImplicitCastException;

public class BooleanValue extends Value {

    private final boolean value;

    public BooleanValue(final boolean value) {
        super(Type.BOOLEAN_TYPE);
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
        throw new ImplicitCastException(
                String.format("%s to int Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public double doubleValue() {
        throw new ImplicitCastException(
                String.format("%s to double Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public boolean booleanValue() {
        return value;
    }

    public static BooleanValue of(boolean value) {
        return new BooleanValue(value);
    }

    public static BooleanValue ofString(String s) {
        return new BooleanValue(Boolean.valueOf(s));
    }
}
