package bssend.expreval.exception;

import bssend.expreval.compiler.Token;
import lombok.Getter;

public class CompileException extends RuntimeException{

    @Getter
    private Token token;

    public CompileException(String message) {
        super(message);
    }
    public CompileException(Token token, String message) {
        super(message);
        this.token = token;
    }
    public CompileException(Token token, Throwable t) {
        super(t);
        this.token = token;
    }
}