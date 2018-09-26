package expreval.visitor;

import expreval.exception.VisitorException;
import expreval.node.INode;
import expreval.node.binaryexpr.AddNode;
import expreval.node.binaryexpr.IBinaryExprNode;
import expreval.node.cast.ICastNode;
import expreval.node.functioncall.IFunctionCallNode;
import expreval.node.value.*;
import expreval.operator.BinaryOperator;
import expreval.operator.IArithmeticOperator;
import expreval.operator.ICompareOperator;
import expreval.operator.ILogicalOperator;
import lombok.var;

public class EvaluateVisitor implements IVisitor<IValueNode> {

    @Override
    public IValueNode visit(IValueNode node) {
        return node.accept(this);
    }

    @Override
    public IValueNode visit(ICastNode node) {
        return node.cast(node.getFromNode().accept(this));
    }

    @Override
    public IValueNode visit(IFunctionCallNode node) {
        return null;
    }

    @Override
    public IValueNode visit(IBinaryExprNode node) {
        var left = node.getLeft().accept(this);
        var right = node.getRight().accept(this);

        return BinaryOperator
                .of(node.getOperatorType())
                .operate(left, right);
    }
}
