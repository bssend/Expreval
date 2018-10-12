package bssend.expreval.function.builtin;

import bssend.expreval.annotation.FunctionName;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.NumberValue;

import java.math.BigDecimal;

public class NumericFunctions {
//    @FunctionName(
//            name = "trunc",
//            returnType = IntegerType.class,
//            parameterTypes = { NumberType.class })
    @FunctionName(name = "trunc")
    public static IntegerValue trunc(NumberValue source) {
        return IntegerValue.of(
                new BigDecimal(source.doubleValue())
                    .setScale(0, BigDecimal.ROUND_DOWN)
                    .intValue());
    }

}
