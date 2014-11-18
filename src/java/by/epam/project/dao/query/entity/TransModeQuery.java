package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Appender;
import by.epam.project.dao.query.Params;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.TransMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of transportation mode query forming.
 * @author Helena.Grouk
 */
class TransModeQuery implements TypedQuery<TransMode>{
    private static final String ERR_TRANSMODE_SAVE = "Transportation mode not saved.";
    private static final String ERR_TRANSMODE_LOAD = "Transportation mode not loaded.";
    private static final String ERR_TRANSMODE_UPDATE = "Transportation mode not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_TRANSMODE = "transportation_mode";
    private static final String DB_TRANSMODE_ID_MODE = "id_mode";
    private static final String DB_TRANSMODE_NAME = "name_mode";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TRANSMODE + " ("
            + DB_TRANSMODE_NAME + ") values (?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TRANSMODE;
    private static final String UPDATE_QUERY = 
            "Update " + DB_TRANSMODE + " set ";

    @Override
    public List<Integer> save(List<TransMode> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (TransMode bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getNameMode();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_TRANSMODE_SAVE, ex);
        }
    }
    
    @Override
    public List<TransMode> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_TRANSMODE, DB_TRANSMODE_ID_MODE, criteria, paramList, sb, AND);
                Appender.append(DAO_TRANSMODE_NAME, DB_TRANSMODE_NAME, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
       
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                TransMode bean = new TransMode();
                bean.setIdMode(rs.getInt(DB_TRANSMODE_ID_MODE));
                bean.setNameMode(rs.getString(DB_TRANSMODE_NAME));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TRANSMODE_LOAD, ex);
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
                Appender.append(DAO_TRANSMODE_NAME, DB_TRANSMODE_NAME, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_TRANSMODE, DB_TRANSMODE_ID_MODE, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TRANSMODE_UPDATE ,ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    
}
