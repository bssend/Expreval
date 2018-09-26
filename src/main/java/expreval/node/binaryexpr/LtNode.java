package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class LtNode extends BinaryExprNode implements IBinaryExprNode {

    protected LtNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Lt);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
