package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class of tourist query forming.
 * @author Helena.Grouk
 */
public class TouristQuery implements TypedQuery<Tourist>{
    private static final String ERR_TOURIST_SAVE = "Tourist not saved.";
    private static final String ERR_TOURIST_LOAD = "Tourist not loaded.";
    private static final String ERR_TOURIST_UPDATE = "Tourist not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_TOURIST = "tourist";
    private static final String DB_TOURIST_ID_TOURIST = "id_tourist";
    private static final String DB_TOURIST_ID_ORDER = "id_order";
    private static final String DB_TOURIST_FNAME = "first_name";
    private static final String DB_TOURIST_MNAME = "middle_name";
    private static final String DB_TOURIST_LNAME = "last_name";
    private static final String DB_TOURIST_BIRTH = "birth_date";
    private static final String DB_TOURIST_PASSPORT = "passport";
    private static final String DB_TOURIST_STATUS = "status";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TOURIST + " (" + DB_TOURIST_ID_ORDER + ", "
            + DB_TOURIST_FNAME + ", " + DB_TOURIST_MNAME + ", " 
            + DB_TOURIST_LNAME + ", " + DB_TOURIST_BIRTH + ", "
            + DB_TOURIST_PASSPORT + ") values (?, ?, ?, ?, ?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TOURIST;
    private static final String UPDATE_QUERY = 
            "Update " + DB_TOURIST + " set ";

    @Override
    public List<Integer> save(List<Tourist> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Tourist bean) -> {
                Object[] obj = new Object[6];
                obj[0] = bean.getOrder().getIdOrder();
                obj[1] = bean.getFirstName();
                obj[2] = bean.getMiddleName();
                obj[3] = bean.getLastName();
                obj[4] = bean.getBirthDate();
                obj[5] = bean.getPassport();
                return obj;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_TOURIST_SAVE, ex);
        }
    }
    
    @Override
    public List<Tourist> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_TOURIST, DB_TOURIST_ID_TOURIST, criteria, paramList, sb, AND);
                append(DAO_ID_ORDER, DB_TOURIST_ID_ORDER, criteria, paramList, sb, AND);
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, criteria, paramList, sb, AND);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, criteria, paramList, sb, AND);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, criteria, paramList, sb, AND);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, criteria, paramList, sb, AND);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, criteria, paramList, sb, AND);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Tourist bean = new Tourist();
                bean.setIdTourist(rs.getInt(DB_TOURIST_ID_TOURIST));
                bean.setOrder(new Order(rs.getInt(DB_TOURIST_ID_ORDER)));
                bean.setFirstName(rs.getString(DB_TOURIST_FNAME));
                bean.setMiddleName(rs.getString(DB_TOURIST_MNAME));
                bean.setLastName(rs.getString(DB_TOURIST_LNAME));
                bean.setBirthDate(rs.getDate(DB_TOURIST_BIRTH));
                bean.setPassport(rs.getString(DB_TOURIST_PASSPORT));
                bean.setStatus(rs.getShort(DB_TOURIST_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TOURIST_LOAD, ex);
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
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, criteria, paramList1, sb, COMMA);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, criteria, paramList1, sb, COMMA);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, criteria, paramList1, sb, COMMA);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, criteria, paramList1, sb, COMMA);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, criteria, paramList1, sb, COMMA);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_TOURIST, DB_TOURIST_ID_TOURIST, beans, paramList2, sb, AND);
                append(DAO_ID_ORDER, DB_TOURIST_ID_ORDER, beans, paramList2, sb, AND);
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, beans, paramList2, sb, AND);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, beans, paramList2, sb, AND);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, beans, paramList2, sb, AND);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, beans, paramList2, sb, AND);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, beans, paramList2, sb, AND);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TOURIST_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    public static Object createBean(Criteria criteria) {
        Tourist bean = new Tourist();
        bean.setIdTourist((Integer) criteria.getParam(DAO_ID_TOURIST));
        bean.setOrder(new Order((Integer) criteria.getParam(DAO_ID_ORDER)));
        bean.setFirstName((String) criteria.getParam(DAO_TOURIST_FNAME));
        bean.setMiddleName((String) criteria.getParam(DAO_TOURIST_MNAME));
        bean.setLastName((String) criteria.getParam(DAO_TOURIST_LNAME));
        bean.setBirthDate((Date) criteria.getParam(DAO_TOURIST_BIRTH));
        bean.setPassport((String) criteria.getParam(DAO_TOURIST_PASSPORT));
        return bean;
    }
}
