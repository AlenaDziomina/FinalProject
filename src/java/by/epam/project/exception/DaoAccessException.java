package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoAccessException extends DaoException {

    private static final String ERROR_STR = "Attempt to access the database without the required permissions. ";

    public DaoAccessException(){
    }

    public DaoAccessException(String message, Throwable exception) {
        super(ERROR_STR + message, exception);
    }

    public DaoAccessException(Throwable exception) {
        super(exception);
    }

    public DaoAccessException(String message) {
        super(ERROR_STR + message);
    }

}
