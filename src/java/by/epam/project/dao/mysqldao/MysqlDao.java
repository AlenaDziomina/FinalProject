/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.mysqlquery.MysqlGenericDeleteQuery;
import by.epam.project.dao.query.mysqlquery.MysqlGenericLoadQuery;
import by.epam.project.dao.query.mysqlquery.MysqlGenericSaveQuery;
import by.epam.project.dao.query.mysqlquery.MysqlGenericUpdateQuery;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        mysqlConn = MysqlConnectionPool.getConnection();
    }
    
    @Override
    public void close() throws DaoException {
        try {
            mysqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        MysqlConnectionPool.returnConnection(mysqlConn);
    }
    
    @Override
    public void rollback() throws DaoException {
        try {
            mysqlConn.rollback();
        } catch (SQLException ex) {
            throw new DaoConnectException("Rollback failed.");
        }
    }
    
    @Override
    public void commit() throws DaoException {
        try {
            mysqlConn.commit();
        } catch (SQLException ex) {
            throw new DaoConnectException("Error in commit connection in pool.");
        }
    }
    
}
