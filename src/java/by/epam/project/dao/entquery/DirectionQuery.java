/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransportationMode;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.QueryExecutionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DirectionQuery implements TypedQuery<Direction>{
    
    public static final String DB_DIRECTION = "direction";
    public static final String DB_DIRECTION_ID_DIRECTION = "id_direction";
    public static final String DB_DIRECTION_ID_TOURTYPE = "id_tour_type";
    public static final String DB_DIRECTION_ID_TRANSMODE = "id_mode";
    public static final String DB_DIRECTION_ID_DESCRIPTION = "id_description";
    public static final String DB_DIRECTION_NAME = "name";
    public static final String DB_DIRECTION_PICTURE = "picture";
    public static final String DB_DIRECTION_TEXT = "text";
    public static final String DB_DIRECTION_STATUS = "status";
    
    public static final String DAO_ID_DIRECTION = "idDirection";
    public static final String DAO_DIRECTION_NAME = "nameDirection";
    public static final String DAO_DIRECTION_PICTURE = "pictureDirection";
    public static final String DAO_DIRECTION_TEXT = "textDirection";
    public static final String DAO_DIRECTION_STATUS = "statusDirection";
   
    private static final String SAVE_QUERY = 
            "Insert into " + DB_DIRECTION + "(" + DB_DIRECTION_NAME + ", "
            + DB_DIRECTION_ID_TOURTYPE + ", " + DB_DIRECTION_ID_TRANSMODE + ", "
            + DB_DIRECTION_ID_DESCRIPTION + ", " + DB_DIRECTION_PICTURE + ", "
            + DB_DIRECTION_TEXT + ") values (?, ?, ?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_DIRECTION;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_DIRECTION + " set ";

    public static final Direction createBean(Criteria criteria) {
        Direction bean = new Direction();
        bean.setIdDirection((Integer) criteria.getParam(DAO_ID_DIRECTION));
        bean.setName((String) criteria.getParam(DAO_DIRECTION_NAME));
        bean.setPicture((String) criteria.getParam(DAO_DIRECTION_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_DIRECTION_STATUS));
        bean.setText((String) criteria.getParam(DAO_DIRECTION_TEXT));
        bean.setDescription(DescriptionQuery.createBean(criteria));
        bean.setTourType(TourTypeQuery.createBean(criteria));
        bean.setTransMode(TransModeQuery.createBean(criteria));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<Direction> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
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
            throw new QueryExecutionException("Direction not saved", ex);
        }
    }

    @Override
    public List<Direction> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_DIRECTION, DB_DIRECTION_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, criteria, paramList, sb, separator);
                append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, criteria, paramList, sb, separator);
                append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, criteria, paramList, sb, separator);
                append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, criteria, paramList, sb, separator);
                append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, criteria, paramList, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, criteria, paramList, sb, separator);
                append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, criteria, paramList, sb, separator);
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
                Direction bean = new Direction();
                bean.setIdDirection(rs.getInt(DB_DIRECTION_ID_DIRECTION));
                bean.setName(rs.getString(DB_DIRECTION_NAME));
                bean.setStatus(rs.getShort(DB_DIRECTION_STATUS));
                bean.setPicture(rs.getString(DB_DIRECTION_PICTURE));
                bean.setText(rs.getString(DB_DIRECTION_TEXT));
                bean.setTransMode(new TransportationMode(rs.getInt(DB_DIRECTION_ID_TRANSMODE)));
                bean.setTourType(new TourType(rs.getInt(DB_DIRECTION_ID_TOURTYPE)));
                bean.setDescription(new Description(rs.getInt(DB_DIRECTION_ID_DESCRIPTION)));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("Direction not loaded.", ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, criteria, paramList1, sb, separator);
                append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, criteria, paramList1, sb, separator);
                append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, criteria, paramList1, sb, separator);
                append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, criteria, paramList1, sb, separator);
                append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, criteria, paramList1, sb, separator);
                append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, criteria, paramList1, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_DIRECTION, DB_DIRECTION_ID_DIRECTION, beans, paramList2, sb, separator);
                append(DAO_DIRECTION_NAME, DB_DIRECTION_NAME, beans, paramList2, sb, separator);
                append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, beans, paramList2, sb, separator);
                append(DAO_DIRECTION_PICTURE, DB_DIRECTION_PICTURE, beans, paramList2, sb, separator);
                append(DAO_DIRECTION_TEXT, DB_DIRECTION_TEXT, beans, paramList2, sb, separator);
                append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, beans, paramList2, sb, separator);
                append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, beans, paramList2, sb, separator);
                append(DAO_ID_DESCRIPTION, DB_DIRECTION_ID_DESCRIPTION, beans, paramList2, sb, separator);
                
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Direction not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
