package exception;

/**
 * @author mdarmanansari
 */
public class InvalidUndoMoveException extends RuntimeException {
    public InvalidUndoMoveException() {
        super();
    }

    public InvalidUndoMoveException(String message) {
        super(message);
    }

    public InvalidUndoMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUndoMoveException(Throwable cause) {
        super(cause);
    }

    protected InvalidUndoMoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
