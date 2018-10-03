package bssend.expreval.compiler.util;

import bssend.expreval.compiler.Token;

public interface ITokenSequence {
    Token next();
    Token peek();
    Token peekNext();
    boolean isEnd();
    boolean isNextEnd();
}
