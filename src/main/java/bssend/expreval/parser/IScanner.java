package bssend.expreval.parser;

import bssend.expreval.parser.util.IStringSequence;
import bssend.expreval.parser.util.ITokenSequence;

public interface IScanner {
    ITokenSequence scan(final IStringSequence seq);
}
