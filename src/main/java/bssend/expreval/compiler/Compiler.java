package bssend.expreval.compiler;

import bssend.expreval.Expression;
import bssend.expreval.scope.IScope;
import bssend.expreval.visitor.ITypeResolveVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.var;

public class Compiler implements ICompiler {

    private final IParser parser = new Parser();
    private final ITypeResolveVisitor typeResolver = new TypeResolveVisitor();

    @Override
    public Expression compile(final IScope scope, final String s) {

        var ast = parser.parse(s);
        ast.resolveType(scope, typeResolver);

        return new Expression(scope, ast);
    }
}
