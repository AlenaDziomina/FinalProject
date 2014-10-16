/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.UserDao;
import by.epam.project.dao.entquery.UserQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlUserDao extends MysqlGuestDao implements UserDao {
   
    protected MysqlUserDao(){}
    
    @Override
    public List<Integer> updateUser(Criteria bean, Criteria criteria) throws DaoException {
        
        return new UserQuery().update(bean, criteria, updateDao, mysqlConn);
    }
}
