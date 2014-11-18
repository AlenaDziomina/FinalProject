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
    private static final String ERR_CITY_SAVE = "City not saved.";
    private static final String ERR_CITY_LOAD = "City not loaded.";
    private static final String ERR_CITY_UPDATE = "City not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_CITY = "city";
    private static final String DB_CITY_ID_CITY = "id_city";
    private static final String DB_CITY_ID_COUNTRY = "id_country";
    private static final String DB_CITY_NAME = "name";
    private static final String DB_CITY_STATUS = "status";
    private static final String DB_CITY_PICTURE = "picture";
    private static final String DB_CITY_ID_DESCRIPTION = "id_description";
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
            throw new DaoQueryException(ERR_CITY_SAVE, ex);
        }
    }

    @Override
    public List<City> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_CITY, DB_CITY_ID_CITY, criteria, paramList, sb, AND);
                append(DAO_CITY_NAME, DB_CITY_NAME, criteria, paramList, sb, AND);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, criteria, paramList, sb, AND);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, criteria, paramList, sb, AND);
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, criteria, paramList, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
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
             throw new DaoQueryException(ERR_CITY_LOAD, ex);
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
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, criteria, paramList1, sb, COMMA);
                append(DAO_CITY_NAME, DB_CITY_NAME, criteria, paramList1, sb, COMMA);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, criteria, paramList1, sb, COMMA);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, criteria, paramList1, sb, COMMA);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_CITY, DB_CITY_ID_CITY, beans, paramList2, sb, AND);
                append(DAO_ID_COUNTRY, DB_CITY_ID_COUNTRY, beans, paramList2, sb, AND);
                append(DAO_CITY_NAME, DB_CITY_NAME, beans, paramList2, sb, AND);
                append(DAO_CITY_STATUS, DB_CITY_STATUS, beans, paramList2, sb, AND);
                append(DAO_CITY_PICTURE, DB_CITY_PICTURE, beans, paramList2, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_CITY_ID_DESCRIPTION, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_CITY_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
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
