package bssend.expreval.node.binaryexpr;

import bssend.expreval.compiler.Token;
import bssend.expreval.node.INode;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;

public abstract class LogicalExprNode extends BinaryExprNode {

    protected LogicalExprNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

    protected LogicalEvalDispatcher typeOf(Value v1, Value v2) {
        return new LogicalEvalDispatcher(v1, v2);
    }

    protected TypeResolveDispatcher typeOf(Type t1, Type t2) {
        return new TypeResolveDispatcher(t1, t2);
    }

}