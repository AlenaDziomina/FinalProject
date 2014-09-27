/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class HotelQuery implements TypedQuery<Hotel>{

    private static final String EM_SAVE_QUERY = 
            "Insert into hotel(id_city, name, stars, picture, id_description) values (?, ?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from hotel h left join description d on (h.id_description = d.id_description) where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from hotel h left join description d on (h.id_description = d.id_description);";
    private static final String EM_UPDATE_QUERY = 
            "Update hotel set ";
    
    @Override
    public List<Integer> save(List<Hotel> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (Hotel bean) -> {
                Object[] objects = new Object[3];
                objects[0] = bean.getIdCity();
                objects[1] = bean.getName();
                objects[2] = bean.getStars();
                objects[3] = bean.getPicture();
                objects[4] = bean.getDescription().getIdDescription();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
    }

    @Override
    public List<Hotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_HOTEL, "id_hotel", criteria, paramList, sb, separator);
                append(PARAM_NAME_NAME_HOTEL, "name", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_HOTEL, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_PICTURE_HOTEL, "picture", criteria, paramList, sb, separator);
                append(PARAM_NAME_STARS_HOTEL, "stars", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_CITY, "id_city", criteria, paramList, sb, separator);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Hotel bean = new Hotel();
                bean.setIdHotel(rs.getInt("id_hotel"));
                bean.setName(rs.getString("name"));
                bean.setStatus(rs.getShort("status"));
                bean.setStars(rs.getInt("stars"));
                bean.setPicture(rs.getString("picture"));
                bean.setDescription(new Description(rs.getInt("id_description"), rs.getString("text")));
                bean.setIdCity(rs.getInt("id_country"));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
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
                append(PARAM_NAME_NAME_HOTEL, "name", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_HOTEL, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_STARS_HOTEL, "stars", criteria, paramList, sb, separator);
                append(PARAM_NAME_PICTURE_HOTEL, "picture", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_DESCRIPTION, "id_description", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_CITY, "id_city", criteria, paramList, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(PARAM_NAME_ID_HOTEL, "id_hotel", beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }
    }
    
    
}