/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
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
import by.epam.project.exception.DaoAccessPermission;
import by.epam.project.exception.DaoException;
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
        throw new DaoAccessPermission("Method showRoles. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<User> showUsers(Criteria criteria) throws DaoException {
        throw new DaoAccessPermission("Method showUsers. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    } 
    default public Integer createNewUser(Criteria criteria) throws DaoException {
        throw new DaoAccessPermission("Method createNewUser. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }   
    default public List<Description> showDescriptions(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Country> showCountries(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showCountries. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<City> showCities(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showCities. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Hotel> showHotels(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Direction> showDirections(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showDirections. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<TourType> showTourTypes (Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showTourTypes. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<TransMode> showTransModes (Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showTransMode. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showLinkDirectionCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showLinkDirectionCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Tour> showTours(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showTours. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Tour> searchTours(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method searchTours. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Order> showOrders(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method showOrders. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    
    //user
    default public User changeOwnUser(Criteria bean, Criteria criteria) throws DaoException {
        throw new DaoAccessPermission("Method changeOwnUser. Current role: " 
                + bean.getParam(DAO_ROLE_NAME).toString());
    }
    
    //admin
    default public List<Integer> createNewDescription(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method createNewDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> updateDescription(Criteria beans, Criteria criteria) throws DaoException {
        throw new DaoAccessPermission("Method updateDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> createNewCountry(Criteria criteria)throws DaoException{
        throw new DaoAccessPermission("Method createNewCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> updateCountry(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> createNewCity(Criteria criteria)throws DaoException{
        throw new DaoAccessPermission("Method createNewCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> updateCity(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> createNewHotel(Criteria criteria)throws DaoException{
        throw new DaoAccessPermission("Method createNewHotel. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> updateHotel(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateHotel. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default public List<Integer> createNewDirection(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method createNewDirection. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException{
         throw new DaoAccessPermission("Method createNewDirectionCountryLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException{
         throw new DaoAccessPermission("Method createNewDirectionCityLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method createNewDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateDirection. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateDirectionCountryLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateDirectionCityLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> createNewTour(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method createNewTour. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> createNewOrder(Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method createNewOrder. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateTour. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default public List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessPermission("Method updateOrder. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
}
