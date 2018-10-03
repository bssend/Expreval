package bssend.expreval.compiler.util;

public interface IStringSequence {
    int match(String pattern);
    String peek(int length);
    String next(int length);
    boolean isEnd();
    int getPosition();
}
