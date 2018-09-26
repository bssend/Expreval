package expreval.node.binaryexpr;

import expreval.node.INode;
import expreval.node.Node;
import expreval.node.NodeType;
import expreval.node.ValueType;
import expreval.operator.BinaryOperatorType;
import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class BinaryExprNode extends Node {
    private final INode left;
    private final INode right;
    private final BinaryOperatorType operatorType;

    protected BinaryExprNode(
            @NonNull final INode left,
            @NonNull final INode right,
            final BinaryOperatorType operatorType) {
        super(NodeType.BinaryExpr, ValueType.Untyped);
        this.left = left;
        this.right = right;
        this.operatorType = operatorType;
    }

    public static IBinaryExprNode create(
            @NonNull final INode left,
            @NonNull final INode right,
            final BinaryOperatorType operatorType) {

        switch (operatorType) {
            case Add:
                return new AddNode(left, right);
            case Mul:
                return new MulNode(left, right);
            case Sub:
                return new SubNode(left, right);
            case Div:
                return new DivNode(left, right);
            case Mod:
                return new ModNode(left, right);
            case Eq:
                return new EqNode(left, right);
            case Ne:
                return new NeNode(left, right);
            case Lt:
                return new LtNode(left, right);
            case Lte:
                return new LteNode(left, right);
            case Gt:
                return new GtNode(left, right);
            case Gte:
                return new GteNode(left, right);
            case And:
                return new AndNode(left, right);
            case Or:
                return new OrNode(left, right);
        }

        throw new RuntimeException("Unreachable statement.");
    }
}
