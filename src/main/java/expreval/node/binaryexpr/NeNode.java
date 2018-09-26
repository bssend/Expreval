package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class NeNode extends BinaryExprNode implements IBinaryExprNode {

    protected NeNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Ne);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
