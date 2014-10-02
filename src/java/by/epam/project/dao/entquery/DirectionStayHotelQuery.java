/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_HOTEL;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_STAY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PICTURE_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STATUS_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STATUS_STAY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STAY_NO;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionStayHotelQuery implements TypedQuery<DirectionStayHotel>{
    
   
    private static final String EM_SAVE_QUERY = 
            "Insert into direction_stay_hotels(stay_no, id_direction, id_hotel) values (?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from direction_stay_hotels s where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from direction_stay_hotels s ;";
    private static final String EM_UPDATE_QUERY = 
            "Update direction_stay_hotels set ";

    @Override
    public List<Integer> save(List<DirectionStayHotel> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (DirectionStayHotel bean) -> {
                Object[] objects = new Object[3];
                objects[0] = bean.getStayNo();
                objects[1] = bean.getIdDirection();
                objects[2] = bean.getStayHotel().getIdHotel();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public List<DirectionStayHotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_COUNTRY, "id_country", criteria, paramList, sb, separator);
                append(PARAM_NAME_NAME_COUNTRY, "name", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_COUNTRY, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_PICTURE_COUNTRY, "picture", criteria, paramList, sb, separator);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                DirectionStayHotel bean = new DirectionStayHotel();
                bean.setIdStay(rs.getInt("id_stay"));
                bean.setStayNo(rs.getInt("stay_no"));
                bean.setStatus(rs.getShort("status"));
                bean.setIdDirection(rs.getInt("id_direction"));
                bean.setStayHotel(new Hotel(rs.getInt("id_hotel")));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        List paramList = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(PARAM_NAME_STAY_NO, "stay_no", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_STAY, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_HOTEL, "id_hotel", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_DIRECTION, "id_direction", criteria, paramList, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(PARAM_NAME_ID_STAY, "id_stay", beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("",ex);
        }
    }
    
    
}

