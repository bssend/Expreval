package bssend.expreval.compiler.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.var;

import java.util.regex.Pattern;

public class StringSequence implements IStringSequence{

    private final String source;

    @Getter
    private int position;

    public StringSequence(@NonNull final String source) {
        this.source = source;
        this.position = 0;
    }

    @Override
    public int match(@NonNull final String pattern) {
        var matcher = Pattern.compile(pattern)
                .matcher(source.substring(position));

        if (!matcher.find())
            return 0;

        return matcher.group(0).length();
    }

    @Override
    public String peek(final int length) {
        return source.substring(position, position + length);
    }

    @Override
    public String next(final int length) {
        var result = source.substring(position, position + length);
        position = position + length;
        return result;
    }

    @Override
    public boolean isEnd() {
        return position > source.length() - 1;
    }
}
