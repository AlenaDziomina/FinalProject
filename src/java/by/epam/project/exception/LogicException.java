package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class LogicException extends Exception {

    public LogicException(){
    }

    public LogicException(String message, Throwable exception) {
        super(message, exception);
    }

    public LogicException(Throwable exception) {
        super(exception);
    }

    public LogicException(String message) {
        super(message);
    }

}

