package bssend.expreval.compiler;

public enum TokenType {
    STRING(0,"'.+?'") ,
    NUMBER(1, "[0-9]+\\.[0-9]+") ,
    INTEGER(2,"[0-9]+") ,
    BOOLEAN(3,"(true|false)") ,

    WHITESPACE(4, "\\s") ,
    TAB(5, "\\t") ,
    OPEN_PAREN(6, "\\(") ,
    CLOSE_PAREN(7, "\\)") ,
    IDENTIFIER(8, "[a-zA-Z][a-zA-Z0-9]*") ,
    COMMA(9,",") ,

    AMPERSAND_AMPERSAND(10, "&&") ,
    PIPE_PIPE(11, "\\|\\|") ,

    EQUAL_EQUAL(12, "==") ,
    BANG_EQUAL(13, "!=") ,
    OPEN_ANGLE_EQUAL(14, "<=") ,
    OPEN_ANGLE(15, "<") ,
    CLOSE_ANGLE_EQUAL(16, ">=") ,
    CLOSE_ANGLE(17, ">") ,

    BANG(18, "!") ,
    PLUS(19,"\\+") ,
    MINUS(20, "\\-") ,
    STAR(21, "\\*") ,
    SLASH(22, "\\/") ,
    PERCENT(23, "%");

    private final String pattern;
    private final int priority;

    TokenType(int priority, String pattern) {
        this.priority = priority;
        this.pattern = pattern;
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }
}
