package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoConnectException extends DaoException {

    private static final String ERROR_STR = "Connection to database failed. ";

    public DaoConnectException(){
    }

    public DaoConnectException(String message, Throwable exception) {
        super(ERROR_STR + message, exception);
    }

    public DaoConnectException(Throwable exception) {
        super(exception);
    }

    public DaoConnectException(String message) {
        super(ERROR_STR + message);
    }
}
