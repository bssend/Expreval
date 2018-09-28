package bssend.expreval.parser;

import bssend.expreval.parser.util.IStringSequence;
import bssend.expreval.parser.util.ITokenSequence;
import bssend.expreval.parser.util.TokenSequence;
import lombok.NonNull;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static bssend.expreval.parser.TokenType.STRING;
import static bssend.expreval.parser.TokenType.WHITESPACE;
import static java.util.Comparator.comparing;

public class Scanner implements IScanner {

    @Override
    public final ITokenSequence scan(@NonNull final IStringSequence seq) {

        var tokenTypes = Arrays.asList(TokenType.values()).stream()
                .sorted(comparing(TokenType::getPriority))
                .collect(Collectors.toList());

        var tokens = new ArrayList<Token>();

        while (!seq.isEnd()) {
            Token token = null;
            for (var tokenType : tokenTypes) {

                var length = seq.match("^" + tokenType.getPattern());

                if (length == 0)
                    continue;

                var position = seq.getPosition();
                var content = seq.next(length);

                if (tokenType == STRING)
                    content = content.substring(1, content.length() - 1);

                token = Token.builder()
                        .type(tokenType)
                        .position(position)
                        .length(length)
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
