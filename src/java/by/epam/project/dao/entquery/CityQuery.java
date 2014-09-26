/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CityQuery implements TypedQuery<City>{

    private static final String EM_SAVE_QUERY = 
            "Insert into city(id_country, name, picture, id_description) values (?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from city c left join description d on (c.id_description = d.id_description) where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from city c left join description d on (c.id_description = d.id_description);";
    private static final String EM_UPDATE_QUERY = 
            "Update city set ";
    
    @Override
    public List<Integer> save(List<City> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (City bean) -> {
                Object[] objects = new Object[4];
                objects[0] = bean.getIdCountry();
                objects[1] = bean.getName();
                objects[2] = bean.getPicture();
                objects[3] = bean.getDescription().getIdDescription();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
    }

    @Override
    public List<City> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_ID_CITY, "id_city", criteria, paramList, sb);
                append(PARAM_NAME_NAME_CITY, "name", criteria, paramList, sb);
                append(PARAM_NAME_STATUS_CITY, "status", criteria, paramList, sb);
                append(PARAM_NAME_PICTURE_CITY, "picture", criteria, paramList, sb);
                append(PARAM_NAME_ID_COUNTRY, "id_country", criteria, paramList, sb);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                City bean = new City();
                bean.setIdCity(rs.getInt("id_city"));
                bean.setName(rs.getString("name"));
                bean.setStatus(rs.getShort("status"));
                bean.setPicture(rs.getString("picture"));
                bean.setDescription(new Description(rs.getInt("id_description"), rs.getString("text")));
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
                append(PARAM_NAME_ID_COUNTRY, "id_country", beans, paramList2, sb);
                append(PARAM_NAME_NAME_CITY, "name", criteria, paramList, sb);
                append(PARAM_NAME_STATUS_CITY, "status", criteria, paramList, sb);
                append(PARAM_NAME_PICTURE_CITY, "picture", criteria, paramList, sb);
                append(PARAM_NAME_ID_DESCRIPTION, "id_description", criteria, paramList, sb);
                sb.append(" where ");
                append(PARAM_NAME_ID_CITY, "id_city", beans, paramList2, sb);
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
