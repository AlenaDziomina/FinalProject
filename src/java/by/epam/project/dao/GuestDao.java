/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface GuestDao extends AbstractDao {
    
    //metods
    public void toRegistrate(Criteria criteria) throws DaoException;
    public User toLogin(Criteria criteria) throws DaoException;
    public List<Country> toShowCountries(Criteria criteria) throws DaoException;
    public List<City> toShowCities(Criteria criteria) throws DaoException;
    
}
