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
public class DaoAccessPermission extends DaoException {
    
    private static final String str = "Attempt to access the database without the required permissions. ";
    public DaoAccessPermission(String msg) {
        super(str + msg);
    }
    
}
