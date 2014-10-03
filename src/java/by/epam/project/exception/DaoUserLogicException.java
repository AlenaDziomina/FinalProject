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

    public DaoUserLogicException(String msg) {
        super(msg);
    }
    
}
