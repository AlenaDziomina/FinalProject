/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
