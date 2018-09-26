package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class SubNode extends BinaryExprNode implements IBinaryExprNode{

    protected SubNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Sub);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
