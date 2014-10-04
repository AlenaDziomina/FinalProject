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
public class DaoConnectException extends DaoException {

    private static final String str = "Connection to database failed. ";
    
    public DaoConnectException(String msg) {
        super(str + msg);
    }
    
    
}
