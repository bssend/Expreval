package bssend.expreval.exception;

public class ImplicitCastException extends EvalException {
    public ImplicitCastException(String message) {
        super(message);
    }

    public ImplicitCastException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImplicitCastException(Throwable cause) {
        super(cause);
    }
}
