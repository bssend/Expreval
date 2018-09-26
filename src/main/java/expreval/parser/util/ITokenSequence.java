package expreval.parser.util;

import expreval.parser.Token;

public interface ITokenSequence {
    Token next();
    Token peek();
    boolean isEnd();
}
