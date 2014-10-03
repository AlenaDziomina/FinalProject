/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
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
import by.epam.project.entity.TransportationMode;
import by.epam.project.entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface AbstractDao {
    
    static final String PARAM_NAME_ID_DIRECTION = "id_direction";
    static final String PARAM_NAME_NAME_DIRECTION = "name_direction";
    static final String PARAM_NAME_PICTURE_DIRECTION = "picture_direction";
    static final String PARAM_NAME_STATUS_DIRECTION = "status_direction";
    static final String PARAM_NAME_TEXT_DIRECTION = "text_direction";
    
    static final String PARAM_NAME_ID_TOUR_TYPE = "id_tour_type";
    static final String PARAM_NAME_NAME_TOUR_TYPE = "name_tour_type";
    static final String PARAM_NAME_ID_MODE = "id_mode";
    static final String PARAM_NAME_NAME_MODE = "name_mode";
    
    static final String PARAM_NAME_ID_STAY = "id_stay";
    static final String PARAM_NAME_STAY_NO = "stay_no";
    static final String PARAM_NAME_STATUS_STAY = "status_stay";
    
    static final String PARAM_NAME_ID_TOUR = "id_tour";
    static final String PARAM_NAME_DEPARTURE_DATE = "departure_date";
    static final String PARAM_NAME_DAYS_COUNT = "days_count";
    static final String PARAM_NAME_PRICE_TOUR = "price";
    static final String PARAM_NAME_DISCOUNT_TOUR = "discount";
    static final String PARAM_NAME_TOTAL_SEATS = "total_seats";
    static final String PARAM_NAME_FREE_SEATS = "free_seats";
    
    
    //common
    public abstract void open() throws DaoException;   
    public abstract void close() throws DaoException;
    public abstract void rollback() throws DaoException;
    
    //guest
    default public List<Role> showRoles(Criteria criteria) throws DaoException {
        throw new DaoException("Method registrate.");
    }
    default public List<User> showUsers(Criteria criteria) throws DaoException {
        throw new DaoException("Method registrate.");
    } 
    default public Integer createNewUser(Criteria criteria) throws DaoException {
        throw new DaoException("Method registrate.");
    }   
    default public List<Description> showDescriptions(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Country> showCountries(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<City> showCities(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Hotel> showHotels(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Direction> showDirections(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<TourType> showTourTypes (Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<TransportationMode> showTransModes (Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    
    default public List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Tour> showTours(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    
    //user
    default public User changeOwnUser(Criteria bean, Criteria criteria) throws DaoException {
        throw new DaoException("error access");
    }
    
    //admin
    default public List<Integer> createNewDescription(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> updateDescription(Criteria beans, Criteria crit) throws DaoException {
        throw new DaoException("error access");
    }
    default public List<Integer> createNewCountry(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> updateCountry(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> createNewCity(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> updateCity(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> createNewHotel(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> updateHotel(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Integer> createNewDirection(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    
}
