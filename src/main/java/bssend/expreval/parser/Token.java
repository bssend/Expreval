package bssend.expreval.parser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private TokenType type;
    private String content;
    private int position;
    private int length;
}
