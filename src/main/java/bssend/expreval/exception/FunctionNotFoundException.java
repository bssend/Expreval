package bssend.expreval.exception;

import bssend.expreval.compiler.Token;

public class FunctionNotFoundException extends CompileException {
    public FunctionNotFoundException(String message) {
        super(message);
    }

    public FunctionNotFoundException(Token token, String message) {
        super(token, message);
    }

    public FunctionNotFoundException(Token token, Throwable t) {
        super(token, t);
    }
}
