/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import static by.epam.project.dao.MysqlDao.saveDao;
import by.epam.project.dao.entquery.*;
import by.epam.project.dao.entquery.CountryQuery;
import by.epam.project.dao.entquery.DirectionQuery;
import by.epam.project.dao.entquery.HotelQuery;
import by.epam.project.dao.entquery.RoleQuery;
import by.epam.project.dao.entquery.TourTypeQuery;
import by.epam.project.dao.entquery.TransModeQuery;
import by.epam.project.dao.entquery.UserQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Order;
import by.epam.project.entity.Role;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoLogicException;
import by.epam.project.exception.DaoQueryException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlGuestDao extends MysqlDao implements GuestDao {
    
    protected MysqlGuestDao(){}

    @Override
    public List<Role> showRoles(Criteria criteria) throws DaoException {
        try {
            List<Role> roles = new RoleQuery().load(criteria, loadDao, mysqlConn);
            return roles;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }
    
    @Override
    public List<User> showUsers(Criteria criteria) throws DaoException {
        try {
            List<User> users = new UserQuery().load(criteria, loadDao, mysqlConn);
            return users;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }
    
    @Override
    public Integer createNewUser(Criteria criteria) throws DaoException {
        try {
            User user = UserQuery.createBean(criteria);
            List list = new ArrayList<>();
            list.add(user);
            List<Integer> res = new UserQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoLogicException("New user not created.");
            } else {
                return res.get(0);
            }
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public List<Description> showDescriptions(Criteria criteria) throws DaoException {
        try {
            List<Description> desc = new DescriptionQuery().load(criteria, loadDao, mysqlConn);
            return desc;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }
    
    @Override
    public List<Country> showCountries(Criteria criteria) throws DaoException {
        try {
            List<Country> countries = new CountryQuery().load(criteria, loadDao, mysqlConn);
            return countries;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public List<City> showCities(Criteria criteria) throws DaoException {
        try {
            List<City> cities = new CityQuery().load(criteria, loadDao, mysqlConn);
            return cities;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public List<Hotel> showHotels(Criteria criteria) throws DaoException {
        try {
            List<Hotel> hotels = new HotelQuery().load(criteria, loadDao, mysqlConn);
            return hotels;
        } catch (DaoQueryException ex) {
            throw new DaoException("Error in query.");
        }
    }
    
    @Override
    public List<TourType> showTourTypes (Criteria criteria) throws DaoException {
            return new TourTypeQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<TransMode> showTransModes (Criteria criteria) throws DaoException {
        return new TransModeQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        return new DirectionQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException {
        return new DirectionCountryQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException {
        return new DirectionCityQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException {
        return new DirectionStayHotelQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        return new TourQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Order> showOrders(Criteria criteria) throws DaoException {
        return new OrderQuery().load(criteria, loadDao, mysqlConn);
    }
   
}
