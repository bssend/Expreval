package bssend.expreval.visitor;

import bssend.expreval.node.binaryexpr.IBinaryExprNode;
import bssend.expreval.node.functioncall.IFunctionCallNode;
import bssend.expreval.node.literal.ILiteralNode;
import bssend.expreval.value.InternalValue;

public class EvalVisitor implements IVisitor<InternalValue> {

    @Override
    public InternalValue visit(ILiteralNode node) {
        return node.eval(this);
    }

    @Override
    public InternalValue visit(IFunctionCallNode node) {
        return null;
    }

    @Override
    public InternalValue visit(IBinaryExprNode node) {
        return node.eval(this);
//        var left = node.getLeft().accept(this);
//        var right = node.getRight().accept(this);
//
//        return BinaryOperator
//                .of(node.getOperatorType())
//                .operate(left, right);
    }
}
