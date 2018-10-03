package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.type.Type;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.Value;

import java.util.Optional;

public class LogicalEvalDispatcher {

    @FunctionalInterface
    interface ILogicalOperator<T> {
        boolean apply(T v1, T v2);
    }

    private final Value v1;
    private final Value v2;

    private ILogicalOperator<Boolean> booleanOperator;

    public LogicalEvalDispatcher(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public LogicalEvalDispatcher ifBoolean(
            final ILogicalOperator<Boolean> operator) {
        booleanOperator = operator;
        return this;
    }

    public Value dispatch() {

        Type t1 = v1.getType();
        Type t2 = v2.getType();

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
