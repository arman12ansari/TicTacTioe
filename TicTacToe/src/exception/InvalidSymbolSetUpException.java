package exception;

/**
 * @author mdarmanansari
 */
public class InvalidSymbolSetUpException extends RuntimeException{

    public InvalidSymbolSetUpException() {
        super();
    }

    public InvalidSymbolSetUpException(String message) {
        super(message);
    }

    public InvalidSymbolSetUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSymbolSetUpException(Throwable cause) {
        super(cause);
    }

    protected InvalidSymbolSetUpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
