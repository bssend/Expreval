package bssend.expreval.visitor;

import bssend.expreval.ast.Ast;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.compiler.Token;
import bssend.expreval.exception.EvalException;
import bssend.expreval.exception.FunctionNotFoundException;
import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.exception.VariableNotDefinedException;
import bssend.expreval.function.FunctionFactory;
import bssend.expreval.ast.node.binary.*;
import bssend.expreval.ast.node.literal.*;
import bssend.expreval.ast.node.unary.IUnaryExpressionNode;
import bssend.expreval.ast.node.unary.MinusExpressionNode;
import bssend.expreval.ast.node.unary.NotExpressionNode;
import bssend.expreval.ast.node.unary.PlusExpressionNode;
import bssend.expreval.ast.node.variable.IVariableExpressionNode;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.ast.node.functioncall.IFunctionCallExpressionNode;
import lombok.val;
import lombok.var;

import java.util.stream.Collectors;

public class TypeResolveVisitor implements ITypeResolveVisitor {

//    @Override
//    public Type visit(IScope scope, ILiteralNode node) {
//        return node.resolveType(scope, this);
//    }

    @Override
    public Type visit(IScope scope, IExpressionNode node) {
        return null;
    }

    @Override
    public Type visit(IScope scope, StringNode node) {
        return node.setAndGetType(Type.STRING_TYPE);
    }

    @Override
    public Type visit(IScope scope, IntegerNode node) {
        return node.setAndGetType(Type.INTEGER_TYPE);
    }

    @Override
    public Type visit(IScope scope, NumberNode node) {
        return node.setAndGetType(Type.NUMBER_TYPE);
    }

    @Override
    public Type visit(IScope scope, BooleanNode node) {
        return node.setAndGetType(Type.BOOLEAN_TYPE);
    }

    @Override
    public Type visit(IScope scope, IFunctionCallExpressionNode node) {
        // Resolve argument types.
        var argTypes = node.getArguments().stream()
                .map(arg -> arg.resolveType(scope, this))
                .collect(Collectors.toList());

        // Resolve function return type.
        try {
            val function =
                    FunctionFactory.create(node.getName(), argTypes);

            node.setFunction(function);

            var returnType = Type.fromJavaType(function.getReturnType())
                    .orElseThrow(() ->
                            new TypeResolveException(
                                    node.getToken() ,
                                    "Cannot resolve function return type."));

            return node.setAndGetType(returnType);

        } catch (FunctionNotFoundException ex) {
            throw new TypeResolveException(
                    node.getToken() ,
                    "Function not found (" + node.getName() + ")");
        }
    }

//    @Override
//    public Type visit(IScope scope, IBinaryExpressionNode node) {
//        return node.resolveType(scope, this);
//    }

    @Override
    public Type visit(IScope scope, AddExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isString(t1, t2))
            return node.setAndGetType(Type.STRING_TYPE);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"+");
    }

    @Override
    public Type visit(IScope scope, SubtractExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"-");
    }

    @Override
    public Type visit(IScope scope, MultiplyExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"*");
    }

    @Override
    public Type visit(IScope scope, DivideExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"/");
    }

    @Override
    public Type visit(IScope scope, ModuloExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"%");
    }

    @Override
    public Type visit(IScope scope, EqualExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isString(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isBoolean(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"==");
    }

    @Override
    public Type visit(IScope scope, NotEqualExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isString(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isBoolean(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"!=");
    }

    @Override
    public Type visit(IScope scope, LessThanExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"<");
    }

    @Override
    public Type visit(IScope scope, LessThanOrEqualExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),"<=");
    }

    @Override
    public Type visit(IScope scope, GreaterThanExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(), ">");
    }

    @Override
    public Type visit(IScope scope, GreaterThanOrEqualExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isInteger(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        if (Type.isIntegerOrNumber(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),">=");
    }

    @Override
    public Type visit(IScope scope, AndAlsoExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isBoolean(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(),  "&&");
    }

    @Override
    public Type visit(IScope scope, OrElseExpressionNode node) {

        val t1 = node.getLeft().resolveType(scope, this);
        val t2 = node.getRight().resolveType(scope, this);

        if (Type.isBoolean(t1, t2))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t1, t2, node.getToken(), "||");
    }

//    @Override
//    public Type visit(IScope scope, IUnaryExpressionNode node) {
//        return node.resolveType(scope, this);
//    }

    @Override
    public Type visit(IScope scope, NotExpressionNode node) {

        var t = node.getExpression().resolveType(scope, this);

        if (Type.isBoolean(t))
            return node.setAndGetType(Type.BOOLEAN_TYPE);

        throw typeResolveError(t, node.getToken(), "!");
    }

    @Override
    public Type visit(IScope scope, MinusExpressionNode node) {

        var t = node.getExpression().resolveType(scope, this);

        if (Type.isInteger(t))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t, node.getToken(), "-");
    }

    @Override
    public Type visit(IScope scope, PlusExpressionNode node) {

        var t = node.getExpression().resolveType(scope, this);

        if (Type.isInteger(t))
            return node.setAndGetType(Type.INTEGER_TYPE);

        if (Type.isIntegerOrNumber(t))
            return node.setAndGetType(Type.NUMBER_TYPE);

        throw typeResolveError(t, node.getToken(), "+");
    }

    @Override
    public Type visit(IScope scope, IVariableExpressionNode node) {
        var type = scope.find(node.getName()).orElseThrow(() ->
                new VariableNotDefinedException(
                        node.getToken() ,
                        String.format("%s is not defined.", node.getName())))
                .getType();

        return node.setAndGetType(type);
    }

    private EvalException typeResolveError(Type t, Token token, String operator) {
        throw new TypeResolveException(
                token ,
                String.format("'%s' operator is not supported type %s",
                        operator ,
                        t.toString()));
    }

    protected EvalException typeResolveError(Type t1, Type t2, Token token, String operator) {
        throw new TypeResolveException(
                token ,
                String.format("'%s' operator is not supported type %s %s",
                        operator ,
                        t1.toString(),
                        t2.toString()));
    }

}
