package bssend.expreval.ast.node.functioncall;

import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.function.FunctionDef;

import java.lang.reflect.Method;
import java.util.List;

public interface IFunctionCallExpressionNode extends IExpressionNode {
    String getName();
    List<IExpressionNode> getArguments();
    FunctionDef getFunction();
    void setFunction(FunctionDef method);
}
