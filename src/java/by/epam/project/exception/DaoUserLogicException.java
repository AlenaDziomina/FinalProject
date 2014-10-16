/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.exception;

import javax.servlet.ServletException;

/**
 *
 * @author User
 */
public class DaoUserLogicException extends ServletException {

    public DaoUserLogicException(){
    }
    
    public DaoUserLogicException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoUserLogicException(Throwable exception) {
        super(exception);
    }
    
    public DaoUserLogicException(String message) {
        super(message);
    }
    
}
