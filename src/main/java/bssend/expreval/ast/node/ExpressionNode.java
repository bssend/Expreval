package bssend.expreval.ast.node;

import bssend.expreval.compiler.Token;
import bssend.expreval.type.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ExpressionNode {

    @Getter
    private final NodeType nodeType;

    @Getter @Setter
    private Type type;

    protected ExpressionNode(final NodeType nodeType, final Type type) {
        this.nodeType = nodeType;
        this.type = type;
    }

    public Type setAndGetType(Type type) {
        this.type = type;
        return this.type;
    }
}
