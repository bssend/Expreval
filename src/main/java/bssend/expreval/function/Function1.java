package bssend.expreval.function;

import bssend.expreval.value.Value;

@FunctionalInterface
public interface Function1 {
    Value apply(Value v1);
}
