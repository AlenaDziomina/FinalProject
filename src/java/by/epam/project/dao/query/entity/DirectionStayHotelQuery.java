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
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of direction stay hotel query forming.
 * @author Helena.Grouk
 */
class DirectionStayHotelQuery implements TypedQuery<DirectionStayHotel>{
    private static final String ERR_DIR_STAY_SAVE = "Direction stay hotel not saved.";
    private static final String ERR_DIR_STAY_LOAD = "Direction stay hotel not loaded.";
    private static final String ERR_DIR_STAY_UPDATE = "Direction stay hotel not updated.";
    private static final String ERR_DIR_STAY_DELETE = "Direction stay hotel not deleted.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_DIRSTAY = "direction_stay_hotels";
    private static final String DB_DIRSTAY_ID_STAY = "id_stay";
    private static final String DB_DIRSTAY_STAY_NO = "stay_no";
    private static final String DB_DIRSTAY_ID_HOTEL = "id_hotel";
    private static final String DB_DIRSTAY_ID_DIRECTION = "id_direction";
    private static final String DB_DIRSTAY_STATUS = "status";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRSTAY + " (" + DB_DIRSTAY_STAY_NO + ", "
            + DB_DIRSTAY_ID_DIRECTION + ", " + DB_DIRSTAY_ID_HOTEL 
            + ") values (?, ?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRSTAY;
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRSTAY + " set ";
    private static final String DELETE_QUERY = 
            "Delete from " + DB_DIRSTAY + WHERE;

    @Override
    public List<Integer> save(List<DirectionStayHotel> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (DirectionStayHotel bean) -> {
                Object[] objects = new Object[3];
                objects[0] = bean.getStayNo();
                objects[1] = bean.getDirection().getIdDirection();
                objects[2] = bean.getHotel().getIdHotel();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_DIR_STAY_SAVE, ex);
        }
    }

    @Override
    public List<DirectionStayHotel> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                DirectionStayHotel bean = new DirectionStayHotel();
                bean.setIdStay(rs.getInt(DB_DIRSTAY_ID_STAY));
                bean.setStayNo(rs.getInt(DB_DIRSTAY_STAY_NO));
                bean.setStatus(rs.getShort(DB_DIRSTAY_STATUS));
                bean.setDirection(new Direction(rs.getInt(DB_DIRSTAY_ID_DIRECTION)));
                bean.setHotel(new Hotel(rs.getInt(DB_DIRSTAY_ID_HOTEL)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_STAY_LOAD, ex);
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
                Appender.append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_STAY_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(DELETE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_DIRSTAY, DB_DIRSTAY_ID_STAY, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRSTAY_NO, DB_DIRSTAY_STAY_NO, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRSTAY_STATUS, DB_DIRSTAY_STATUS, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_DIRECTION, DB_DIRSTAY_ID_DIRECTION, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, criteria, paramList, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        
        try {
            return deleteDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIR_STAY_DELETE, ex);
        }
    }
}


