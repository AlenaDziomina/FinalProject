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
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DescriptionQuery implements TypedQuery<Description>{

    private static final String EM_SAVE_QUERY = 
            "Insert into description(text) values (?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from description d where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from description;";
    private static final String EM_UPDATE_QUERY = 
            "Update description set ";
    
       
    @Override
    public List<Integer> save(List<Description> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (Description bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getText();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
    }
    
    @Override
    public List<Description> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10; 
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_ID_DESCRIPTION, "id_description", criteria, paramList, sb);
                append(PARAM_NAME_TEXT_DESCRIPTION, "text", criteria, paramList, sb);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Description bean = new Description();
                bean.setIdDescription(rs.getInt("id_description"));
                bean.setText(rs.getString("text"));
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
                append(PARAM_NAME_TEXT_DESCRIPTION, "text", criteria, paramList, sb);
                sb.append(" where ");
                append(PARAM_NAME_ID_DESCRIPTION, "id_description", beans, paramList2, sb);
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
