package expreval.parser;

import expreval.parser.util.IStringSequence;
import expreval.parser.util.ITokenSequence;

public interface ILexer {
    ITokenSequence scan(final IStringSequence seq);
}
