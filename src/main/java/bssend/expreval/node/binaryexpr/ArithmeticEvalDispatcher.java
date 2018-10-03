package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.type.Type;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.NumberValue;
import bssend.expreval.value.StringValue;
import bssend.expreval.value.Value;

import java.util.Optional;

public class ArithmeticEvalDispatcher {

    @FunctionalInterface
    interface IArithmeticOperator<T> {
        T apply(T v1, T v2);
    }

    private final Value v1;
    private final Value v2;

    private IArithmeticOperator<String> stringOperator;
    private IArithmeticOperator<Integer> integerOperator;
    private IArithmeticOperator<Double> numberOperator;
//        private IArithmeticOperator<Boolean> booleanOperator;

    public ArithmeticEvalDispatcher(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public ArithmeticEvalDispatcher ifString(
            final IArithmeticOperator<String> operator) {
        stringOperator = operator;
        return this;
    }

    public ArithmeticEvalDispatcher ifInteger(
            final IArithmeticOperator<Integer> operator) {
        integerOperator = operator;
        return this;
    }

    public ArithmeticEvalDispatcher ifNumber(
            final IArithmeticOperator<Double> operator) {
        numberOperator = operator;
        return this;
    }

    public Value dispatch() {

        Type t1 = v1.getType();
        Type t2 = v2.getType();

        // If string type then dispatch to string operator.
        if (Type.isString(t1, t2)) {
            return Optional.ofNullable(stringOperator)
                    .map(op -> new StringValue(
                            op.apply(v1.stringValue(), v2.stringValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If integer type then dispatch to string operator.
        if (Type.isInteger(t1, t2)) {
            return Optional.ofNullable(integerOperator)
                    .map(op -> new IntegerValue(
                            op.apply(v1.intValue(), v2.intValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If number type then dispatch to string operator.
        if (Type.isIntegerOrNumber(t1, t2)) {
            return Optional.ofNullable(numberOperator)
                    .map(op -> new NumberValue(
                            op.apply(v1.doubleValue(), v2.doubleValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        throw error(v1, v2);
    }

    private EvalException error(Value v1, Value v2) {
        throw new EvalException(
                String.format("Operator is not supported type %s %s",
                        v1.toString(),
                        v2.toString()));
    }

}
