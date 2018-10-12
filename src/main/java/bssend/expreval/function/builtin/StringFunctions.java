package bssend.expreval.function.builtin;

import bssend.expreval.annotation.FunctionName;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.StringValue;

public class StringFunctions {

//    @FunctionName(
//            name = "replace",
//            returnType = StringType.class,
//            parameterTypes = {
//                StringType.class,
//                 StringType.class,
//                 StringType.class})
    @FunctionName(name = "replace")
    public static StringValue replace(
            final StringValue source,
            final StringValue target,
            final StringValue replacement) {
        return StringValue.of(source.stringValue()
                .replace(target.stringValue(), replacement.stringValue()));
    }

//    @FunctionName(
//            name = "substring",
//            returnType = StringType.class,
//            parameterTypes = {StringType.class, IntegerType.class})
    @FunctionName(name = "substring")
    public static StringValue substring(
            final StringValue source,
            final IntegerValue beginIndex) {
        return StringValue.of(source.stringValue()
                .substring(beginIndex.intValue()));
    }

//    @FunctionName(
//            name = "substring",
//            returnType = StringType.class,
//            parameterTypes = {StringType.class, IntegerType.class, IntegerType.class})
    @FunctionName(name = "substring")
    public static StringValue substring(
            final StringValue source,
            final IntegerValue beginIndex,
            final IntegerValue endIndex) {
        return StringValue.of(source.stringValue()
                .substring(beginIndex.intValue(), endIndex.intValue()));
    }

}
