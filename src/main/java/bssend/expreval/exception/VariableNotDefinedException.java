package bssend.expreval.exception;

import bssend.expreval.compiler.Token;

public class VariableNotDefinedException extends CompileException {
    public VariableNotDefinedException(String message) {
        super(message);
    }

    public VariableNotDefinedException(Token token, String message) {
        super(token, message);
    }

    public VariableNotDefinedException(Token token, Throwable t) {
        super(token, t);
    }
}
