package by.epam.project.dao.query.entity;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.dao.query.TypedQuery;
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
    private static final String ERR_TOURTYPE_SAVE = "Tour type not saved.";
    private static final String ERR_TOURTYPE_LOAD = "Tour type not loaded.";
    private static final String ERR_TOURTYPE_UPDATE = "Tour type not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_TOURTYPE = "tour_type";
    private static final String DB_TOURTYPE_ID_TOURTYPE = "id_tour_type";
    private static final String DB_TOURTYPE_NAME = "name_tour_type";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TOURTYPE + " (" + DB_TOURTYPE_NAME
            + ") values (?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TOURTYPE;
    private static final String UPDATE_QUERY = 
            "Update " + DB_TOURTYPE + " set ";

    @Override
    public List<Integer> save(List<TourType> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (TourType bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameTourType();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_TOURTYPE_SAVE, ex);
        }
    }

    @Override
    public List<TourType> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_TOURTYPE, DB_TOURTYPE_ID_TOURTYPE, criteria, paramList, sb, AND);
                append(DAO_TOURTYPE_NAME, DB_TOURTYPE_NAME, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                TourType bean = new TourType();
                bean.setIdTourType(rs.getInt(DB_TOURTYPE_ID_TOURTYPE));
                bean.setNameTourType(rs.getString(DB_TOURTYPE_NAME));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TOURTYPE_LOAD, ex);
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
                append(DAO_TOURTYPE_NAME, DB_TOURTYPE_NAME, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_TOURTYPE, DB_TOURTYPE_ID_TOURTYPE, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TOURTYPE_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }

    public static TourType createBean(Criteria criteria) {
        TourType bean = new TourType();
        bean.setIdTourType((Integer)criteria.getParam(DAO_ID_TOURTYPE));
        bean.setNameTourType((String)criteria.getParam(DAO_TOURTYPE_NAME));
        return bean;
    }
}

