package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;

import java.util.Optional;

public class CompareEvalDispatcher {

    @FunctionalInterface
    interface ICompareOperator {
        BooleanValue apply(Value v1, Value v2);
    }

    private final Value v1;
    private final Value v2;

    private ICompareOperator stringOperator;
    private ICompareOperator integerOperator;
    private ICompareOperator numberOperator;
    private ICompareOperator booleanOperator;

    public CompareEvalDispatcher(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public CompareEvalDispatcher ifString(final ICompareOperator operator) {
        this.stringOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifInteger(final ICompareOperator operator) {
        this.integerOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifNumber(final ICompareOperator operator) {
        this.numberOperator = operator;
        return this;
    }

    public CompareEvalDispatcher ifBoolean(final ICompareOperator operator) {
        this.booleanOperator = operator;
        return this;
    }

    public Value dispatch() {

        Type t1 = v1.getType();
        Type t2 = v2.getType();

        // If string type then dispatch to string operator.
        if (Type.isString(t1, t2)) {
            return Optional.ofNullable(stringOperator)
                    .map(op -> op.apply(v1, v2))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If integer type then dispatch to string operator.
        if (Type.isInteger(t1, t2)) {
            return Optional.ofNullable(integerOperator)
                    .map(op -> op.apply(v1, v2))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If number type then dispatch to string operator.
        if (Type.isIntegerOrNumber(t1, t2)) {
            return Optional.ofNullable(numberOperator)
                    .map(op -> op.apply(v1, v2))
                    .orElseThrow(() -> error(v1, v2));
        }

        // If boolean type then dispatch to string operator.
        if (Type.isBoolean(t1, t2)) {
            return Optional.ofNullable(booleanOperator)
                    .map(op -> op.apply(v1, v2))
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
