package bssend.expreval.exception;

import bssend.expreval.parser.Token;

public class ParseException extends CompileException{
    public ParseException(String message) {
        super(message);
    }

    public ParseException(Token token, String message) {
        super(token, message);
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
