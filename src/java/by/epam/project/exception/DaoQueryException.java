package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoQueryException extends DaoException {

    private static final String ERROR_STR = "Exception in database query.";

    public DaoQueryException(){
    }

    public DaoQueryException(String message, Throwable exception) {
        super(ERROR_STR + message, exception);
    }

    public DaoQueryException(Throwable exception) {
        super(exception);
    }

    public DaoQueryException(String message) {
        super(ERROR_STR + message);
    }

}
