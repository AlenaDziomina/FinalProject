/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CityQuery implements TypedSaveQuery<City>, TypedLoadQuery<City>, TypedUpdateQuery<City>{

    private static final GenericSaveQuery saveDao = new MysqlGenericSaveQuery();
    private static final GenericLoadQuery loadDao = new MysqlGenericLoadQuery();
    private static final GenericUpdateQuery updateDao = new MysqlGenericUpdateQuery();
    
    private static final String EM_SAVE_QUERY = 
            "Insert into city(id_country, login, password, email, phone, discount, balance, lang) values (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from city c left join description d on (c.id_description = d.id_description) where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from city c left join description d on (c.id_description = d.id_description);";
    private static final String EM_UPDATE_QUERY = 
            "Update city set ";
    
    @Override
    public void save(List<City> beans) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<City> load(Criteria criteria) throws QueryExecutionException {
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
            return loadDao.query(queryStr, paramList.toArray(), pageSize, new RowMapper<City>() {
                @Override
                public City mapRow(ResultSet rs, int rowNum) throws SQLException {
                    City bean = new City();
                    bean.setIdCity(rs.getInt("id_city"));
                    bean.setName(rs.getString("name"));
                    bean.setStatus(rs.getShort("status"));
                    bean.setPicture(rs.getString("picture"));
                    bean.setDescription(new Description(rs.getInt("id_description"), rs.getString("text")));
                    
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }
    }

    @Override
    public int update(Criteria beans, Criteria criteria) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
