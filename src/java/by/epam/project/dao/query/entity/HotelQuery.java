package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of hotel query forming.
 * @author Helena.Grouk
 */
public class HotelQuery implements TypedQuery<Hotel>{
    private static final String ERR_HOTEL_SAVE = "Hotel not saved.";
    private static final String ERR_HOTEL_LOAD = "Hotel not loaded.";
    private static final String ERR_HOTEL_UPDATE = "Hotel not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_HOTEL = "hotel";
    private static final String DB_HOTEL_ID_HOTEL = "id_hotel";
    private static final String DB_HOTEL_ID_CITY = "id_city";
    private static final String DB_HOTEL_NAME = "name";
    private static final String DB_HOTEL_STARS = "stars";
    private static final String DB_HOTEL_STATUS = "status";
    private static final String DB_HOTEL_PICTURE = "picture";
    private static final String DB_HOTEL_ID_DESCRIPTION = "id_description";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_HOTEL + " (" + DB_HOTEL_ID_CITY + ", "
            + DB_HOTEL_NAME + ", " + DB_HOTEL_STARS + ", " 
            + DB_HOTEL_PICTURE + ", " + DB_HOTEL_ID_DESCRIPTION
            + ") values (?, ?, ?, ?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_HOTEL;
    private static final String UPDATE_QUERY = 
            "Update " + DB_HOTEL + " set ";
    
    @Override
    public List<Integer> save(List<Hotel> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Hotel bean) -> {
                Object[] objects = new Object[5];
                objects[0] = bean.getCity().getIdCity();
                objects[1] = bean.getName();
                objects[2] = bean.getStars();
                objects[3] = bean.getPicture();
                objects[4] = bean.getDescription().getIdDescription();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_HOTEL_SAVE, ex);
        }
    }

    @Override
    public List<Hotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_HOTEL, DB_HOTEL_ID_HOTEL, criteria, paramList, sb, AND);
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, criteria, paramList, sb, AND);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, criteria, paramList, sb, AND);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, criteria, paramList, sb, AND);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, criteria, paramList, sb, AND);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, criteria, paramList, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Hotel bean = new Hotel();
                bean.setIdHotel(rs.getInt(DB_HOTEL_ID_HOTEL));
                bean.setName(rs.getString(DB_HOTEL_NAME));
                bean.setStatus(rs.getShort(DB_HOTEL_STATUS));
                bean.setStars(rs.getInt(DB_HOTEL_STARS));
                bean.setPicture(rs.getString(DB_HOTEL_PICTURE));
                bean.setDescription(new Description(rs.getInt(DB_HOTEL_ID_DESCRIPTION)));
                bean.setCity(new City(rs.getInt(DB_HOTEL_ID_CITY)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_HOTEL_LOAD, ex);
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
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, criteria, paramList1, sb, COMMA);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, criteria, paramList1, sb, COMMA);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, criteria, paramList1, sb, COMMA);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, criteria, paramList1, sb, COMMA);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, criteria, paramList1, sb, COMMA);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, criteria, paramList1, sb, COMMA);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_HOTEL, DB_HOTEL_ID_HOTEL, beans, paramList2, sb, AND);
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, beans, paramList2, sb, AND);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, beans, paramList2, sb, AND);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, beans, paramList2, sb, AND);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, beans, paramList2, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, beans, paramList2, sb, AND);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_HOTEL_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    public static Hotel createBean(Criteria criteria){
        Hotel bean = new Hotel();
        bean.setIdHotel((Integer) criteria.getParam(DAO_ID_HOTEL));
        bean.setName((String) criteria.getParam(DAO_HOTEL_NAME));
        bean.setPicture((String) criteria.getParam(DAO_HOTEL_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_HOTEL_STATUS));
        bean.setStars((Integer) criteria.getParam(DAO_HOTEL_STARS));
        bean.setDescription(DescriptionQuery.createBean(criteria));
        bean.setCity(CityQuery.createBean(criteria));
        return bean;
    }
}