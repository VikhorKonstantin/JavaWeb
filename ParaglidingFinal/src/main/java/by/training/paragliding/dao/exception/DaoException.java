package by.training.paragliding.dao.exception;

public class DaoException extends Exception {
    /**
     * Constructor without params.
     */
    public DaoException() {
    }

    /**
     * @param message exception message.
     */
    public DaoException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public DaoException(final String message,
                        final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public DaoException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public DaoException(final String message, final Throwable cause,
                        final boolean enableSuppression,
                        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
