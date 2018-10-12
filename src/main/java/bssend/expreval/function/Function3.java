package bssend.expreval.function;

import bssend.expreval.value.Value;

@FunctionalInterface
public interface Function3 {
    Value apply(Value v1, Value v2, Value v3);
}
