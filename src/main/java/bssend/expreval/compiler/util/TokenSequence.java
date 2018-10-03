package bssend.expreval.compiler.util;

import bssend.expreval.compiler.Token;
import lombok.NonNull;
import lombok.var;

import java.util.List;

public class TokenSequence implements ITokenSequence{

    private final List<Token> tokens;
    private int position;

    public TokenSequence(@NonNull final List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    @Override
    public Token next() {
        var next = tokens.get(position);
        position++;
        return next;
    }

    @Override
    public Token peek() {
        return tokens.get(position);
    }

    @Override
    public Token peekNext() {
        return tokens.get(position + 1);
    }

    @Override
    public boolean isEnd() {
        return position > tokens.size() - 1;
    }

    @Override
    public boolean isNextEnd() {
        return position > tokens.size() - 2;
    }
}
