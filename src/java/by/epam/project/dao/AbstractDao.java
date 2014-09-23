/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;


import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public interface AbstractDao {
    
    static final String PARAM_NAME_ID_USER = "id_user";
    static final String PARAM_NAME_LOGIN = "login";
    static final String PARAM_NAME_PASSWORD = "password";
    static final String PARAM_NAME_ROLE = "role";
    static final String PARAM_NAME_EMAIL = "email";
    static final String PARAM_NAME_PHONE = "phone";
    static final String PARAM_NAME_LOCALE = "locale";
    static final String PARAM_NAME_LANGUAGE = "language";
    static final String PARAM_NAME_DISCOUNT = "discount";
    static final String PARAM_NAME_BALANCE = "balance";
    static final String PARAM_NAME_DATE = "date";
    static final String PARAM_NAME_ID_ROLE = "id_role";
    
    static public Connection getConnection() throws DaoException{
        try {
            return ConnectionPool.getConnection();
        } catch (SQLException ex) {
            throw new DaoException("Cant take connection to database.");
        }
    }
    
    static public void returnConnection(Connection con) throws DaoException {
        try {
            ConnectionPool.returnConnection(con);
        } catch (SQLException ex) {
            throw new DaoException("Cant return connection in pool.");
        }
    }
    
}
