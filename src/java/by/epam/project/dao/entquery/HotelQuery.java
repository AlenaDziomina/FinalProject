/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import by.epam.project.dao.query.*;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.QueryExecutionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class HotelQuery implements TypedQuery<Hotel>{
    
    public static final String DB_HOTEL = "hotel";
    public static final String DB_HOTEL_ID_HOTEL = "id_hotel";
    public static final String DB_HOTEL_ID_CITY = "id_city";
    public static final String DB_HOTEL_NAME = "name";
    public static final String DB_HOTEL_STARS = "stars";
    public static final String DB_HOTEL_STATUS = "status";
    public static final String DB_HOTEL_PICTURE = "picture";
    public static final String DB_HOTEL_ID_DESCRIPTION = "id_description";
    
    public static final String DAO_ID_HOTEL = "idHotel";
    public static final String DAO_HOTEL_NAME = "nameHotel";
    public static final String DAO_HOTEL_STARS = "starsHotel";
    public static final String DAO_HOTEL_STATUS = "statusHotel";
    public static final String DAO_HOTEL_PICTURE = "pictureHotel";    
    public static final String DAO_HOTEL_LIST = "hotelList";

    private static final String SAVE_QUERY = 
            "Insert into " + DB_HOTEL + "(" + DB_HOTEL_ID_CITY + ", "
            + DB_HOTEL_NAME + ", " + DB_HOTEL_STARS + ", " 
            + DB_HOTEL_PICTURE + ", " + DB_HOTEL_ID_DESCRIPTION
            + ") values (?, ?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_HOTEL;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_HOTEL + " set ";
    
    
    public static final Hotel createBean(Criteria criteria){
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
    
    @Override
    public List<Integer> save(List<Hotel> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
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
            throw new QueryExecutionException("Hotel not saved.", ex);
        }
    }

    @Override
    public List<Hotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_HOTEL, DB_HOTEL_ID_HOTEL, criteria, paramList, sb, separator);
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, criteria, paramList, sb, separator);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, criteria, paramList, sb, separator);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, criteria, paramList, sb, separator);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, criteria, paramList, sb, separator);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, criteria, paramList, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, criteria, paramList, sb, separator);
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
             throw new QueryExecutionException("Hotel not loaded.", ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, criteria, paramList1, sb, separator);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, criteria, paramList1, sb, separator);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, criteria, paramList1, sb, separator);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, criteria, paramList1, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, criteria, paramList1, sb, separator);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_HOTEL, DB_HOTEL_ID_HOTEL, beans, paramList2, sb, separator);
                append(DAO_HOTEL_NAME, DB_HOTEL_NAME, beans, paramList2, sb, separator);
                append(DAO_HOTEL_STATUS, DB_HOTEL_STATUS, beans, paramList2, sb, separator);
                append(DAO_HOTEL_STARS, DB_HOTEL_STARS, beans, paramList2, sb, separator);
                append(DAO_HOTEL_PICTURE, DB_HOTEL_PICTURE, beans, paramList2, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_HOTEL_ID_DESCRIPTION, beans, paramList2, sb, separator);
                append(DAO_ID_CITY, DB_HOTEL_ID_CITY, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Hotel not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}