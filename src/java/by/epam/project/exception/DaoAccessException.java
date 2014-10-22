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
