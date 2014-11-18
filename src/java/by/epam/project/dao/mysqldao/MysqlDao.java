/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.mysqldao.querygeneric.MysqlGenericFactory;
import by.epam.project.dao.query.generic.*;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
class MysqlDao implements AbstractDao {
    
    protected Connection mysqlConn;
    protected static GenericLoadQuery loadGeneric = MysqlGenericFactory.getLoadInstance();
    protected static GenericSaveQuery saveGeneric = MysqlGenericFactory.getSaveInstance();
    protected static GenericUpdateQuery updateGeneric = MysqlGenericFactory.getUpdateInstance();
    protected static GenericDeleteQuery deleteGeneric = MysqlGenericFactory.getDeleteInstance();
    
    @Override
    public void open() throws DaoException {
        mysqlConn = MysqlConnectionPool.getConnection();
    }
    
    @Override
    public void close() throws DaoException {
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
