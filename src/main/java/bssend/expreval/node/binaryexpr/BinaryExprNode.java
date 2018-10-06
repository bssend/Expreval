package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.node.INode;
import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Supplier;

@Getter
public abstract class BinaryExprNode extends Node {

    private final INode left;
    private final INode right;
    private final Token token;

    protected BinaryExprNode(
            @NonNull final INode left,
            @NonNull final INode right,
            @NonNull final Token token) {
        super(NodeType.BinaryExpr, null);
        this.left = left;
        this.right = right;
        this.token = token;
    }

    protected EvalException typeResolveError(Type t1, Type t2, String operaor) {
        throw new TypeResolveException(
                getToken() ,
                String.format("'%s' operator is not supported type %s %s",
                        operaor ,
                        t1.toString(),
                        t2.toString()));
    }

    protected EvalException evalError(Value v1, Value v2, String operaor) {
        throw new EvalException(
                String.format("'%s' operator is not supported type %s %s",
                        operaor ,
                        v1.toString(),
                        v2.toString()));
    }

}
