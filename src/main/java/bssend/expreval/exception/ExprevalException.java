package bssend.expreval.exception;

public class ExprevalException extends RuntimeException {

    public ExprevalException() {
        super();
    }

    public ExprevalException(String message) {
        super(message);
    }

    public ExprevalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExprevalException(Throwable cause) {
        super(cause);
    }
}
