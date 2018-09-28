package bssend.expreval.parser.util;

import bssend.expreval.parser.Token;

public interface ITokenSequence {
    Token next();
    Token peek();
    boolean isEnd();
}
