package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class AndNode extends BinaryExprNode
    implements IBinaryExprNode {

    protected AndNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.And);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}