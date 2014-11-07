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

