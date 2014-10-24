/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_LIST;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public class CountryQuery implements TypedQuery<Country>{
    
    public static final String DB_COUNTRY = "country";
    public static final String DB_COUNTRY_ID_COUNTRY = "id_country";
    public static final String DB_COUNTRY_NAME = "name";
    public static final String DB_COUNTRY_STATUS = "status";
    public static final String DB_COUNTRY_PICTURE = "picture";
    public static final String DB_COUNTRY_ID_DESCRIPTION = "id_description";
    
    public static final String DAO_ID_COUNTRY = "idCountry";
    public static final String DAO_COUNTRY_NAME = "nameCountry";
    public static final String DAO_COUNTRY_PICTURE = "pictureCountry";
    public static final String DAO_COUNTRY_STATUS = "statusCountry";
   
    private static final String SAVE_QUERY = 
            "Insert into " + DB_COUNTRY + " (" + DB_COUNTRY_NAME + ", "
            + DB_COUNTRY_PICTURE + ", " + DB_COUNTRY_ID_DESCRIPTION 
            + ") values (?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_COUNTRY;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_COUNTRY + " set ";

    public static Country createBean(Criteria criteria){
        Country bean = new Country();
        bean.setIdCountry((Integer) criteria.getParam(DAO_ID_COUNTRY));
        bean.setName((String) criteria.getParam(DAO_COUNTRY_NAME));
        bean.setPicture((String) criteria.getParam(DAO_COUNTRY_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_COUNTRY_STATUS));
        bean.setDescription(DescriptionQuery.createBean(criteria));
        bean.setCityCollection((Collection<City>) criteria.getParam(DAO_CITY_LIST));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<Country> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Country bean) -> {
                Object[] objects = new Object[3];
                objects[0] = bean.getName();
                objects[1] = bean.getPicture();
                objects[2] = bean.getDescription().getIdDescription();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Country not saved.",ex);
        }
    }

    @Override
    public List<Country> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_COUNTRY, DB_COUNTRY_ID_COUNTRY, criteria, paramList, sb, separator);
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, criteria, paramList, sb, separator);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, criteria, paramList, sb, separator);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, criteria, paramList, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, criteria, paramList, sb, separator);
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
                Country bean = new Country();
                bean.setIdCountry(rs.getInt(DB_COUNTRY_ID_COUNTRY));
                bean.setName(rs.getString(DB_COUNTRY_NAME));
                bean.setStatus(rs.getShort(DB_COUNTRY_STATUS));
                bean.setPicture(rs.getString(DB_COUNTRY_PICTURE));
                bean.setDescription(new Description(rs.getInt(DB_COUNTRY_ID_DESCRIPTION)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Country not loaded.", ex);
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
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, criteria, paramList1, sb, separator);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, criteria, paramList1, sb, separator);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, criteria, paramList1, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_COUNTRY, DB_COUNTRY_ID_COUNTRY, beans, paramList2, sb, separator);
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, beans, paramList2, sb, separator);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, beans, paramList2, sb, separator);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, beans, paramList2, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Country not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
