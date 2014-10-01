/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import static by.epam.project.dao.MysqlDao.saveDao;
import by.epam.project.dao.entquery.CityQuery;
import by.epam.project.dao.entquery.CountryQuery;
import by.epam.project.dao.entquery.DescriptionQuery;
import by.epam.project.dao.entquery.DirectionCountryQuery;
import by.epam.project.dao.entquery.DirectionQuery;
import by.epam.project.dao.entquery.HotelQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.LinkDirectionCountryFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlAdminDao extends MysqlUserDao implements MysqlDao,  AdminDao {
    
    protected MysqlAdminDao() throws DaoException{}
    
    @Override
    public Integer toEditDescription(Criteria criteria) throws DaoException {
        Description desc = new Description(criteria);
        
        try {
            if (desc.getIdDescription() == null) {
                if (desc.getText() == null || desc.getText().isEmpty()) {
                    return null;
                } else {
                    List list = new ArrayList<>();
                    list.add(desc);
                    List<Integer> res = new DescriptionQuery().save(list, saveDao, mysqlConn);
                    if (res == null || res.isEmpty()) {
                        throw new DaoException("Error in description query.");
                    } else {
                        return res.get(0);
                    }
                }
            } else {
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_TEXT_DESCRIPTION, desc.getText());
                Criteria beans = new Criteria();
                beans.addParam(PARAM_NAME_ID_DESCRIPTION, desc.getIdDescription());
                new DescriptionQuery().update(beans, crit, updateDao, mysqlConn);
                return desc.getIdDescription();
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in description query.");
        }
        
    }
    
    @Override
    public Integer toCreateNewCountry(Criteria criteria)throws DaoException {

        try {
            Integer idDescription = toEditDescription(criteria);
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new Country(criteria));
            List<Integer> res = new CountryQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in country query.");
            } else {
                return res.get(0);
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
    }

    @Override
    public Integer toUpdateCountry(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            Integer idCountry = (Integer) criteria.getParam(PARAM_NAME_ID_COUNTRY);
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_NAME_COUNTRY, criteria.getParam(PARAM_NAME_NAME_COUNTRY));
            crit.addParam(PARAM_NAME_PICTURE_COUNTRY, criteria.getParam(PARAM_NAME_PICTURE_COUNTRY));
            crit.addParam(PARAM_NAME_STATUS_COUNTRY, criteria.getParam(PARAM_NAME_STATUS_COUNTRY));
            crit.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            Criteria beans = new Criteria();
            beans.addParam(PARAM_NAME_ID_COUNTRY, idCountry);
            new CountryQuery().update(beans, crit, updateDao, mysqlConn);
            return idCountry;
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public Integer toCreateNewCity(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new City(criteria));
            List<Integer> res = new CityQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in city query.");
            } else {
                return res.get(0);
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public Integer toUpdateCity(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            Integer idCity = (Integer) criteria.getParam(PARAM_NAME_ID_CITY);
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_NAME_CITY, criteria.getParam(PARAM_NAME_NAME_CITY));
            crit.addParam(PARAM_NAME_PICTURE_CITY, criteria.getParam(PARAM_NAME_PICTURE_CITY));
            crit.addParam(PARAM_NAME_STATUS_CITY, criteria.getParam(PARAM_NAME_STATUS_CITY));
            crit.addParam(PARAM_NAME_ID_COUNTRY, criteria.getParam(PARAM_NAME_ID_COUNTRY));
            crit.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            Criteria beans = new Criteria();
            beans.addParam(PARAM_NAME_ID_CITY, idCity);
            new CityQuery().update(beans, crit, updateDao, mysqlConn);
            return idCity;
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public Integer toCreateNewHotel(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new Hotel(criteria));
            List<Integer> res = new HotelQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in hotel query.");
            } else {
                return res.get(0);
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public Integer toUpdateHotel(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            Integer idHotel = (Integer) criteria.getParam(PARAM_NAME_ID_HOTEL);
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_NAME_HOTEL, criteria.getParam(PARAM_NAME_NAME_HOTEL));
            crit.addParam(PARAM_NAME_PICTURE_HOTEL, criteria.getParam(PARAM_NAME_PICTURE_HOTEL));
            crit.addParam(PARAM_NAME_STATUS_HOTEL, criteria.getParam(PARAM_NAME_STATUS_HOTEL));
            crit.addParam(PARAM_NAME_STARS_HOTEL, criteria.getParam(PARAM_NAME_STARS_HOTEL));
            crit.addParam(PARAM_NAME_ID_CITY, criteria.getParam(PARAM_NAME_ID_CITY));
            crit.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            Criteria beans = new Criteria();
            beans.addParam(PARAM_NAME_ID_HOTEL, idHotel);
            new HotelQuery().update(beans, crit, updateDao, mysqlConn);
            return idHotel;
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    @Override
    public Integer toCreateNewDirection(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new Direction(criteria));
            List<Integer> res = new DirectionQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in hotel query.");
            } else {
                Integer idDirection = res.get(0);
                criteria.addParam(PARAM_NAME_ID_DIRECTION, idDirection);
                
                List<LinkDirectionCountry> linkList1 = LinkDirectionCountryFactory.getInstances(criteria);
                List<Integer> res1 = new DirectionCountryQuery().save(linkList1, saveDao, mysqlConn);
                
                
                
                
                return idDirection;
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

    

}
