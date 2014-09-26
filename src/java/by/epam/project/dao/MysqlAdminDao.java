/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import java.sql.Connection;

/**
 *
 * @author User
 */
public class MysqlAdminDao extends MysqlUserDao implements MysqlDao,  AdminDao {
    
    private Connection mysqlConn;
    
    protected MysqlAdminDao() throws DaoException{
        mysqlConn = MysqlDao.getConnection();
    }
    
    @Override
    public void close() throws DaoException {
        MysqlDao.returnConnection(mysqlConn);
    }

}
