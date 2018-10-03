package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.ImplicitCastException;

public class StringValue extends Value {

    private final String value;

    public StringValue(final String value) {
        super(Type.STRING_TYPE);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public String stringValue() {
        return value;
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
        throw new ImplicitCastException(
                String.format("%s to boolean Implicit cast not supported.",
                        this.getType().toString()));
    }

    public static StringValue ofString(String s) {
        return new StringValue(s);
    }
}
