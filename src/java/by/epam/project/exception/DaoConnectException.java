/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.exception;

/**
 *
 * @author User
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
