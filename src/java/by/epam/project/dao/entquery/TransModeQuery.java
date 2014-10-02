/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_MODE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_MODE;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.TransportationMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TransModeQuery implements TypedQuery<TransportationMode>{
    
    private static final String EM_SAVE_QUERY = 
            "Insert into transportation_mode (name_mode) values (?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from transportation_mode where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from transportation_mode;";
    private static final String EM_UPDATE_QUERY = 
            "Update transportation_mode set ";

    @Override
    public List<Integer> save(List<TransportationMode> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (TransportationMode bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameMode();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("",ex);
        }
    }
    
    @Override
    public List<TransportationMode> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_MODE, "id_mode", criteria, paramList, sb, separator);
                append(PARAM_NAME_NAME_MODE, "name_mode", criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                TransportationMode bean = new TransportationMode();
                bean.setIdMode(rs.getInt("id_mode"));
                bean.setNameMode(rs.getString("name_mode"));
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
                append(PARAM_NAME_NAME_MODE, "name_mode", criteria, paramList, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(PARAM_NAME_ID_MODE, "id_mode", beans, paramList2, sb, separator);
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
