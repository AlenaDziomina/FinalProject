/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_TOUR_TYPE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_TOUR_TYPE;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Direction;
import by.epam.project.entity.TourType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TourTypeQuery implements TypedQuery<TourType>{
    
    public static final String DB_TOURTYPE = "tour_type";
    public static final String DB_TOURTYPE_ID_TOURTYPE = "id_tour_type";
    public static final String DB_TOURTYPE_NAME = "name_tour_type";
    
    public static final String DAO_ID_TOURTYPE = "idTourType";
    public static final String DAO_TOURTYPE_NAME = "nameTourType";

    private static final String SAVE_QUERY = 
            "Insert into " + DB_TOURTYPE + "(" + DB_TOURTYPE_NAME
            + ") values (?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TOURTYPE;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_TOURTYPE + " set ";

    
    public static final TourType createBean(Criteria criteria) {
        TourType bean = new TourType();
        bean.setIdTourType((Integer)criteria.getParam(DAO_ID_TOURTYPE));
        bean.setNameTourType((String)criteria.getParam(DAO_TOURTYPE_NAME));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<TourType> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (TourType bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameTourType();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("Tour type not saved.", ex);
        }
    }

    @Override
    public List<TourType> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_TOURTYPE, DB_TOURTYPE_ID_TOURTYPE, criteria, paramList, sb, separator);
                append(DAO_TOURTYPE_NAME, DB_TOURTYPE_NAME, criteria, paramList, sb, separator);
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
                TourType bean = new TourType();
                bean.setIdTourType(rs.getInt(DB_TOURTYPE_ID_TOURTYPE));
                bean.setNameTourType(rs.getString(DB_TOURTYPE_NAME));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("Tour type not loaded.", ex);
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
                append(DAO_TOURTYPE_NAME, DB_TOURTYPE_NAME, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_TOURTYPE, DB_TOURTYPE_ID_TOURTYPE, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Tour type not updated.", ex);
        }
    }

    
    
}

