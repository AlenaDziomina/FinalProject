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
public class DaoException extends Exception {

    public DaoException(){
    }

    public DaoException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoException(Throwable exception) {
        super(exception);
    }
    
    public DaoException(String message) {
        super(message);
    }
}
