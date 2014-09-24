/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.RoleQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.dao.query.UserQuery;
import by.epam.project.entity.Role;
import by.epam.project.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlGuestDao implements GuestDao {
    
    protected MysqlGuestDao(){}

    @Override
    public void toRegistrate(Criteria criteria) throws DaoException {
        
        Criteria test1 = new Criteria();
        Object login = criteria.getParam(PARAM_NAME_LOGIN);
        if (login == null) {
            throw new DaoException("Login is empty.");
        }
        test1.addParam(PARAM_NAME_LOGIN, login);
        try {
            List<User> person = new UserQuery().load(test1);
            if (!person.isEmpty()) {
                throw new DaoException("Login is not unique.");
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
        Criteria test2 = new Criteria();
        Object email = criteria.getParam(PARAM_NAME_EMAIL);
        if (email == null) {
            throw new DaoException("Email is empty.");
        }
        test2.addParam(PARAM_NAME_EMAIL, email);
        try {
            List<User> person = new UserQuery().load(test2);
            if (!person.isEmpty()) {
                throw new DaoException("Email is not unique.");
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
        Integer id_role;
        Criteria test3 = new Criteria();
        Object role = criteria.getParam(PARAM_NAME_ROLE);
        if (role == null) {
            throw new DaoException("Role is empty.");
        }
        test3.addParam(PARAM_NAME_ROLE, role);
        try {
            List<Role> listRole = new RoleQuery().load(test3);
            if (listRole.isEmpty() || listRole.size() > 1) {
                throw new DaoException("Unnoun role in database.");
            } else {
                id_role = listRole.get(0).getIdRole();
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
        criteria.addParam(PARAM_NAME_ID_ROLE, id_role);
        
        List list = new ArrayList<>();
        list.add(new User(criteria));
        
        try {
            new UserQuery().save(list);
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }
    
    @Override
    public User toLogin(Criteria criteria) throws DaoException {
        
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

        
}
