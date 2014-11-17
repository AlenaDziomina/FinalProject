/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.entity;

import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public class CityQuery implements TypedQuery<City>{

    public static final String DB_CITY = "city";
    public static final String DB_CITY_ID_CITY = "id_city";
    public static final String DB_CITY_ID_COUNTRY = "id_country";
    public static final String DB_CITY_NAME = "name";
    public static final String DB_CITY_STATUS = "status";
    public static final String DB_CITY_PICTURE = "picture";
    public static final String DB_CITY_ID_DESCRIPTION = "id_description";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_CITY + " (" + DB_CITY_ID_COUNTRY + ", "
            + DB_CITY_NAME + ", " + DB_CITY_PICTURE + ", " 
            + DB_CITY_ID_DESCRIPTION + ") values (?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_CITY;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_CITY + " set ";
    
    @Override
    public List<Integer> save(List<City> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (City bean) -> {
                Object[] objects = new Object[4];
                objects[0] = bean.getCountry().getIdCountry();
                objects[1] = bean.getName();
                objects[2] = bean.getPicture();
                objects[3] = bean.getDescription().getIdDescription();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("City not saved.", ex);
        }
    }

    @Override
    public List<City> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_CITY, DB_CITY_ID_CITY, criteria, paramList, sb, separator);
                append(DAO_CITY_NAME, DB_CITY_NAME, criteria, paramList, sb, separator);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, criteria, paramList, sb, separator);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, criteria, paramList, sb, separator);
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, criteria, paramList, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, criteria, paramList, sb, separator);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = LOAD_QUERY;
        } else {
            queryStr = LOAD_QUERY + queryStr;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                City bean = new City();
                bean.setIdCity(rs.getInt(DB_CITY_ID_CITY));
                bean.setCountry(new Country(rs.getInt(DB_CITY_ID_COUNTRY)));
                bean.setName(rs.getString(DB_CITY_NAME));
                bean.setStatus(rs.getShort(DB_CITY_STATUS));
                bean.setPicture(rs.getString(DB_CITY_PICTURE));
                bean.setDescription(new Description(rs.getInt(DB_CITY_ID_DESCRIPTION)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("City not loaded.", ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, criteria, paramList1, sb, separator);
                append(DAO_CITY_NAME, DB_CITY_NAME, criteria, paramList1, sb, separator);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, criteria, paramList1, sb, separator);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, criteria, paramList1, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_CITY, DB_CITY_ID_CITY, beans, paramList2, sb, separator);
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, beans, paramList2, sb, separator);
                append(DAO_CITY_NAME, DB_CITY_NAME, beans, paramList2, sb, separator);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, beans, paramList2, sb, separator);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, beans, paramList2, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("City not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static City createBean(Criteria criteria){
        City bean = new City();
        bean.setIdCity((Integer) criteria.getParam(DAO_ID_CITY));
        bean.setName((String) criteria.getParam(DAO_CITY_NAME));
        bean.setPicture((String) criteria.getParam(DAO_CITY_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_CITY_STATUS));
        bean.setDescription(DescriptionQuery.createBean(criteria));
        bean.setHotelCollection((Collection<Hotel>) criteria.getParam(DAO_HOTEL_LIST));
        bean.setCountry(CountryQuery.createBean(criteria));
        return bean;
    }
}
