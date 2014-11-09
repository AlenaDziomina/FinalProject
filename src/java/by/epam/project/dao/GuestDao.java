/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
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
import java.util.List;

/**
 *
 * @author User
 */
public interface GuestDao extends AbstractDao {
    
    @Override
    List<Role> showRoles(Criteria criteria) throws DaoException;
    @Override
    List<User> showUsers(Criteria criteria) throws DaoException;
    @Override
    Integer createNewUser(Criteria criteria) throws DaoException;   
    @Override
    List<Direction> showDirections(Criteria criteria) throws DaoException;
    @Override
    List<Country> showCountries(Criteria criteria) throws DaoException;
    @Override
    List<City> showCities(Criteria criteria) throws DaoException;
    @Override
    List<Hotel> showHotels(Criteria criteria) throws DaoException;
    @Override
    List<TourType> showTourTypes (Criteria criteria) throws DaoException;
    @Override
    List<TransMode> showTransModes (Criteria criteria) throws DaoException;
    @Override
    List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException;
    @Override
    List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException;
    @Override
    List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException;
    @Override
    List<Tour> showTours(Criteria criteria) throws DaoException;
    @Override
    List<Tour> searchTours(Criteria criteria) throws DaoException;
    
    
}
