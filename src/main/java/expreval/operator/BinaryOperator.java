package expreval.operator;

public class BinaryOperator {
    public static IBinaryOperator of(BinaryOperatorType opType) {

        switch (opType) {
            case Add:
                return new AddOperator();
            default:
                break;
        }

        return null;
        //throw new RuntimeException("unreachable.");
    }
}
