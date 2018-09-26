package expreval.node.cast;

import expreval.node.INode;
import expreval.node.ValueType;
import expreval.node.value.DecimalNode;
import expreval.node.value.IntNode;
import expreval.node.value.ValueNode;
import expreval.visitor.IVisitor;

public class IntToDecimalCastNode extends CastNode implements ICastNode<IntNode, DecimalNode>{

    protected IntToDecimalCastNode(INode node) {
        super(node, ValueType.Decimal);
    }

    @Override
    public DecimalNode cast(IntNode node) {
        return ValueNode.of((double)node.intValue());
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
