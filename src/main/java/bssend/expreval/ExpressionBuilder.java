package bssend.expreval;

import bssend.expreval.compiler.Compiler;
import bssend.expreval.compiler.ICompiler;
import bssend.expreval.exception.CompileException;
import bssend.expreval.scope.IScope;
import bssend.expreval.scope.Scope;
import bssend.expreval.value.BooleanValue;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.NumberValue;
import bssend.expreval.value.StringValue;
import com.google.common.base.Strings;
import lombok.var;

public class ExpressionBuilder {

    private final ICompiler compiler = new Compiler();
    private final IScope scope = new Scope();

    public ExpressionBuilder var(String name, String value) {
        scope.addVar(name, new StringValue(value));
        return this;
    }

    public ExpressionBuilder var(String name, int value) {
        scope.addVar(name, new IntegerValue(value));
        return this;
    }

    public ExpressionBuilder var(String name, double value) {
        scope.addVar(name, new NumberValue(value));
        return this;
    }

    public ExpressionBuilder var(String name, boolean value) {
        scope.addVar(name, new BooleanValue(value));
        return this;
    }

    /**
     * Compile source string.
     * @param source
     * @return
     */
    public Expression compile(String source) {
        try {
            return compiler.compile(scope, source);
        } catch (CompileException ex) {

            System.out.println(ex.getMessage());
            System.out.println(source);
            System.out.println(
                    Strings.repeat(" ", ex.getToken().getPosition()) +
                            Strings.repeat("^", ex.getToken().getLength()));
            throw ex;
        }

    }

}
