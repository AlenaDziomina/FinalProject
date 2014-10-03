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
    default public List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException{
         throw new DaoException("error access");
    };
    default public List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException{
         throw new DaoException("error access");
    };
    default public List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    default public List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    default public List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    default public List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
    default public List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoException("error access");
    };
}
