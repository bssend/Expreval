package bssend.expreval.type;

import java.util.Arrays;
import java.util.Optional;

public class Type {
    public final static Type STRING_TYPE = new StringType();
    public final static Type INTEGER_TYPE = new IntegerType();
    public final static Type NUMBER_TYPE = new NumberType();
    public final static Type BOOLEAN_TYPE = new BooleanType();

    @Override
    public String toString() {
        return getClass().getSimpleName();
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

        return Optional.empty();
        //throw new RuntimeException("cannot transform.");
    }

}
