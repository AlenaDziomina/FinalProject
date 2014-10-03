/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
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
    
    public static final String DB_TRANSMODE = "transportation_mode";
    public static final String DB_TRANSMODE_ID_MODE = "id_mode";
    public static final String DB_TRANSMODE_NAME = "name_mode";
    
    public static final String DAO_ID_TRANSMODE = "idMode";
    public static final String DAO_TRANSMODE_NAME = "nameMode";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TRANSMODE + "("
            + DB_TRANSMODE_NAME + ") values (?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TRANSMODE;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_TRANSMODE + " set ";

    public static final TransportationMode createBean(Criteria criteria) {
        TransportationMode bean = new TransportationMode();
        bean.setIdMode((Integer)criteria.getParam(DAO_ID_TRANSMODE));
        bean.setNameMode((String)criteria.getParam(DAO_TRANSMODE_NAME));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<TransportationMode> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (TransportationMode bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameMode();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("Transportation mode not saved.", ex);
        }
    }
    
    @Override
    public List<TransportationMode> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_TRANSMODE, DB_TRANSMODE_ID_MODE, criteria, paramList, sb, separator);
                append(DAO_TRANSMODE_NAME, DB_TRANSMODE_NAME, criteria, paramList, sb, separator);
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
                TransportationMode bean = new TransportationMode();
                bean.setIdMode(rs.getInt(DB_TRANSMODE_ID_MODE));
                bean.setNameMode(rs.getString(DB_TRANSMODE_NAME));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("Transportation mode not loaded.", ex);
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
                append(DAO_TRANSMODE_NAME, DB_TRANSMODE_NAME, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_TRANSMODE, DB_TRANSMODE_ID_MODE, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Transportation mode not updated." ,ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
