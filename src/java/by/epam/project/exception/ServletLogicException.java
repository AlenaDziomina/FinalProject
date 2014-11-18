/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
