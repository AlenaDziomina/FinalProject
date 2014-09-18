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
