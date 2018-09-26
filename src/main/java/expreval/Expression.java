package expreval;

import expreval.node.INode;
import expreval.node.value.IValueNode;
import expreval.parser.Parser;
import expreval.visitor.EvaluateVisitor;
import lombok.var;

public class Expression {

    private final INode rootNode;

    private Expression(INode rootNode) {
        this.rootNode = rootNode;
    }

    public static Expression compile(String source) {
        var rootNode = new Parser().parse(source);
        return new Expression(rootNode);
    }

    public IValueNode eval() {
        return rootNode.accept(new EvaluateVisitor());
    }
}
