package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class GtNode extends BinaryExprNode implements IBinaryExprNode {

    protected GtNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Gt);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
