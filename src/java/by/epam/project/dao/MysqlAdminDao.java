/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import static by.epam.project.dao.MysqlDao.saveDao;
import by.epam.project.dao.entquery.CityQuery;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_NAME;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_PICTURE;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_STATUS;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import by.epam.project.dao.entquery.CountryQuery;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_NAME;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_PICTURE;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_STATUS;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import by.epam.project.dao.entquery.DescriptionQuery;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import by.epam.project.dao.entquery.DirectionCityQuery;
import by.epam.project.dao.entquery.DirectionCountryQuery;
import by.epam.project.dao.entquery.DirectionQuery;
import by.epam.project.dao.entquery.DirectionStayHotelQuery;
import by.epam.project.dao.entquery.HotelQuery;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_NAME;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_PICTURE;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STARS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STATUS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.LinkDirectionFactory;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.QueryExecutionException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlAdminDao extends MysqlUserDao implements AdminDao {
    
    protected MysqlAdminDao(){}
    
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
        try {
            Integer idDescription = createNewDescription(criteria).get(0);
            criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new Direction(criteria));
            List<Integer> res = new DirectionQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in hotel query.");
            } else {
                Integer idDirection = res.get(0);
                criteria.addParam(PARAM_NAME_ID_DIRECTION, idDirection);
                
                List<LinkDirectionCountry> linkList1 = LinkDirectionFactory.getLinkCountryInstances(criteria);
                List<Integer> res1 = new DirectionCountryQuery().save(linkList1, saveDao, mysqlConn);
                
                List<LinkDirectionCity> linkList2 = LinkDirectionFactory.getLinkCityInstances(criteria);
                List<Integer> res2 = new DirectionCityQuery().save(linkList2, saveDao, mysqlConn);
                
                List<DirectionStayHotel> linkList3 = LinkDirectionFactory.getStayHotelInstances(criteria);
                List<Integer> res3 = new DirectionStayHotelQuery().save(linkList3, saveDao, mysqlConn);
                
                return res;
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    

}
