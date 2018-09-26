package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class AddNode extends BinaryExprNode implements IBinaryExprNode{

    protected AddNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Add);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
