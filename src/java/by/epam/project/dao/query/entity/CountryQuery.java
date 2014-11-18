package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Criteria;
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
 * Class of country query forming.
 * @author Helena.Grouk
 */
public class CountryQuery implements TypedQuery<Country>{
    private static final String ERR_COUNTRY_SAVE = "Country not saved.";
    private static final String ERR_COUNTRY_LOAD = "Country not loaded.";
    private static final String ERR_COUNTRY_UPDATE = "Country not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_COUNTRY = "country";
    private static final String DB_COUNTRY_ID_COUNTRY = "id_country";
    private static final String DB_COUNTRY_NAME = "name";
    private static final String DB_COUNTRY_STATUS = "status";
    private static final String DB_COUNTRY_PICTURE = "picture";
    private static final String DB_COUNTRY_ID_DESCRIPTION = "id_description";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_COUNTRY + " (" + DB_COUNTRY_NAME + ", "
            + DB_COUNTRY_PICTURE + ", " + DB_COUNTRY_ID_DESCRIPTION 
            + ") values (?, ?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_COUNTRY;
    private static final String UPDATE_QUERY = 
            "Update " + DB_COUNTRY + " set ";

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
            throw new DaoQueryException(ERR_COUNTRY_SAVE, ex);
        }
    }

    @Override
    public List<Country> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_COUNTRY, DB_COUNTRY_ID_COUNTRY, criteria, paramList, sb, AND);
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, criteria, paramList, sb, AND);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, criteria, paramList, sb, AND);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, criteria, paramList, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
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
             throw new DaoQueryException(ERR_COUNTRY_LOAD, ex);
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
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, criteria, paramList1, sb, COMMA);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, criteria, paramList1, sb, COMMA);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, criteria, paramList1, sb, COMMA);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_COUNTRY, DB_COUNTRY_ID_COUNTRY, beans, paramList2, sb, AND);
                append(DAO_COUNTRY_NAME, DB_COUNTRY_NAME, beans, paramList2, sb, AND);
                append(DAO_COUNTRY_STATUS, DB_COUNTRY_STATUS, beans, paramList2, sb, AND);
                append(DAO_COUNTRY_PICTURE, DB_COUNTRY_PICTURE, beans, paramList2, sb, AND);
                append(DAO_ID_DESCRIPTION, DB_COUNTRY_ID_DESCRIPTION, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_COUNTRY_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
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
}
