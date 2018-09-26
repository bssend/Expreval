package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class LteNode extends BinaryExprNode implements IBinaryExprNode {

    protected LteNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Lte);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}