package bssend.expreval.exception;

import bssend.expreval.parser.Token;

public class TypeResolveException extends CompileException {
    public TypeResolveException(String message) {
        super(message);
    }

    public TypeResolveException(Token token, String message) {
        super(token, message);
    }
//    public TypeResolveException() {
//        super();
//    }
//    public TypeResolveException(String message) {
//        super(message);
//    }
//
//    public TypeResolveException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public TypeResolveException(Throwable cause) {
//        super(cause);
//    }
}
