package by.epam.project.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class DaoInitException extends DaoException {
    
    private static final String str = "Undeclared initialization parameters of database resource: ";

    public DaoInitException(String msg) {
        super(str + msg);
    }
    
}
