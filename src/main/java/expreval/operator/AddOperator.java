package expreval.operator;

import expreval.exception.OperatorNotSupportedException;
import expreval.node.ValueType;
import expreval.node.binaryexpr.AddNode;
import expreval.node.value.*;
import expreval.visitor.EvaluateVisitor;
import lombok.NonNull;

public class AddOperator extends ArithmeticOperator implements IArithmeticOperator {

    @Override
    public StringNode compute(StringNode left, StringNode right) {
        return ValueNode.of(left.stringValue() + right.stringValue());
    }

    @Override
    public IntNode compute(IntNode left, IntNode right) {
        return ValueNode.of(left.intValue() + right.intValue());
    }

    @Override
    public DecimalNode compute(DecimalNode left, DecimalNode right) {
        return ValueNode.of(left.doubleValue() + right.doubleValue());
    }

    @Override
    public BooleanNode compute(BooleanNode left, BooleanNode right) {
        throw new OperatorNotSupportedException(
                "Add operator is not supported boolean type.");
    }

//    @Override
//    public IValueNode operate(IValueNode left, IValueNode right) {
//
//        if (left instanceof StringNode && right instanceof StringNode) {
//            return compute((StringNode)left, (StringNode)right);
//        }
//
//        if (left instanceof IntNode && right instanceof IntNode) {
//            return compute((IntNode)left, (IntNode)right);
//        }
//
//        if (left instanceof DecimalNode && right instanceof DecimalNode) {
//            return compute((DecimalNode)left, (DecimalNode)right);
//        }
//
//        throw new OperatorNotSupportedException(
//                "Add operator is not supported value type.");
//    }

//    private StringNode compute(
//            @NonNull final StringNode left,
//            @NonNull final StringNode right) {
//        return ValueNode.of(left.stringValue() + right.stringValue());
//    }
//
//    private IntNode compute(
//            @NonNull final IntNode left,
//            @NonNull final IntNode right) {
//        return ValueNode.of(left.intValue() + right.intValue());
//    }
//
//    private DecimalNode compute(
//            @NonNull final DecimalNode left,
//            @NonNull final DecimalNode right) {
//        return ValueNode.of(left.doubleValue() + right.doubleValue());
//    }
}
