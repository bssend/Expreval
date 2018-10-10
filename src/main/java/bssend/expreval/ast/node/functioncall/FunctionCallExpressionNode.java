package bssend.expreval.ast.node.functioncall;

import bssend.expreval.ast.node.ExpressionNode;
import bssend.expreval.ast.node.IExpressionNode;
import bssend.expreval.ast.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.value.*;
import bssend.expreval.visitor.IEvalVisitor;
import bssend.expreval.visitor.ITypeResolveVisitor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.List;

public class FunctionCallExpressionNode extends ExpressionNode implements IFunctionCallExpressionNode {

    @Getter
    private final Token token;
    @Getter
    private final String name;
    @Getter
    private final List<IExpressionNode> arguments;
    @Getter @Setter
    private Method function;

    public FunctionCallExpressionNode(Token nameToken, List<IExpressionNode> arguments) {
        super(NodeType.FunctionCall, null);
        this.token = nameToken;
        this.name = nameToken.getContent();
        this.arguments = arguments;
    }

    @Override
    public Value eval(IScope scope, IEvalVisitor visitor) {
        return visitor.visit(scope, this);
    }

    @Override
    public Type resolveType(IScope scope, ITypeResolveVisitor visitor) {
        return visitor.visit(scope, this);
    }
}
