/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_STATUS;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionLogic extends AbstractLogic {
    
    @Override
    List<Direction> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Direction> directions = dao.showDirections(criteria);
        fillDirections(directions, dao);
         
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            crit.addParam(DAO_TOUR_STATUS, criteria.getParam(DAO_TOUR_STATUS));
            crit.addParam(DAO_TOUR_DATE_FROM, criteria.getParam(DAO_TOUR_DATE_FROM));
            crit.addParam(DAO_TOUR_DATE_TO, criteria.getParam(DAO_TOUR_DATE_TO));
            List<Tour> list = dao.showTours(crit);
            dir.setTourCollection(list);
        }
        return directions;   
    }
    
    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDirection = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        if (idDirection != null) {
            return updateDirection(criteria, dao);
        } else {      
            return createDirection(criteria, dao);
        } 
    }

    private static Integer createDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        List<Integer> res =  dao.createNewDirection(criteria);   
        if (res != null && !res.isEmpty()) {
            Integer idDirection = res.get(0);
            criteria.addParam(DAO_ID_DIRECTION, idDirection);
            List<Integer> res1 = dao.createNewDirectionCountryLinks(criteria);
            List<Integer> res2 = dao.createNewDirectionCityLinks(criteria);
            List<Integer> res3 = dao.createNewDirectionStayHotels(criteria);
            return idDirection;
        } else {
            throw new DaoException("Error in hotel query.");
        }
    }
    
    private static Integer updateDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDirection = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        if (idDirection != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DIRECTION, idDirection);
            dao.updateDirection(beans, criteria);
        }
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, idDirection);
            dao.updateDirectionCountryLinks(crit, criteria);
            dao.updateDirectionCityLinks(crit, criteria);
            dao.updateDirectionStayHotels(crit, criteria);
        }
        return idDirection;
    }
    
    public static void fillDirections(List<Direction> directions, AbstractDao dao) throws DaoException {
        getCountryCollection(dao, directions);
        getCityCollection(dao, directions);
        getStayHotelCollection(dao, directions);  
        getDescription(dao, directions);
        getTransMode(dao, directions);
        getTourType(dao, directions);
    }
    
    
    private static void getTransMode(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_TRANSMODE, dir.getTransMode().getIdMode());
            List<TransMode> mode = dao.showTransModes(crit);
            dir.setTransMode(mode.get(0));
        }
    }
    
    private static void getTourType(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_TOURTYPE, dir.getTourType().getIdTourType());
            List<TourType> type = dao.showTourTypes(crit);
            dir.setTourType(type.get(0));
        }
    }
    
    private static void getDescription(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DESCRIPTION, dir.getDescription().getIdDescription());
            List<Description> desc = dao.showDescriptions(crit);
            dir.setDescription(desc.get(0));
        }
    }
    
    private static void getStayHotelCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            List<DirectionStayHotel> stays = dao.showDirectionStayHotel(crit);
            dir.setStayCollection(getHotelInfo(dao, stays));
        }
    }
    
    public static List<DirectionStayHotel> getHotelInfo(AbstractDao dao, List<DirectionStayHotel> stays) throws DaoException {
        for (DirectionStayHotel st : stays) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_HOTEL, st.getHotel().getIdHotel());
            List<Hotel> hotels = dao.showHotels(crit);
            if (hotels != null && !hotels.isEmpty()) {
                st.setHotel(hotels.get(0));
                crit.addParam(DAO_ID_CITY, st.getHotel().getCity().getIdCity());
                List<City> cities = dao.showCities(crit);
                if (cities != null && !cities.isEmpty()) {
                    st.getHotel().setCity(cities.get(0));
                }
            }
        }
        return stays;
    }
    
    private static void getCityCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
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
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
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

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_DIRECTION_STATUS, DELETED);
        Integer res = updateDirection(criteria, dao);
        List<Direction> list = getEntity(criteria, dao);
        for (Direction d : list) {
            List<Tour> tours = (List<Tour>) d.getTourCollection();
            for (Tour t : tours) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_TOUR, t.getIdTour());
                new TourLogic().deleteEntity(crit, dao);
            }
        }
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        Integer res = updateDirection(criteria, dao);
        return res;
    }
    
}
