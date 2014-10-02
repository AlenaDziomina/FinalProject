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
    
    
    static final String PARAM_NAME_ID_COUNTRY = "id_country";
    static final String PARAM_NAME_NAME_COUNTRY = "name_country";
    static final String PARAM_NAME_STATUS_COUNTRY = "status_country";
    static final String PARAM_NAME_PICTURE_COUNTRY = "picture_country";
    static final String PARAM_NAME_ID_DESCRIPTION = "id_description";
    static final String PARAM_NAME_TEXT_DESCRIPTION = "text_description";
    static final String PARAM_NAME_ID_CITY = "id_city";
    static final String PARAM_NAME_NAME_CITY = "name_city";
    static final String PARAM_NAME_STATUS_CITY = "status_city";
    static final String PARAM_NAME_PICTURE_CITY = "picture_city";
    static final String PARAM_NAME_ID_HOTEL = "id_hotel";
    static final String PARAM_NAME_NAME_HOTEL = "name_hotel";
    static final String PARAM_NAME_STATUS_HOTEL = "status_hotel";
    static final String PARAM_NAME_STARS_HOTEL = "stars_hotel";
    static final String PARAM_NAME_PICTURE_HOTEL = "picture_hotel";
    
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
    
    default public List<Country> toShowCountries(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<City> toShowCities(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Hotel> toShowHotels(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<TourType> toShowTourTypes (Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<TransportationMode> toShowTransModes (Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Direction> toShowDirections(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<LinkDirectionCountry> toShowLinkDirectionCountry(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<LinkDirectionCity> toShowLinkDirectionCity(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<DirectionStayHotel> toShowDirectionStayHotel(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public List<Tour> toShowTours(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    
    //user
    default public User toChangeOwnUser(Criteria bean, Criteria criteria) throws DaoException {
        throw new DaoException("error access");
    }
    
    //admin
    default public Integer toCreateNewCountry(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toUpdateCountry(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toCreateNewCity(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toUpdateCity(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toCreateNewHotel(Criteria criteria)throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toUpdateHotel(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toEditDescription(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    }
    default public Integer toCreateNewDirection(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    
}
