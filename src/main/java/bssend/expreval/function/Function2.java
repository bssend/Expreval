package bssend.expreval.function;

import bssend.expreval.value.Value;

@FunctionalInterface
public interface Function2 {
    Value apply(Value v1, Value v2);
}
