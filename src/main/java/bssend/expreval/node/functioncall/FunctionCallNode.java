package bssend.expreval.node.functioncall;

import bssend.expreval.node.INode;
import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.type.Type;
import bssend.expreval.value.InternalValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class FunctionCallNode extends Node implements IFunctionCallNode{

    private final String name;
    private final List<INode> arguments;

    private FunctionCallNode(String name, List<INode> arguments) {
        super(NodeType.FunctionCall, null);
        this.name = name;
        this.arguments = arguments;
    }

    public static IFunctionCallNode of(String name, List<INode> arguments) {
        return new FunctionCallNode(name, arguments);
    }

//    @Override
//    public <T> T accept(IVisitor<T> visitor) {
//        return visitor.visit(this);
//    }

    @Override
    public InternalValue eval(EvalVisitor visitor) {
        return null;
    }

    @Override
    public Type resolveType(TypeResolveVisitor visitor) {
        return null;
    }
}
