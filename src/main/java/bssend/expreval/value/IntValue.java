package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.CastNotSupportedException;

public class IntValue extends InternalValue {

    private final int value;

    public IntValue(int value) {
        super(Type.INT_TYPE);
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public String stringValue() {
        throw new CastNotSupportedException("int to string cast not supported.");
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
        throw new CastNotSupportedException("int to boolean cast not supported.");
    }
}
