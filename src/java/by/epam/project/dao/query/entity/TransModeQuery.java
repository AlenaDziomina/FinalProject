/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.entity;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.TransMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TransModeQuery implements TypedQuery<TransMode>{
    
    public static final String DB_TRANSMODE = "transportation_mode";
    public static final String DB_TRANSMODE_ID_MODE = "id_mode";
    public static final String DB_TRANSMODE_NAME = "name_mode";
    
    
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TRANSMODE + " ("
            + DB_TRANSMODE_NAME + ") values (?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TRANSMODE;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_TRANSMODE + " set ";

    public static TransMode createBean(Criteria criteria) {
        TransMode bean = new TransMode();
        bean.setIdMode((Integer)criteria.getParam(DAO_ID_TRANSMODE));
        bean.setNameMode((String)criteria.getParam(DAO_TRANSMODE_NAME));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<TransMode> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (TransMode bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameMode();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Transportation mode not saved.", ex);
        }
    }
    
    @Override
    public List<TransMode> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
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
                TransMode bean = new TransMode();
                bean.setIdMode(rs.getInt(DB_TRANSMODE_ID_MODE));
                bean.setNameMode(rs.getString(DB_TRANSMODE_NAME));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Transportation mode not loaded.", ex);
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
             throw new DaoQueryException("Transportation mode not updated." ,ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
