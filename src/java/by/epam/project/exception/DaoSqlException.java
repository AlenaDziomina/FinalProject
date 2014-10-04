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
public class DaoSqlException extends DaoException {
    
    private static final String str = "Error in MySQL query: ";
    
    public DaoSqlException(String msg) {
        super(str + msg);
    }

    public DaoSqlException(String msg, Exception ex) {
        super(str + msg, ex);
    }
    
}
