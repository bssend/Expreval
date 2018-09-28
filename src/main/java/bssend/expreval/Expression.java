package bssend.expreval;

import bssend.expreval.node.INode;
import bssend.expreval.value.InternalValue;
import bssend.expreval.visitor.TypeResolveVisitor;
import bssend.expreval.parser.Parser;
import bssend.expreval.visitor.EvalVisitor;
import lombok.var;

public class Expression {

    private final INode rootNode;

    private Expression(INode rootNode) {
        this.rootNode = rootNode;
    }

    public static Expression compile(String source) {
        var rootNode = new Parser().parse(source);
        var typeResolver = new TypeResolveVisitor();

        rootNode.resolveType(typeResolver);

        return new Expression(rootNode);
    }

    public InternalValue eval() {
        return rootNode.eval(new EvalVisitor());
    }
}
