package bssend.expreval.compiler;

import bssend.expreval.compiler.util.IStringSequence;
import bssend.expreval.compiler.util.ITokenSequence;

public interface IScanner {
    ITokenSequence scan(final IStringSequence seq);
}
