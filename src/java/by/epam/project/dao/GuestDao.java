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
public interface GuestDao extends AbstractDao {
    
    @Override
    public List<Role> showRoles(Criteria criteria) throws DaoException;
    @Override
    public List<User> showUsers(Criteria criteria) throws DaoException;
    @Override
    public Integer createNewUser(Criteria criteria) throws DaoException;   
    @Override
    public List<Country> toShowCountries(Criteria criteria) throws DaoException;
    @Override
    public List<City> toShowCities(Criteria criteria) throws DaoException;
    @Override
    public List<Hotel> toShowHotels(Criteria criteria) throws DaoException;
    @Override
    public List<TourType> toShowTourTypes (Criteria criteria) throws DaoException;
    @Override
    public List<TransportationMode> toShowTransModes (Criteria criteria) throws DaoException;
    @Override
    public List<Direction> toShowDirections(Criteria criteria) throws DaoException;
    @Override
    public List<LinkDirectionCountry> toShowLinkDirectionCountry(Criteria criteria) throws DaoException;
    @Override
    public List<LinkDirectionCity> toShowLinkDirectionCity(Criteria criteria) throws DaoException;
    @Override
    public List<DirectionStayHotel> toShowDirectionStayHotel(Criteria criteria) throws DaoException;
    @Override
    public List<Tour> toShowTours(Criteria criteria) throws DaoException;
    
}
