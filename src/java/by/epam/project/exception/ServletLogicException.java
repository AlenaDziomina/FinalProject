package by.epam.project.exception;

import javax.servlet.ServletException;

/**
 *
 * @author Helena.Grouk
 */
public class ServletLogicException extends ServletException {

    public ServletLogicException(){
    }

    public ServletLogicException(String message, Throwable exception) {
        super(message, exception);
    }

    public ServletLogicException(Throwable exception) {
        super(exception);
    }

    public ServletLogicException(String message) {
        super(message);
    }

}
