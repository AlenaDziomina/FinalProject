package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoInitException extends DaoException {

    private static final String ERROR_STR = "Undeclared initialization parameters of database resource: ";

    public DaoInitException(){
    }

    public DaoInitException(String message, Throwable exception) {
        super(ERROR_STR + message, exception);
    }

    public DaoInitException(Throwable exception) {
        super(exception);
    }

    public DaoInitException(String message) {
        super(ERROR_STR + message);
    }

}
