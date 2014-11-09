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
import by.epam.project.exception.DaoAccessException;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public interface AbstractDao {
    
    //common
    void open() throws DaoException;   
    void close() throws DaoException;
    void rollback() throws DaoException;
    void commit() throws DaoException;
    
    //guest
    default List<Role> showRoles(Criteria criteria) throws DaoException {
        throw new DaoAccessException("Method showRoles. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<User> showUsers(Criteria criteria) throws DaoException {
        throw new DaoAccessException("Method showUsers. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    } 
    default Integer createNewUser(Criteria criteria) throws DaoException {
        throw new DaoAccessException("Method createNewUser. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }   
    default List<Description> showDescriptions(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Country> showCountries(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showCountries. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<City> showCities(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showCities. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Hotel> showHotels(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Direction> showDirections(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showDirections. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<TourType> showTourTypes (Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showTourTypes. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<TransMode> showTransModes (Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showTransMode. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showLinkDirectionCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showLinkDirectionCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Tour> showTours(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showTours. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Tour> searchTours(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method searchTours. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Order> showOrders(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method showOrders. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    
    //user
    default List<Integer> updateUser(Criteria bean, Criteria criteria) throws DaoException {
        throw new DaoAccessException("Method updateUser. Current role: " 
                + bean.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> createNewOrder(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewOrder. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateTour. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateOrder. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> createNewTourist(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewTourist. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    
    
    //admin
    default List<Integer> createNewDescription(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> updateDescription(Criteria beans, Criteria criteria) throws DaoException {
        throw new DaoAccessException("Method updateDescription. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> createNewCountry(Criteria criteria)throws DaoException{
        throw new DaoAccessException("Method createNewCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> updateCountry(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateCountry. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> createNewCity(Criteria criteria)throws DaoException{
        throw new DaoAccessException("Method createNewCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> updateCity(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateCity. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> createNewHotel(Criteria criteria)throws DaoException{
        throw new DaoAccessException("Method createNewHotel. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> updateHotel(Criteria bean, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateHotel. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    }
    default List<Integer> createNewDirection(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewDirection. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException{
         throw new DaoAccessException("Method createNewDirectionCountryLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException{
         throw new DaoAccessException("Method createNewDirectionCityLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateDirection. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateDirectionCountryLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateDirectionCityLinks. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateDirectionStayHotels. Current role: " 
                + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> createNewTour(Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method createNewTour. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
    default List<Integer> updateTourist(Criteria beans, Criteria criteria) throws DaoException{
        throw new DaoAccessException("Method updateTourist. Current role: " 
               + criteria.getParam(DAO_ROLE_NAME).toString());
    };
}
