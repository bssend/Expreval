package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class MulNode extends BinaryExprNode implements IBinaryExprNode {

    protected MulNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Mul);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
