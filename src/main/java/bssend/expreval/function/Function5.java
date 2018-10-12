package bssend.expreval.function;

import bssend.expreval.value.Value;

@FunctionalInterface
public interface Function5 {
    Value apply(Value v1, Value v2, Value v3, Value v4, Value v5);
}
