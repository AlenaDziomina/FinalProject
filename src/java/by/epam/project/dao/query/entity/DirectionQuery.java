package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Appender;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.Params.QueryMapper;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of city direction forming.
 * @author Helena.Grouk
 */
class DirectionQuery implements TypedQuery<Direction>{
    private static final String ERR_DIRECTION_SAVE = "Direction not saved.";
    private static final String ERR_DIRECTION_LOAD = "Direction not loaded.";
    private static final String ERR_DIRECTION_UPDATE = "Direction not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_DIRECTION = "direction";
    private static final String DB_DIRECTION_ID_DIRECTION = "id_direction";
    private static final String DB_DIRECTION_ID_TOURTYPE = "id_tour_type";
    private static final String DB_DIRECTION_ID_TRANSMODE = "id_mode";
    private static final String DB_DIRECTION_ID_DESCRIPTION = "id_description";
    private static final String DB_DIRECTION_NAME = "name";
    private static final String DB_DIRECTION_PICTURE = "picture";
    private static final String DB_DIRECTION_TEXT = "text";
    private static final String DB_DIRECTION_STATUS = "status";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRECTION + " (" + DB_DIRECTION_NAME + ", "
            + DB_DIRECTION_ID_TOURTYPE + ", " + DB_DIRECTION_ID_TRANSMODE + ", "
            + DB_DIRECTION_ID_DESCRIPTION + ", " + DB_DIRECTION_PICTURE + ", "
            + DB_DIRECTION_TEXT + ") values (?, ?, ?, ?, ?, ?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRECTION;
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRECTION + " set ";
    
    @Override
    public List<Integer> save(List<Direction> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Direction bean) -> {
                Object[] objects = new Object[6];
                objects[0] = bean.getName();
                objects[1] = bean.getTourType().getIdTourType();
                objects[2] = bean.getTransMode().getIdMode();
                objects[3] = bean.getDescription().getIdDescription();
                objects[4] = bean.getPicture();
                objects[5] = bean.getText();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_DIRECTION_SAVE, ex);
        }
    }

    @Override
    public List<Direction> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_DIRECTION, DB_DIRECTION_ID_DIRECTION, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, criteria, paramList, sb, AND);
                Appender.append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Direction bean = new Direction();
                bean.setIdDirection(rs.getInt(DB_DIRECTION_ID_DIRECTION));
                bean.setName(rs.getString(DB_DIRECTION_NAME));
                bean.setStatus(rs.getShort(DB_DIRECTION_STATUS));
                bean.setPicture(rs.getString(DB_DIRECTION_PICTURE));
                bean.setText(rs.getString(DB_DIRECTION_TEXT));
                bean.setTransMode(new TransMode(rs.getInt(DB_DIRECTION_ID_TRANSMODE)));
                bean.setTourType(new TourType(rs.getInt(DB_DIRECTION_ID_TOURTYPE)));
                bean.setDescription(new Description(rs.getInt(DB_DIRECTION_ID_DESCRIPTION)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIRECTION_LOAD, ex);
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
                Appender.append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_DIRECTION, DB_DIRECTION_ID_DIRECTION, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, beans, paramList2, sb, AND);
                Appender.append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DIRECTION_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    
}
