package bssend.expreval.visitor;

import bssend.expreval.ast.Ast;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.exception.EvalException;
import bssend.expreval.exception.VariableNotDefinedException;
import bssend.expreval.ast.node.binary.*;
import bssend.expreval.ast.node.functioncall.IFunctionCallExpressionNode;
import bssend.expreval.ast.node.literal.*;
import bssend.expreval.ast.node.unary.IUnaryExpressionNode;
import bssend.expreval.ast.node.unary.MinusExpressionNode;
import bssend.expreval.ast.node.unary.NotExpressionNode;
import bssend.expreval.ast.node.unary.PlusExpressionNode;
import bssend.expreval.ast.node.variable.IVariableExpressionNode;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;
import lombok.val;
import lombok.var;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class EvalVisitor implements IEvalVisitor {

    @Override
    public Value visit(IScope scope, IExpressionNode node) {
        return node.eval(scope, this);
    }

    @Override
    public Value visit(IScope scope, StringNode node) {
        return node.value();
    }

    @Override
    public Value visit(IScope scope, IntegerNode node) {
        return node.value();
    }

    @Override
    public Value visit(IScope scope, NumberNode node) {
        return node.value();
    }

    @Override
    public Value visit(IScope scope, BooleanNode node) {
        return node.value();
    }

    @Override
    public Value visit(IScope scope, IFunctionCallExpressionNode node) {

        var argTypes = node.getArguments().stream()
                .map(arg -> arg.getType())
                .collect(Collectors.toList());

        try {
            return node.getFunction().call(
                    node.getArguments().stream()
                            .map(arg -> arg.eval(scope, this))
                            .collect(Collectors.toList()));

//            Object result = node.getFunction().invoke(null,
//                    node.getArguments().stream()
//                            .map(arg -> arg.eval(scope, this).name())
//                            .toArray());
//
//            return Value.ofType(node.getType(), result);

        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

//    @Override
//    public Value visit(IScope scope, IBinaryExpressionNode node) {
//        return node.eval(scope, this);
//    }

    @Override
    public Value visit(IScope scope, AddExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isString(v1.getType(), v2.getType()))
            return StringValue.of(v1.stringValue() + v2.stringValue());

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() + v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(v1.doubleValue() + v2.doubleValue());

        throw evalError(v1, v2, "+");
    }

    @Override
    public Value visit(IScope scope, SubtractExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() - v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(BigDecimal.valueOf(v1.doubleValue())
                    .subtract(BigDecimal.valueOf(v2.doubleValue()))
                    .doubleValue());

        throw evalError(v1, v2, "-");
    }

    @Override
    public Value visit(IScope scope, MultiplyExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() * v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(BigDecimal.valueOf(v1.doubleValue())
                    .multiply(BigDecimal.valueOf(v2.doubleValue()))
                    .doubleValue());

        throw evalError(v1, v2, "*");
    }

    @Override
    public Value visit(IScope scope, DivideExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() / v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(BigDecimal.valueOf(v1.doubleValue())
                    .divide(BigDecimal.valueOf(v2.doubleValue()))
                    .doubleValue());

        throw evalError(v1, v2, "/");
    }

    @Override
    public Value visit(IScope scope, ModuloExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return IntegerValue.of(v1.intValue() % v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return NumberValue.of(BigDecimal.valueOf(v1.doubleValue())
                    .remainder(BigDecimal.valueOf(v2.doubleValue()))
                    .doubleValue());

        throw evalError(v1, v2, "%");
    }

    @Override
    public Value visit(IScope scope, EqualExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isString(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.stringValue().equals(v2.stringValue()));

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() == v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() == v2.doubleValue());

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() == v2.booleanValue());

        throw evalError(v1, v2, "==");
    }

    @Override
    public Value visit(IScope scope, NotEqualExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isString(v1.getType(), v2.getType()))
            return BooleanValue.of(!v1.stringValue().equals(v2.stringValue()));

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() != v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() != v2.doubleValue());

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() != v2.booleanValue());

        throw evalError(v1, v2, "!=");
    }

    @Override
    public Value visit(IScope scope, LessThanExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() < v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() < v2.doubleValue());

        throw evalError(v1, v2, "<");
    }

    @Override
    public Value visit(IScope scope, LessThanOrEqualExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() <= v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() <= v2.doubleValue());

        throw evalError(v1, v2, "<=");
    }

    @Override
    public Value visit(IScope scope, GreaterThanExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() > v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() > v2.doubleValue());

        throw evalError(v1, v2, ">");
    }

    @Override
    public Value visit(IScope scope, GreaterThanOrEqualExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isInteger(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.intValue() >= v2.intValue());

        if (Type.isIntegerOrNumber(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.doubleValue() >= v2.doubleValue());

        throw evalError(v1, v2, ">=");
    }

    @Override
    public Value visit(IScope scope, AndAlsoExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() && v2.booleanValue());

        throw evalError(v1, v2, "&&");
    }

    @Override
    public Value visit(IScope scope, OrElseExpressionNode node) {

        val v1 = node.getLeft().eval(scope, this);
        val v2 = node.getRight().eval(scope, this);

        if (Type.isBoolean(v1.getType(), v2.getType()))
            return BooleanValue.of(v1.booleanValue() || v2.booleanValue());

        throw evalError(v1, v2, "||");
    }

//    @Override
//    public Value visit(IScope scope, IUnaryExpressionNode node) {
//        return node.eval(scope, this);
//    }

    @Override
    public Value visit(IScope scope, NotExpressionNode node) {

        val v = node.getExpression().eval(scope, this);

        if (Type.isBoolean(v.getType()))
            return BooleanValue.of(!v.booleanValue());

        throw evalError(v, "!");
    }

    @Override
    public Value visit(IScope scope, MinusExpressionNode node) {

        var v = node.getExpression().eval(scope, this);

        if (Type.isInteger(v.getType()))
            return IntegerValue.of(v.intValue() * -1);

        if (Type.isIntegerOrNumber(v.getType()))
            return NumberValue.of(v.doubleValue() * -1);

        throw evalError(v, "-");

    }

    @Override
    public Value visit(IScope scope, PlusExpressionNode node) {

        var v = node.getExpression().eval(scope, this);

        if (Type.isInteger(v.getType()))
            return IntegerValue.of(v.intValue() * 1);

        if (Type.isIntegerOrNumber(v.getType()))
            return NumberValue.of(v.doubleValue() * 1);

        throw evalError(v, "+");
    }

    @Override
    public Value visit(IScope scope, IVariableExpressionNode node) {
        return scope.find(node.getName()).orElseThrow(() ->
                new VariableNotDefinedException(
                        String.format("%s is not defined.", node.getName())));
    }

    private EvalException evalError(Value v, String operator) {
        throw new EvalException(
                String.format("'%s' operator is not supported type %s %s",
                        operator ,
                        v.toString()));
    }

    private EvalException evalError(Value v1, Value v2, String operaor) {
        throw new EvalException(
                String.format("'%s' operator is not supported type %s %s",
                        operaor ,
                        v1.toString(),
                        v2.toString()));
    }

}
