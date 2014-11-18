package by.epam.project.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
