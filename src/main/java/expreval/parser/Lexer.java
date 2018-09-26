package expreval.parser;

import expreval.parser.util.IStringSequence;
import expreval.parser.util.ITokenSequence;
import expreval.parser.util.TokenSequence;
import lombok.NonNull;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static expreval.parser.TokenType.STRING;
import static expreval.parser.TokenType.WHITESPACE;
import static java.util.Comparator.comparing;

public class Lexer implements ILexer {

    @Override
    public final ITokenSequence scan(@NonNull final IStringSequence seq) {

        var tokenTypes = Arrays.asList(TokenType.values()).stream()
                .sorted(comparing(TokenType::getPriority))
                .collect(Collectors.toList());

        var tokens = new ArrayList<Token>();

        while (!seq.isEnd()) {
            Token token = null;
            for (var tokenType : tokenTypes) {

                var length = seq.match(tokenType.getPattern());

                if (length == 0)
                    continue;

                var content = seq.next(length);

                if (tokenType == STRING)
                    content = content.substring(1, content.length() - 1);

                token = Token.builder()
                        .type(tokenType)
                        .content(content)
                        .build();
                break;
            }

            if (token == null)
                throw new RuntimeException("not match token.");

            tokens.add(token);
        }

        return new TokenSequence(tokens.stream()
                .filter(t -> t.getType() != WHITESPACE)
                .collect(Collectors.toList()));
    }

}
