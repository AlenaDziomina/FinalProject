/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.dao.query.UserQuery;
import by.epam.project.entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlUserDao extends MysqlGuestDao implements UserDao {
   
    protected MysqlUserDao(){}

    @Override
    public User toChangeOwnUser(Criteria bean, Criteria criteria) throws DaoException {
        
        try {
            int updCount = new UserQuery().update(bean, criteria);
            if (updCount <= 0 || updCount > 1) {
                throw new DaoException("Error in update results.");
            } 
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
        try {
            List<User> person = new UserQuery().load(bean);
            if (person == null || person.size() > 1) {
                throw new DaoException("Error result of search.");
            } else {
                try {
                    return person.get(0);
                } catch (IndexOutOfBoundsException ex) {
                    return null;
                } 
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }


}
