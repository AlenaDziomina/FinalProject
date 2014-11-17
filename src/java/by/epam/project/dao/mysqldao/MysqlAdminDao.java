/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AdminDao;
import by.epam.project.dao.query.entity.CityQuery;
import by.epam.project.dao.query.entity.CountryQuery;
import by.epam.project.dao.query.entity.DescriptionQuery;
import by.epam.project.dao.query.entity.DirectionCityQuery;
import by.epam.project.dao.query.entity.DirectionCountryQuery;
import by.epam.project.dao.query.entity.DirectionQuery;
import by.epam.project.dao.query.entity.DirectionStayHotelQuery;
import by.epam.project.dao.query.entity.HotelQuery;
import by.epam.project.dao.query.entity.SearchQuery;
import by.epam.project.dao.query.entity.TourQuery;
import by.epam.project.dao.query.entity.TouristQuery;
import static by.epam.project.dao.mysqldao.MysqlDao.saveDao;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.LinkDirectionFactory;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlAdminDao extends MysqlUserDao implements AdminDao {
    
    protected MysqlAdminDao(){}
    
    @Override
    public List<Country> showCountries(Criteria criteria) throws DaoException {
        return new CountryQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<City> showCities(Criteria criteria) throws DaoException {
        return new CityQuery().load(criteria, loadDao, mysqlConn);
    }

    @Override
    public List<Hotel> showHotels(Criteria criteria) throws DaoException {
        return new HotelQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        return new DirectionQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        return new TourQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Tour> searchTours(Criteria criteria) throws DaoException {
        return new SearchQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Integer> createNewDescription(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(DescriptionQuery.createBean(criteria));
        return new DescriptionQuery().save(list, saveDao, mysqlConn);
    }
    @Override
    public List<Integer> updateDescription(Criteria beans, Criteria crit) throws DaoException {
        return new DescriptionQuery().update(beans, crit, updateDao, mysqlConn);
    }
    
    @Override
    public List createNewCountry(Criteria criteria)throws DaoException {
        List list = new ArrayList<>();
        list.add(CountryQuery.createBean(criteria));
        return new CountryQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateCountry(Criteria beans, Criteria crit) throws DaoException {
        return new CountryQuery().update(beans, crit, updateDao, mysqlConn);
    }

    @Override
    public List createNewCity(Criteria criteria) throws DaoException {
            List list = new ArrayList<>();
            list.add(CityQuery.createBean(criteria));
            return new CityQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateCity(Criteria beans, Criteria crit) throws DaoException {
        return new CityQuery().update(beans, crit, updateDao, mysqlConn);
    }

    @Override
    public List createNewHotel(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(HotelQuery.createBean(criteria));
        return new HotelQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateHotel(Criteria beans, Criteria crit) throws DaoException {
        return new HotelQuery().update(beans, crit, updateDao, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirection(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(DirectionQuery.createBean(criteria));
        return new DirectionQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException {
        List<LinkDirectionCountry> linkList = LinkDirectionFactory.getLinkCountryInstances(criteria);
        return new DirectionCountryQuery().save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException {
        List<LinkDirectionCity> linkList = LinkDirectionFactory.getLinkCityInstances(criteria);
        return new DirectionCityQuery().save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException {
        List<DirectionStayHotel> linkList = LinkDirectionFactory.getStayHotelInstances(criteria);
        return new DirectionStayHotelQuery().save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException {
        return new DirectionQuery().update(beans, criteria, updateDao, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException {
        DirectionCountryQuery qu = new DirectionCountryQuery();
        qu.delete(beans, deleteDao, mysqlConn);
        List<LinkDirectionCountry> linkList = LinkDirectionFactory.getLinkCountryInstances(criteria);
        return qu.save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException {
        DirectionCityQuery qu = new DirectionCityQuery();
        qu.delete(beans, deleteDao, mysqlConn);
        List<LinkDirectionCity> linkList = LinkDirectionFactory.getLinkCityInstances(criteria);
        return qu.save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException {
        DirectionStayHotelQuery qu = new DirectionStayHotelQuery();
        qu.delete(beans, deleteDao, mysqlConn);
        List<DirectionStayHotel> linkList = LinkDirectionFactory.getStayHotelInstances(criteria);
        return qu.save(linkList, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> createNewTour(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(TourQuery.createBean(criteria));
        return new TourQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateTourist(Criteria beans, Criteria criteria) throws DaoException {
        return new TouristQuery().update(beans, criteria, updateDao, mysqlConn);
    }

}
