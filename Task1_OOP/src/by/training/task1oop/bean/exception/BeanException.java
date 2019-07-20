package by.training.task1oop.bean.exception;

public class BeanException extends Exception {
    /**
     * Constructor without params.
     */
    public BeanException() {
    }

    /**
     * @param message exception message.
     */
    public BeanException(final String message) {
        super(message);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     */
    public BeanException(final String message,
                         final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause of  exception
     */
    public BeanException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message.
     * @param cause cause of  exception
     * @param enableSuppression enable(true) or not(false) suppression
     * @param writableStackTrace writable(true) or not(false) Stack Trace
     */
    public BeanException(final String message, final Throwable cause,
                         final boolean enableSuppression,
                         final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
