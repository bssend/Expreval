package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.operator.BinaryOperatorType;
import expreval.visitor.IVisitor;

public class ModNode extends BinaryExprNode implements IBinaryExprNode {

    protected ModNode(INode left, INode right) {
        super(left, right, BinaryOperatorType.Mod);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
