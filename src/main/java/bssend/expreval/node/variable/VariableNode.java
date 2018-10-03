package bssend.expreval.node.variable;

import bssend.expreval.compiler.Token;
import bssend.expreval.exception.VariableNotDefinedException;
import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;

public class VariableNode extends Node implements IVariableNode {

    private final Token token;
    private final String name;

    public VariableNode(final Token token) {
        super(NodeType.Variable, null);
        this.token = token;
        this.name = token.getContent();
    }

    @Override
    public Value eval(final IScope scope, final IEvalVisitor visitor) {
        return scope.find(name).orElseThrow(() ->
                new VariableNotDefinedException(token ,
                        String.format("%s is not defined.", name)));
    }

    @Override
    public Type resolveType(final IScope scope, final ITypeResolveVisitor visitor) {
        return scope.find(name).orElseThrow(() ->
                new VariableNotDefinedException(token ,
                        String.format("%s is not defined.", name)))
                .getType();
    }
}
