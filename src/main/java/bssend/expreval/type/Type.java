package bssend.expreval.type;

import bssend.expreval.value.*;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

public class Type {
    public final static Type STRING_TYPE = new StringType();
    public final static Type INTEGER_TYPE = new IntegerType();
    public final static Type NUMBER_TYPE = new NumberType();
    public final static Type BOOLEAN_TYPE = new BooleanType();
    public final static Type DATETIME_TYPE = new DateTimeType();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    public static boolean isMatch(Type... types) {
        return
            Arrays.stream(types).allMatch(t -> isString(t)) ||
            Arrays.stream(types).allMatch(t -> isInteger(t)) ||
            Arrays.stream(types).allMatch(t -> isIntegerOrNumber(t)) ||
            Arrays.stream(types).allMatch(t -> isBoolean(t)) ||
            Arrays.stream(types).allMatch(t -> isDateTime(t));
    }

    public static boolean isString(Type t) {
        return t instanceof StringType;
    }

    public static boolean isString(Type... types) {
        return Arrays.stream(types).allMatch(t -> isString(t));
    }

    public static boolean isInteger(Type t) {
        return t instanceof IntegerType;
    }

    public static boolean isInteger(Type... types) {
        return Arrays.stream(types).allMatch(t -> isInteger(t));
    }

    public static boolean isIntegerOrNumber(Type t) {
        return t instanceof IntegerType || t instanceof NumberType;
    }

    public static boolean isIntegerOrNumber(Type... types) {
        return Arrays.stream(types).allMatch(t -> isIntegerOrNumber(t));
    }

    public static boolean isBoolean(Type t) {
        return t instanceof BooleanType || t instanceof BooleanType;
    }

    public static boolean isBoolean(Type... types) {
        return Arrays.stream(types).allMatch(t -> isBoolean(t));
    }

    public static boolean isDateTime(Type t) {
        return t instanceof DateTimeType;
    }

    public static boolean isDateTime(Type... types) {
        return Arrays.stream(types).allMatch(t -> isDateTime(t));
    }

    public static Optional<Type> ofValueClass(Class<?> clazz) {

        if (clazz.equals(StringValue.class))
            return Optional.of(STRING_TYPE);

        if (clazz.equals(IntegerValue.class))
            return Optional.of(INTEGER_TYPE);

        if (clazz.equals(NumberValue.class))
            return Optional.of(NUMBER_TYPE);

        if (clazz.equals(BooleanValue.class))
            return Optional.of(BOOLEAN_TYPE);

        if (clazz.equals(DateTimeValue.class))
            return Optional.of(DATETIME_TYPE);

        return Optional.empty();
    }

    public static Optional<Type> ofClass(Class<? extends Type> clazz) {

        if (clazz.equals(StringType.class))
            return Optional.of(STRING_TYPE);

        if (clazz.equals(IntegerType.class))
            return Optional.of(INTEGER_TYPE);

        if (clazz.equals(NumberType.class))
            return Optional.of(NUMBER_TYPE);

        if (clazz.equals(BooleanType.class))
            return Optional.of(BOOLEAN_TYPE);

        if (clazz.equals(DateTimeType.class))
            return Optional.of(DATETIME_TYPE);

        return Optional.empty();
    }

    public static boolean isMatchJavaType(Type t, Class<?> javaType) {

        if (isString(t) && javaType.equals(String.class))
            return true;

//        if (isInteger(t) && javaType.equals(long.class))
//            return true;
//
        if (isInteger(t) && javaType.equals(int.class))
            return true;

        if (isIntegerOrNumber(t) && javaType.equals(double.class))
            return true;

        if (isBoolean(t) && javaType.equals(boolean.class))
            return true;

        if (isDateTime(t) && javaType.equals(ZonedDateTime.class))
            return true;

        return false;
    }

    public static Optional<Type> fromJavaType(Class<?> javaType) {

        if (javaType.equals(String.class))
            return Optional.of(STRING_TYPE);

//        if (javaType.equals(long.class))
//            return INTEGER_TYPE;
//
        if (javaType.equals(int.class))
            return Optional.of(INTEGER_TYPE);

        if (javaType.equals(double.class))
            return Optional.of(NUMBER_TYPE);

        if (javaType.equals(boolean.class))
            return Optional.of(BOOLEAN_TYPE);

        if (javaType.equals(ZonedDateTime.class))
            return Optional.of(DATETIME_TYPE);

        return Optional.empty();
        //throw new RuntimeException("cannot transform.");
    }

}
