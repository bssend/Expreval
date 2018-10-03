package bssend.expreval.compiler;

import bssend.expreval.node.INode;
import bssend.expreval.scope.IScope;
import bssend.expreval.type.Type;
import bssend.expreval.visitor.IVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.var;

public class Compiler implements ICompiler {

    private final IParser parser = new Parser();
    private final TypeResolveVisitor typeResolver = new TypeResolveVisitor();

    @Override
    public INode compile(final IScope scope, final String s) {

        var rootNode = parser.parse(s);
        rootNode.resolveType(scope, typeResolver);

        return rootNode;
    }
}
