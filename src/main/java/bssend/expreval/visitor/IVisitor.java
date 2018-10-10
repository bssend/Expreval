package bssend.expreval.visitor;

import bssend.expreval.ast.Ast;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.ast.node.binary.*;
import bssend.expreval.ast.node.functioncall.IFunctionCallExpressionNode;
import bssend.expreval.ast.node.literal.*;
import bssend.expreval.ast.node.unary.IUnaryExpressionNode;
import bssend.expreval.ast.node.unary.MinusExpressionNode;
import bssend.expreval.ast.node.unary.NotExpressionNode;
import bssend.expreval.ast.node.unary.PlusExpressionNode;
import bssend.expreval.ast.node.variable.IVariableExpressionNode;
import bssend.expreval.scope.IScope;

public interface IVisitor<T> {

    T visit(final IScope scope, final IExpressionNode node);

//    T visit(final IScope scope, final ILiteralNode node);
    T visit(final IScope scope, final StringNode node);
    T visit(final IScope scope, final IntegerNode node);
    T visit(final IScope scope, final NumberNode node);
    T visit(final IScope scope, final BooleanNode node);

    T visit(final IScope scope, final IFunctionCallExpressionNode node);

//    T visit(final IScope scope, final IBinaryExpressionNode node);
    T visit(final IScope scope, final AddExpressionNode node);
    T visit(final IScope scope, final SubtractExpressionNode node);
    T visit(final IScope scope, final MultiplyExpressionNode node);
    T visit(final IScope scope, final DivideExpressionNode node);
    T visit(final IScope scope, final ModuloExpressionNode node);
    T visit(final IScope scope, final EqualExpressionNode node);
    T visit(final IScope scope, final NotEqualExpressionNode node);
    T visit(final IScope scope, final LessThanExpressionNode node);
    T visit(final IScope scope, final LessThanOrEqualExpressionNode node);
    T visit(final IScope scope, final GreaterThanExpressionNode node);
    T visit(final IScope scope, final GreaterThanOrEqualExpressionNode node);
    T visit(final IScope scope, final AndAlsoExpressionNode node);
    T visit(final IScope scope, final OrElseExpressionNode node);

//    T visit(final IScope scope, final IUnaryExpressionNode node);
    T visit(final IScope scope, final NotExpressionNode node);
    T visit(final IScope scope, final MinusExpressionNode node);
    T visit(final IScope scope, final PlusExpressionNode node);

    T visit(final IScope scope, final IVariableExpressionNode node);
}
