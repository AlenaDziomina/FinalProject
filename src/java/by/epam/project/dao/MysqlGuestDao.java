/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.UserQuery;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlGuestDao implements GuestDao {
    
    protected MysqlGuestDao(){}

    @Override
    public void to_registrate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User to_login(Criteria criteria) throws DaoException {
        
        try {
            List<User> person = new UserQuery().load(criteria);
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

    @Override
    public void to_view_discounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
