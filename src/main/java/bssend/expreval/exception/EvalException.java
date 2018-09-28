package bssend.expreval.exception;

public class EvalException extends ExprevalException {
    public EvalException() {
        super();
    }
    public EvalException(String message) {
        super(message);
    }

    public EvalException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvalException(Throwable cause) {
        super(cause);
    }

}
