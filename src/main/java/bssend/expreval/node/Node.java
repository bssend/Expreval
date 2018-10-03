package bssend.expreval.node;

import bssend.expreval.compiler.Token;
import bssend.expreval.type.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Node {

    @Getter
    private final NodeType nodeType;

    @Getter @Setter
    private Type type;

    @Getter
    private List<Token> tokens;

    protected Node(final NodeType nodeType, final Type type) {
        this.nodeType = nodeType;
        this.type = type;
    }
}
