package expreval.node.functioncall;

import expreval.node.INode;
import expreval.node.Node;
import expreval.node.NodeType;
import expreval.node.ValueType;
import expreval.visitor.IVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class FunctionCallNode extends Node implements IFunctionCallNode{

    private final String name;
    private final List<INode> arguments;

    private FunctionCallNode(String name, List<INode> arguments) {
        super(NodeType.FunctionCall, ValueType.Untyped);
        this.name = name;
        this.arguments = arguments;
    }

    public static IFunctionCallNode of(String name, List<INode> arguments) {
        return new FunctionCallNode(name, arguments);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
