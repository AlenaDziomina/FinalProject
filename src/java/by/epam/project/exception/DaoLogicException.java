package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoLogicException extends DaoException {

    public DaoLogicException(){
    }

    public DaoLogicException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoLogicException(Throwable exception) {
        super(exception);
    }

    public DaoLogicException(String message) {
        super(message);
    }

}
