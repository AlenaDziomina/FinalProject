/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

/**
 *
 * @author User
 */
public class MysqlDaoFactory extends DaoFactory {

    private MysqlDaoFactory(){}
    
    static DaoFactory getInctance() {
       return new MysqlDaoFactory();
    }

    @Override
    public UserDao getUserDao() {
        return MysqlUserDao.getInstance();
    }

    @Override
    public AdminDao getAdminDao() {
        return MysqlAdminDao.getInstance();
    }
    
}
