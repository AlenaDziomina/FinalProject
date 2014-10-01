/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.LinkDirectionCountry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionCountryQuery implements TypedQuery<LinkDirectionCountry>{

    private static final String EM_SAVE_QUERY = 
            "Insert into direction_countries(id_direction, id_country) values (?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from direction_countries where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from direction_countries;";
    private static final String EM_UPDATE_QUERY = 
            "Update direction_countries set ";

    @Override
    public List<Integer> save(List<LinkDirectionCountry> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (LinkDirectionCountry bean) -> {
                Object[] objects = new Object[2];
                objects[0] = bean.getIdDirection();
                objects[1] = bean.getIdCountry();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
    }

    @Override
    public List<LinkDirectionCountry> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_DIRECTION, "id_direction", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_COUNTRY, "id_country", criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                LinkDirectionCountry bean = new LinkDirectionCountry();
                bean.setIdCountry(rs.getInt("id_country"));
                bean.setIdDirection(rs.getInt("id_direction"));
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
                append(PARAM_NAME_ID_COUNTRY, "id_country", criteria, paramList, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(PARAM_NAME_ID_DIRECTION, "id_direction", beans, paramList2, sb, separator);
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

