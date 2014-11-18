package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Appender;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of direction link to city query forming.
 * @author Helena.Grouk
 */
class DirectionCityQuery implements TypedQuery<LinkDirectionCity>{
    private static final String ERR_DIR_CITY_SAVE = "Direction link to city not saved.";
    private static final String ERR_DIR_CITY_LOAD = "Direction link to city not loaded.";
    private static final String ERR_DIR_CITY_UPDATE = "Direction link to city not updated.";
    private static final String ERR_DIR_CITY_DELETE = "Direction link to city not deleted.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_DIRCITY = "direction_cities";
    private static final String DB_DIRCITY_ID_CITY = "id_city";
    private static final String DB_DIRCITY_ID_DIRECTION = "id_direction";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRCITY + " (" + DB_DIRCITY_ID_DIRECTION + ", "
            + DB_DIRCITY_ID_CITY + ") values (?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRCITY;
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRCITY + " set ";
    private static final String DELETE_QUERY = 
            "Delete from " + DB_DIRCITY + WHERE;

    @Override
    public List<Integer> save(List<LinkDirectionCity> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (LinkDirectionCity bean) -> {
                Object[] objects = new Object[2];
                objects[0] = bean.getIdDirection();
                objects[1] = bean.getIdCity();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_DIR_CITY_SAVE, ex);
        }
    }

    @Override
    public List<LinkDirectionCity> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_DIRECTION, DB_DIRCITY_ID_DIRECTION, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_CITY, DB_DIRCITY_ID_CITY, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                LinkDirectionCity bean = new LinkDirectionCity();
                bean.setIdCity(rs.getInt(DB_DIRCITY_ID_CITY));
                bean.setIdDirection(rs.getInt(DB_DIRCITY_ID_DIRECTION));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_CITY_LOAD, ex);
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
                Appender.append(DAO_ID_CITY, DB_DIRCITY_ID_CITY, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_DIRECTION, DB_DIRCITY_ID_DIRECTION, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_CITY_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DELETE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_DIRECTION, DB_DIRCITY_ID_DIRECTION, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_CITY, DB_DIRCITY_ID_CITY, criteria, paramList, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        
        try {
            return deleteDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_CITY_DELETE, ex);
        }
    }
}



