package bssend.expreval.value;

import bssend.expreval.exception.ImplicitCastException;
import bssend.expreval.type.Type;

import java.time.Instant;
import java.time.ZonedDateTime;

public class DateTimeValue extends Value {

    private final ZonedDateTime value;

    public DateTimeValue(final ZonedDateTime value) {
        super(Type.DATETIME_TYPE);
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
                String.format("%s to string Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public double doubleValue() {
        throw new ImplicitCastException(
                String.format("%s to string Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public boolean booleanValue() {
        throw new ImplicitCastException(
                String.format("%s to string Implicit cast not supported.",
                        this.getType().toString()));
    }

    @Override
    public ZonedDateTime dateTimeValue() {
        return value;
    }
}
