package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class GteNode extends BinaryExprNode implements IBinaryExprNode {

    protected GteNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Gte);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
