package expreval.node.functioncall;

import expreval.node.INode;

import java.util.List;

public interface IFunctionCallNode extends INode {
    String getName();
    List<INode> getArguments();
}
