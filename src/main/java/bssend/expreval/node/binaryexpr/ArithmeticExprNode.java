package bssend.expreval.node.binaryexpr;

import bssend.expreval.compiler.Token;
import bssend.expreval.node.INode;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;

public abstract class ArithmeticExprNode extends BinaryExprNode {

    protected ArithmeticExprNode(INode left, INode right, Token token) {
        super(left, right, token);
    }

//    protected ArithmeticEvalDispatcher typeOf(Value v1, Value v2) {
//        return new ArithmeticEvalDispatcher(v1, v2);
//    }
//
//    protected TypeResolveDispatcher typeOf(Type t1, Type t2) {
//        return new TypeResolveDispatcher(t1, t2);
//    }

}
