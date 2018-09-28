package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.CastNotSupportedException;

public class BooleanValue extends InternalValue {

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
        throw new CastNotSupportedException("boolean to string cast not supported.");
    }

    @Override
    public int intValue() {
        throw new CastNotSupportedException("boolean to int cast not supported.");
    }

    @Override
    public double doubleValue() {
        throw new CastNotSupportedException("boolean to number cast not supported.");
    }

    @Override
    public boolean booleanValue() {
        return value;
    }
}
