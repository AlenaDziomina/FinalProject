/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.GuestDao;
import static by.epam.project.dao.mysqldao.MysqlDao.saveDao;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.entity.CityQuery;
import by.epam.project.dao.query.entity.CountryQuery;
import by.epam.project.dao.query.entity.DescriptionQuery;
import by.epam.project.dao.query.entity.DirectionCityQuery;
import by.epam.project.dao.query.entity.DirectionCountryQuery;
import by.epam.project.dao.query.entity.DirectionQuery;
import by.epam.project.dao.query.entity.DirectionStayHotelQuery;
import by.epam.project.dao.query.entity.HotelQuery;
import by.epam.project.dao.query.entity.RoleQuery;
import by.epam.project.dao.query.entity.SearchQuery;
import by.epam.project.dao.query.entity.TourQuery;
import by.epam.project.dao.query.entity.TourTypeQuery;
import by.epam.project.dao.query.entity.TransModeQuery;
import by.epam.project.dao.query.entity.UserQuery;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Role;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlGuestDao extends MysqlDao implements GuestDao {
    
    protected MysqlGuestDao(){}

    @Override
    public List<Role> showRoles(Criteria criteria) throws DaoException {
        return new RoleQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<User> showUsers(Criteria criteria) throws DaoException {
        return new UserQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public Integer createNewUser(Criteria criteria) throws DaoException {
        User user = UserQuery.createBean(criteria);
        List list = new ArrayList<>();
        list.add(user);
        List<Integer> res = new UserQuery().save(list, saveDao, mysqlConn);
        return res.get(0);
    }

    @Override
    public List<Description> showDescriptions(Criteria criteria) throws DaoException {
        return new DescriptionQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Country> showCountries(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_COUNTRY_STATUS) == null) {
            criteria.addParam(DAO_COUNTRY_STATUS, ACTIVE);
        }
        return new CountryQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<City> showCities(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_CITY_STATUS) == null) {
            criteria.addParam(DAO_CITY_STATUS, ACTIVE);
        }
        return new CityQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<Hotel> showHotels(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_HOTEL_STATUS) == null) {
            criteria.addParam(DAO_HOTEL_STATUS, ACTIVE);
        }
        return new HotelQuery().load(criteria, loadDao, mysqlConn);
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
        if (criteria.getParam(DAO_DIRECTION_STATUS) == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
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
        if (criteria.getParam(DAO_TOUR_STATUS) == null) {
            criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        }
        if (criteria.getParam(DAO_TOUR_DATE_FROM) == null && criteria.getParam(DAO_TOUR_DATE_TO) == null) {
            criteria.addParam(DAO_TOUR_DATE_FROM, new Date());
        }
        return new TourQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<Tour> searchTours(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_DIRECTION_STATUS) == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
        if (criteria.getParam(DAO_TOUR_STATUS) == null) {
            criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        }
        Date currDate = new Date();
        Date dateFrom = (Date) criteria.getParam(DAO_TOUR_DATE_FROM);
        if (dateFrom == null || dateFrom.before(currDate)) {
            criteria.addParam(DAO_TOUR_DATE_FROM, currDate);
        }
        Date dateTo = (Date) criteria.getParam(DAO_TOUR_DATE_TO);
        if (dateTo != null && dateTo.before(currDate)) {
            criteria.addParam(DAO_TOUR_DATE_TO, currDate);
        }
        
        return new SearchQuery().load(criteria, loadDao, mysqlConn);
    }
   
}
