package by.training.task1oop.exception;

/**
 * Exception which is thrown when arguments incorrect.
 */
public class WrongArgumentsException extends Exception {
    /**
     * Constructor without params.
     */
    public WrongArgumentsException() {
    }

    /**
     * @param message exception message.
     */
    public WrongArgumentsException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public WrongArgumentsException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public WrongArgumentsException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public WrongArgumentsException(final String message, final Throwable cause,
                                   final boolean enableSuppression,
                                   final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
