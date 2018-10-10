package bssend.expreval.ast.node.functioncall;

import bssend.expreval.ast.node.IExpressionNode;

import java.lang.reflect.Method;
import java.util.List;

public interface IFunctionCallExpressionNode extends IExpressionNode {
    String getName();
    List<IExpressionNode> getArguments();
    Method getFunction();
    void setFunction(Method method);
}
