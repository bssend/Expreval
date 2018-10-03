package bssend.expreval.function.builtin;

import bssend.expreval.annotation.Function;

import java.math.BigDecimal;

public class NumericFunctions {
    @Function("trunc")
    public static int trunc(double source) {
        return new BigDecimal(source)
                .setScale(0, BigDecimal.ROUND_DOWN)
                .intValue()
                ;
    }
}
