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
public class DaoQueryException extends DaoException {
    
    private static final String str = "Exception in database query.";

    public DaoQueryException(String msg) {
        super(str + msg);
    }

    public DaoQueryException(String msg, DaoException ex) {
        super(str + msg, ex);
    }

    
    
}
