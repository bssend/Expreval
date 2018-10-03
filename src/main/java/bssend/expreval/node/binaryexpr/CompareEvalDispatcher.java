package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.type.Type;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.Value;

import java.util.Optional;

public class CompareEvalDispatcher {

    @FunctionalInterface
    interface ICompareOperator<T> {
        boolean apply(T v1, T v2);
    }

    private final Value v1;
    private final Value v2;

    private ICompareOperator<String> stringOperator;
    private ICompareOperator<Integer> integerOperator;
    private ICompareOperator<Double> numberOperator;
    private ICompareOperator<Boolean> booleanOperator;

    public CompareEvalDispatcher(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public CompareEvalDispatcher ifString(
            final ICompareOperator<String> operator) {
        stringOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifInteger(
            final ICompareOperator<Integer> operator) {
        integerOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifNumber(
            final ICompareOperator<Double> operator) {
        numberOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifBoolean(
            final ICompareOperator<Boolean> operator) {
        booleanOperator = operator;
        return this;
    }

    public Value dispatch() {

        Type t1 = v1.getType();
        Type t2 = v2.getType();

        // If string type then dispatch to string operator.
        if (Type.isString(t1, t2)) {
            return Optional.ofNullable(stringOperator)
                    .map(op -> new BooleanValue(
                            op.apply(v1.stringValue(), v2.stringValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If integer type then dispatch to string operator.
        if (Type.isInteger(t1, t2)) {
            return Optional.ofNullable(integerOperator)
                    .map(op -> new BooleanValue(
                            op.apply(v1.intValue(), v2.intValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If number type then dispatch to string operator.
        if (Type.isIntegerOrNumber(t1, t2)) {
            return Optional.ofNullable(numberOperator)
                    .map(op -> new BooleanValue(
                            op.apply(v1.doubleValue(), v2.doubleValue())))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If boolean type then dispatch to string operator.
        if (Type.isBoolean(t1, t2)) {
            return Optional.ofNullable(booleanOperator)
                    .map(op -> new BooleanValue(
                            op.apply(v1.booleanValue(), v2.booleanValue())))
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
