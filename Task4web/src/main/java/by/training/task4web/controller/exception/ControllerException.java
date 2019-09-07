package by.training.task4web.controller.exception;
/**
 * Controller exception class.
 */
public class ControllerException extends Exception {
    /**
     * Constructor without params.
     */
    public ControllerException() {
    }

    /**
     * @param message exception message.
     */
    public ControllerException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public ControllerException(final String message,
                         final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public ControllerException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public ControllerException(final String message, final Throwable cause,
                         final boolean enableSuppression,
                         final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
