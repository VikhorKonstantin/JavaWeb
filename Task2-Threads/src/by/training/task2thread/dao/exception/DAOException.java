package by.training.task2thread.dao.exception;
/**
 * DAO layer exception class.
 */
public class DAOException extends Exception {
    /**
     * Constructor without params.
     */
    public DAOException() {
    }

    /**
     * @param message exception message.
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public DAOException(final String message,
                        final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public DAOException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public DAOException(final String message, final Throwable cause,
                        final boolean enableSuppression,
                        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
