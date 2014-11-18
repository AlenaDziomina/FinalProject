package by.epam.project.dao;

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
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 * Common interface of DAO for users wirh role GUEST
 * @author Helena.Grouk
 */
public interface GuestDao extends AbstractDao {

    @Override
    List<Role> findRoles(Criteria criteria) throws DaoException;
    @Override
    List<User> findUsers(Criteria criteria) throws DaoException;
    @Override
    Integer createNewUser(Criteria criteria) throws DaoException;
    @Override
    List<Direction> findDirections(Criteria criteria) throws DaoException;
    @Override
    List<Country> findCountries(Criteria criteria) throws DaoException;
    @Override
    List<City> findCities(Criteria criteria) throws DaoException;
    @Override
    List<Hotel> findHotels(Criteria criteria) throws DaoException;
    @Override
    List<TourType> findTourTypes (Criteria criteria) throws DaoException;
    @Override
    List<TransMode> findTransModes (Criteria criteria) throws DaoException;
    @Override
    List<LinkDirectionCountry> findLinkDirectionCountry(Criteria criteria) throws DaoException;
    @Override
    List<LinkDirectionCity> findLinkDirectionCity(Criteria criteria) throws DaoException;
    @Override
    List<DirectionStayHotel> findDirectionStayHotel(Criteria criteria) throws DaoException;
    @Override
    List<Tour> findTours(Criteria criteria) throws DaoException;
    @Override
    List<Tour> searchTours(Criteria criteria) throws DaoException;


}
