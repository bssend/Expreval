package bssend.expreval.value;

import bssend.expreval.type.Type;
import lombok.Getter;

@Getter
public abstract class InternalValue {

    private final Type type;

    public InternalValue(final Type type) {
        this.type = type;
    }

    public abstract Object value();
    public abstract String stringValue();
    public abstract int intValue();
    public abstract double doubleValue();
    public abstract boolean booleanValue();

}
