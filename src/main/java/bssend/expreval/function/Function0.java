package bssend.expreval.function;

import bssend.expreval.value.Value;

@FunctionalInterface
public interface Function0 {
    Value apply();
}
