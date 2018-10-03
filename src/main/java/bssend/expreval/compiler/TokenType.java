package bssend.expreval.compiler;

public enum TokenType {
    STRING(0,"'.+?'") ,
    NUMBER(1, "-?[0-9]+\\.[0-9]+") ,
    INTEGER(2,"-?[0-9]+") ,
    BOOLEAN(3,"(true|false)") ,

    WHITESPACE(4, "\\s") ,
    TAB(5, "\\t") ,
    OPEN_PAREN(6, "\\(") ,
    CLOSE_PAREN(7, "\\)") ,
    IDENTIFIER(8, "[a-zA-Z][a-zA-Z0-9]*") ,
    COMMA(9,",") ,

    PLUS(10,"\\+") ,
    MINUS(11, "\\-") ,
    STAR(12, "\\*") ,
    SLASH(13, "\\/") ,
    PERCENT(14, "%") ,

    AMPERSAND_AMPERSAND(15, "&&") ,
    PIPE_PIPE(16, "\\|\\|") ,

    EQUAL_EQUAL(17, "==") ,
    BANG_EQUAL(18, "!=") ,
    OPEN_ANGLE_EQUAL(19, "<=") ,
    OPEN_ANGLE(20, "<") ,
    CLOSE_ANGLE_EQUAL(21, ">=") ,
    CLOSE_ANGLE(22, ">") ;

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
