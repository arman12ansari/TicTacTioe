package exception;

/**
 * @author mdarmanansari
 */
public class InvalidPlayerException extends RuntimeException{

    public InvalidPlayerException() {
        super();
    }

    public InvalidPlayerException(String message) {
        super(message);
    }

    public InvalidPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlayerException(Throwable cause) {
        super(cause);
    }

    protected InvalidPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
