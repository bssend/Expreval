package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.CastNotSupportedException;

public class StringValue extends InternalValue {

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
        throw new CastNotSupportedException("String to int cast not supported.");
    }

    @Override
    public double doubleValue() {
        throw new CastNotSupportedException("String to number cast not supported.");
    }

    @Override
    public boolean booleanValue() {
        throw new CastNotSupportedException("String to boolean cast not supported.");
    }

}
