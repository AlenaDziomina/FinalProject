package by.epam.project.exception;

/**
 *
 * @author Helena.Grouk
 */
public class DaoSqlException extends DaoException {

    private static final String ERROR_STR = "Error in MySQL query: ";

    public DaoSqlException(){
    }

    public DaoSqlException(String message, Throwable exception) {
        super(ERROR_STR + message, exception);
    }

    public DaoSqlException(Throwable exception) {
        super(exception);
    }

    public DaoSqlException(String message) {
        super(ERROR_STR + message);
    }

}
