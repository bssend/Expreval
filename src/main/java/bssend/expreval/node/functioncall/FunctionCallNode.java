package bssend.expreval.node.functioncall;

import bssend.expreval.exception.EvalException;
import bssend.expreval.exception.FunctionNotFoundException;
import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.function.FunctionFactory;
import bssend.expreval.node.INode;
import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.Getter;
import lombok.val;
import lombok.var;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class FunctionCallNode extends Node implements IFunctionCallNode{

    private final Token nameToken;
    private final String name;
    private final List<INode> arguments;

    private Method function;

    public FunctionCallNode(Token nameToken, List<INode> arguments) {
        super(NodeType.FunctionCall, null);
        this.nameToken = nameToken;
        this.name = nameToken.getContent();
        this.arguments = arguments;
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {

        var argTypes = arguments.stream()
                .map(arg -> arg.getType())
                .collect(Collectors.toList());

        try {
            Object result = function.invoke(null,
                    arguments.stream()
                        .map(arg -> arg.eval(scope, visitor).value())
                        .toArray());

            return Value.ofType(getType(), result);

        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {

        // Resolve argument types.
        var argTypes = arguments.stream()
                .map(arg -> arg.resolveType(scope, visitor))
                .collect(Collectors.toList());

        // Resolve function return type.
        try {
            function =
                    FunctionFactory.create(name, argTypes);

            var returnType =
                    Type.fromJavaType(function.getReturnType())
                        .orElseThrow(() -> new TypeResolveException(nameToken,
                                "Cannot resolve function return type."));

            this.setType(returnType);

        } catch (FunctionNotFoundException ex) {
            throw new TypeResolveException(nameToken, ex);
        }

        return this.getType();
    }
}
