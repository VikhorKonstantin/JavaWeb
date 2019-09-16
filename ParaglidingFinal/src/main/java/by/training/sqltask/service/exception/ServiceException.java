package by.training.sqltask.service.exception;
/**
 * Service layer exception class.
 */
public class ServiceException extends Exception {
    /**
     * Constructor without params.
     */
    public ServiceException() {
    }

    /**
     * @param message exception message.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public ServiceException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public ServiceException(final String message, final Throwable cause,
                            final boolean enableSuppression,
                            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
