/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.entquery.UserQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlUserDao extends MysqlGuestDao implements UserDao {
   
    protected MysqlUserDao(){}
    
    @Override
    public User changeOwnUser(Criteria bean, Criteria criteria) throws DaoException {
        
        try {
            int updCount = new UserQuery().update(bean, criteria, updateDao, mysqlConn).get(0);
            if (updCount <= 0 || updCount > 1) {
                throw new DaoException("Error in update results.");
            } 
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
        
        try {
            List<User> person = new UserQuery().load(bean, loadDao, mysqlConn);
            if (person == null || person.size() > 1) {
                throw new DaoException("Error result of search.");
            } else {
                try {
                    return person.get(0);
                } catch (IndexOutOfBoundsException ex) {
                    return null;
                } 
            }
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }
}
