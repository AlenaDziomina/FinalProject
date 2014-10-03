/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
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
    
   
    private static final String EM_SAVE_QUERY = 
            "Insert into direction(name, id_tour_type, id_mode, id_description, picture, text) values (?, ?, ?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from direction d "
            + "left join description s on (s.id_description = d.id_description) "
            + "left join tour_type t on (t.id_tour_type = d.id_tour_type) "
            + "left join transportation_mode m on (m.id_mode = d.id_mode) where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from direction d "
            + "left join description s on (s.id_description = d.id_description) "
            + "left join tour_type t on (t.id_tour_type = d.id_tour_type) "
            + "left join transportation_mode m on (m.id_mode = d.id_mode);";
    
    private static final String EM_UPDATE_QUERY = 
            "Update direction set ";

    @Override
    public List<Integer> save(List<Direction> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (Direction bean) -> {
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
            throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public List<Direction> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_DIRECTION, "id_direction", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_TOUR_TYPE, "id_tour_type", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_MODE, "id_mode", criteria, paramList, sb, separator);
                append(PARAM_NAME_NAME_DIRECTION, "name", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_DIRECTION, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_PICTURE_DIRECTION, "picture", criteria, paramList, sb, separator);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Direction bean = new Direction();
                bean.setIdDirection(rs.getInt("id_direction"));
                bean.setName(rs.getString("name"));
                bean.setStatus(rs.getShort("status"));
                bean.setPicture(rs.getString("picture"));
                bean.setText(rs.getString("d.text"));
                bean.setTransMode(new TransportationMode(rs.getInt("id_mode"), rs.getString("name_mode")));
                bean.setTourType(new TourType(rs.getInt("id_tour_type"), rs.getString("name_tour_type")));
                bean.setDescription(new Description(rs.getInt("id_description")));
                return bean;
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        List paramList = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(PARAM_NAME_NAME_DIRECTION, "name", criteria, paramList, sb, separator);
                append(PARAM_NAME_STATUS_DIRECTION, "status", criteria, paramList, sb, separator);
                append(PARAM_NAME_PICTURE_DIRECTION, "picture", criteria, paramList, sb, separator);
                append(PARAM_NAME_TEXT_DIRECTION, "text", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_TOUR_TYPE, "id_tour_type", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_MODE, "id_mode", criteria, paramList, sb, separator);
                append(DAO_ID_DESCRIPTION, "id_description", criteria, paramList, sb, separator);
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
             throw new QueryExecutionException("",ex);
        }
    }
    
    
}
