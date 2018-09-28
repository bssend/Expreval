package bssend.expreval.node.functioncall;

import bssend.expreval.node.INode;

import java.util.List;

public interface IFunctionCallNode extends INode {
    String getName();
    List<INode> getArguments();
}
