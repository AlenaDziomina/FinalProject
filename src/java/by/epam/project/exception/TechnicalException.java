package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class TechnicalException extends Exception {

    public TechnicalException(){
    }

    public TechnicalException(String message, Throwable exception) {
        super(message, exception);
    }

    public TechnicalException(Throwable exception) {
        super(exception);
    }

    public TechnicalException(String message) {
        super(message);
    }

}
