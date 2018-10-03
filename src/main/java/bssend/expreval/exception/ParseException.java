package bssend.expreval.exception;

import bssend.expreval.compiler.Token;

public class ParseException extends CompileException{
    public ParseException(String message) {
        super(message);
    }

    public ParseException(Token token, String message) {
        super(token, message);
    }

    public ParseException(Token token, Throwable t) {
        super(token, t);
    }

//    public ParseException() {
//        super();
//    }
//    public ParseException(String message) {
//        super(message);
//    }
//
//    public ParseException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public ParseException(Throwable cause) {
//        super(cause);
//    }
}
