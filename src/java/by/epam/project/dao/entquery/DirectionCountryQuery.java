/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionCountryQuery implements TypedQuery<LinkDirectionCountry>{

    public static final String DB_DIRCOUNTRY = "direction_countries";
    public static final String DB_DIRCOUNTRY_ID_COUNTRY = "id_country";
    public static final String DB_DIRCOUNTRY_ID_DIRECTION = "id_direction";

    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRCOUNTRY + "(" + DB_DIRCOUNTRY_ID_DIRECTION + ", "
            + DB_DIRCOUNTRY_ID_COUNTRY + ") values (?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRCOUNTRY;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRCOUNTRY + " set ";
    
    private static final String DELETE_QUERY = 
            "Delete from " + DB_DIRCOUNTRY + " where ";

    @Override
    public List<Integer> save(List<LinkDirectionCountry> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (LinkDirectionCountry bean) -> {
                Object[] objects = new Object[2];
                objects[0] = bean.getIdDirection();
                objects[1] = bean.getIdCountry();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Direction link to country not saved.", ex);
        }
    }

    @Override
    public List<LinkDirectionCountry> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DIRECTION, DB_DIRCOUNTRY_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_ID_COUNTRY, DB_DIRCOUNTRY_ID_COUNTRY, criteria, paramList, sb, separator);
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
                LinkDirectionCountry bean = new LinkDirectionCountry();
                bean.setIdCountry(rs.getInt(DB_DIRCOUNTRY_ID_COUNTRY));
                bean.setIdDirection(rs.getInt(DB_DIRCOUNTRY_ID_DIRECTION));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction link to country not loaded.", ex);
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
                append(DAO_ID_COUNTRY, DB_DIRCOUNTRY_ID_COUNTRY, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_DIRECTION, DB_DIRCOUNTRY_ID_DIRECTION, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction link to country not updated.",ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DELETE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DIRECTION, DB_DIRCOUNTRY_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_ID_COUNTRY, DB_DIRCOUNTRY_ID_COUNTRY, criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        try {
            return deleteDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Direction link to country not deleted.", ex);
        }
        
    }
}

