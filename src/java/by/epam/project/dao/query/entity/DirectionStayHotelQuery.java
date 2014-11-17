/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionStayHotelQuery implements TypedQuery<DirectionStayHotel>{
    
    public static final String DB_DIRSTAY = "direction_stay_hotels";
    public static final String DB_DIRSTAY_ID_STAY = "id_stay";
    public static final String DB_DIRSTAY_STAY_NO = "stay_no";
    public static final String DB_DIRSTAY_ID_HOTEL = "id_hotel";
    public static final String DB_DIRSTAY_ID_DIRECTION = "id_direction";
    public static final String DB_DIRSTAY_STATUS = "status";
    
    
           
    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRSTAY + " (" + DB_DIRSTAY_STAY_NO + ", "
            + DB_DIRSTAY_ID_DIRECTION + ", " + DB_DIRSTAY_ID_HOTEL 
            + ") values (?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRSTAY;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRSTAY + " set ";
    
    private static final String DELETE_QUERY = 
            "Delete from " + DB_DIRSTAY + " where ";

    @Override
    public List<Integer> save(List<DirectionStayHotel> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (DirectionStayHotel bean) -> {
                Object[] objects = new Object[3];
                objects[0] = bean.getStayNo();
                objects[1] = bean.getDirection().getIdDirection();
                objects[2] = bean.getHotel().getIdHotel();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Direction stay hotel not saved.", ex);
        }
    }

    @Override
    public List<DirectionStayHotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, criteria, paramList, sb, separator);
                append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList, sb, separator);
                append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList, sb, separator);
                append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList, sb, separator);
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
                DirectionStayHotel bean = new DirectionStayHotel();
                bean.setIdStay(rs.getInt(DB_DIRSTAY_ID_STAY));
                bean.setStayNo(rs.getInt(DB_DIRSTAY_STAY_NO));
                bean.setStatus(rs.getShort(DB_DIRSTAY_STATUS));
                bean.setDirection(new Direction(rs.getInt(DB_DIRSTAY_ID_DIRECTION)));
                bean.setHotel(new Hotel(rs.getInt(DB_DIRSTAY_ID_HOTEL)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction stay hotel not loaded.", ex);
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
                append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList1, sb, separator);
                append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList1, sb, separator);
                append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList1, sb, separator);
                append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, beans, paramList2, sb, separator);
                append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, beans, paramList2, sb, separator);
                append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, beans, paramList2, sb, separator);
                append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, beans, paramList2, sb, separator);
                append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction stay hotel not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DELETE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, criteria, paramList, sb, separator);
                append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList, sb, separator);
                append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList, sb, separator);
                append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        try {
            return deleteDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction stay hotel not deleted.", ex);
        }
        
    }
}


