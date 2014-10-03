/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import by.epam.project.dao.query.*;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.entity.Description;
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
public class DescriptionQuery implements TypedQuery<Description>{
    
    public static final String DB_DESCRIPTION = "description";
    public static final String DB_DESCRIPTION_ID_DESCRIPTION = "id_description";
    public static final String DB_DESCRIPTION_TEXT = "text";
    
    public static final String DAO_ID_DESCRIPTION = "idDescription";
    public static final String DAO_DESCRIPTION_TEXT = "textDescription";

    private static final String SAVE_QUERY = 
            "Insert into " + DB_DESCRIPTION + "(" + DB_DESCRIPTION_TEXT
            + ") values (?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DESCRIPTION;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_DESCRIPTION + " set ";
    
    
    public static final Description createBean(Criteria criteria){
        Description bean = new Description();
        bean.setIdDescription((Integer) criteria.getParam(DAO_ID_DESCRIPTION));
        bean.setText((String) criteria.getParam(DAO_DESCRIPTION_TEXT));
        return bean;
    }
       
    @Override
    public List<Integer> save(List<Description> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Description bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getText();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("Description not saved.", ex);
        }
    }
    
    @Override
    public List<Description> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10; 
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DESCRIPTION, DB_DESCRIPTION_ID_DESCRIPTION, criteria, paramList, sb, separator);
                append(DAO_DESCRIPTION_TEXT, DB_DESCRIPTION_TEXT, criteria, paramList, sb, separator);
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
                Description bean = new Description();
                bean.setIdDescription(rs.getInt(DB_DESCRIPTION_ID_DESCRIPTION));
                bean.setText(rs.getString(DB_DESCRIPTION_TEXT));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("Decription not loaded.", ex);
        }
    }

   

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_DESCRIPTION_TEXT, DB_DESCRIPTION_TEXT, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_DESCRIPTION, DB_DESCRIPTION_ID_DESCRIPTION, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Description not updated.", ex);
        }
    }
    
}
