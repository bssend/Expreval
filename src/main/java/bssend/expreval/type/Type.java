package bssend.expreval.type;

public class Type {
    public final static Type STRING_TYPE = new StringType();
    public final static Type INT_TYPE = new IntType();
    public final static Type NUMBER_TYPE = new NumberType();
    public final static Type BOOLEAN_TYPE = new BooleanType();

    public static boolean isString(Type t) {
        return t instanceof StringType;
    }

    public static boolean isInt(Type t) {
        return t instanceof IntType;
    }

    public static boolean isIntOrNumber(Type t) {
        return t instanceof IntType || t instanceof NumberType;
    }

    public static boolean isBoolean(Type t) {
        return t instanceof BooleanType || t instanceof BooleanType;
    }

}
