package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class OrNode extends BinaryExprNode implements IBinaryExprNode {

    protected OrNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Or);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
