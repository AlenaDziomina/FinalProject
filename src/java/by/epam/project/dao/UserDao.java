/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;

/**
 *
 * @author User
 */
public interface UserDao extends AbstractDao {
    
    @Override
    public User changeOwnUser(Criteria bean, Criteria criteria) throws DaoException;

}
