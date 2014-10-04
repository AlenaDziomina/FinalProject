/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.MysqlGenericDeleteQuery;
import by.epam.project.dao.query.MysqlGenericLoadQuery;
import by.epam.project.dao.query.MysqlGenericSaveQuery;
import by.epam.project.dao.query.MysqlGenericUpdateQuery;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MysqlDao implements AbstractDao {
    
    protected Connection mysqlConn;
    protected static final GenericLoadQuery loadDao = new MysqlGenericLoadQuery();
    protected static final GenericSaveQuery saveDao = new MysqlGenericSaveQuery();
    protected static final GenericUpdateQuery updateDao = new MysqlGenericUpdateQuery();
    protected static final GenericDeleteQuery deleteDao = new MysqlGenericDeleteQuery();
    
    @Override
    public void open() throws DaoException {
        mysqlConn = MysqlDao.getConnection();
    }
    
    @Override
    public void close() throws DaoException {
        MysqlDao.returnConnection(mysqlConn);
    }
    
    @Override
    public void rollback() throws DaoException {
        try {
            mysqlConn.rollback();
        } catch (SQLException ex) {
            throw new DaoConnectException("Rollback failed.");
        }
    }
    
    private static Connection getConnection() throws DaoException{
        try {
            return ConnectionPool.getConnection();
        } catch (SQLException ex) {
            throw new DaoConnectException("Cant take connection to database.");
        }
    }
    
    private static void returnConnection(Connection con) throws DaoException {
        
        try {
            con.commit();
        } catch (SQLException ex) {
            throw new DaoConnectException("Error in commit connection in pool.");
        }
        try {
            ConnectionPool.returnConnection(con);
        } catch (SQLException ex) {
            throw new DaoConnectException("Cant return connection in pool.");
        }
    }
    
}
