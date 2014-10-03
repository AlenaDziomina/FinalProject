/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class DirectionLogic {
    
    public static List<Direction> getDirections(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            List<Direction> directions = dao.showDirections(criteria);
            fillDirections(directions, dao);
            
            return directions;   
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    
    private static void fillDirections(List<Direction> directions, AbstractDao dao) throws DaoException {
        getCountryCollection(dao, directions);
        getCityCollection(dao, directions);
        getStayHotelCollection(dao, directions);  
    }
    
    private static void getStayHotelCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_ID_DIRECTION, dir.getIdDirection());
            List<DirectionStayHotel> stays = dao.showDirectionStayHotel(crit);
            dir.setStayCollection(getHotelInfo(dao, stays));
        }
    }
    
    public static List<DirectionStayHotel> getHotelInfo(AbstractDao dao, List<DirectionStayHotel> stays) throws DaoException {
        for (DirectionStayHotel st : stays) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_HOTEL, st.getStayHotel().getIdHotel());
            List<Hotel> hotels = dao.showHotels(crit);
            st.setStayHotel(hotels.get(0));
        }
        return stays;
    }
    
    private static void getCityCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_ID_DIRECTION, dir.getIdDirection());
            List<LinkDirectionCity> links = dao.showLinkDirectionCity(crit);
            dir.setCityCollection(getCityInfo(dao, links));
        }
    }
    
    public static List<City> getCityInfo(AbstractDao dao, List<LinkDirectionCity> links) throws DaoException {
        List<City> list = new ArrayList<>();
        for (LinkDirectionCity link : links) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_CITY, link.getIdCity());
            List<City> cities = dao.showCities(crit);
            if (cities != null) {
                list.addAll(cities);
            }
        }
        return list;
    }
    
    private static void getCountryCollection(AbstractDao dao, List<Direction> directions) throws DaoException {  
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_ID_DIRECTION, dir.getIdDirection());
            List<LinkDirectionCountry> links = dao.showLinkDirectionCountry(crit);
            dir.setCountryCollection(getCountryInfo(dao, links));
        }
    }
    
    public static List<Country> getCountryInfo(AbstractDao dao, List<LinkDirectionCountry> links) throws DaoException {
        List<Country> list = new ArrayList<>();
        for (LinkDirectionCountry link : links) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_COUNTRY, link.getIdCountry());
            List<Country> countries = dao.showCountries(crit);
            if (countries != null) {
                list.addAll(countries);
            }
        }
        return list;
    }

    public static Integer redactDirection(Criteria criteria) throws DaoException {
        
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idDirection = (Integer) criteria.getParam(PARAM_NAME_ID_DIRECTION);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {   
            if (idDirection == null) {      
                return createDirection(criteria, dao);
            } else {
                return updateDirection(criteria, dao);
            } 
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }

    private static Integer createDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.createNewDirection(criteria).get(0);   
    }
    
    private static Integer updateDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria beans1 = new Criteria();
        Criteria beans2 = new Criteria();
        beans1.addParam(DAO_ID_DESCRIPTION, criteria.getParam(DAO_ID_DESCRIPTION));
        beans2.addParam(DAO_ID_DIRECTION, criteria.getParam(DAO_ID_DIRECTION));
        criteria.remuveParam(DAO_ID_DIRECTION);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        Integer idDescription = dao.updateDescription(beans1, criteria).get(0);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.updateDirection(beans2, criteria).get(0);
    }
    
}
