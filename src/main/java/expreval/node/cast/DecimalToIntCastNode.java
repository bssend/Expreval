package expreval.node.cast;

import expreval.node.INode;
import expreval.node.ValueType;
import expreval.node.value.DecimalNode;
import expreval.node.value.IntNode;
import expreval.node.value.ValueNode;
import expreval.visitor.IVisitor;

public class DecimalToIntCastNode extends CastNode
        implements ICastNode<DecimalNode, IntNode> {

    protected DecimalToIntCastNode(INode node) {
        super(node, ValueType.Int);
    }

    @Override
    public IntNode cast(DecimalNode node) {
        return ValueNode.of((int)node.doubleValue());
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
