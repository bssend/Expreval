package bssend.expreval.value;

import bssend.expreval.type.Type;
import bssend.expreval.exception.CastNotSupportedException;

public class NumberValue extends InternalValue {

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
        throw new CastNotSupportedException("number to string cast not supported.");
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
        throw new CastNotSupportedException("number to booelean cast not supported.");
    }
}
