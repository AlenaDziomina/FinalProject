/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_DAYS_COUNT;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_DEPARTURE_DATE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_DISCOUNT_TOUR;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_FREE_SEATS;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_TOUR;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PRICE_TOUR;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TOTAL_SEATS;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.exception.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Tour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TourQuery implements TypedQuery<Tour>{
    
    
    private static final String EM_SAVE_QUERY = 
            "Insert into tour(id_direction, departure_date, days_count, price, discount, total_seats, free_seats) values (?,?,?,?,?,?,?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from tour where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from tour;";
    private static final String EM_UPDATE_QUERY = 
            "Update tour set ";

    @Override
    public List<Integer> save(List<Tour> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (Tour bean) -> {
                Object[] objects = new Object[7];
                objects[0] = bean.getIdDirection();
                objects[1] = bean.getDepartureDate();
                objects[2] = bean.getDaysCount();
                objects[3] = bean.getPrice();
                objects[4] = bean.getDiscount();
                objects[5] = bean.getTotalSeats();
                objects[6] = bean.getFreeSeats();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public List<Tour> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(PARAM_NAME_ID_TOUR, "id_tour", criteria, paramList, sb, separator);
                append(PARAM_NAME_ID_DIRECTION, "id_direction", criteria, paramList, sb, separator);
                append(PARAM_NAME_DEPARTURE_DATE, "departure_date", criteria, paramList, sb, separator);
                append(PARAM_NAME_DAYS_COUNT, "days_count", criteria, paramList, sb, separator);
                append(PARAM_NAME_PRICE_TOUR, "price", criteria, paramList, sb, separator);
                append(PARAM_NAME_DISCOUNT_TOUR, "discount", criteria, paramList, sb, separator);
                append(PARAM_NAME_TOTAL_SEATS, "total_seats", criteria, paramList, sb, separator);
                append(PARAM_NAME_FREE_SEATS, "free_seats", criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, new Params.RowMapper<Tour>() {
                @Override
                public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Tour bean = new Tour();
                    bean.setIdTour(rs.getInt("id_tour"));
                    bean.setIdDirection(rs.getInt("id_direction"));
                    bean.setDepartureDate(rs.getDate("departure_date"));
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("",ex);
        }
    }

    @Override
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {        
        List paramList = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
//                append(PARAM_NAME_ROLE, "role_name", criteria, paramList, sb, separator);
                sb.append(" where ");
                separator = " and ";
//                append(PARAM_NAME_ID_ROLE, "id_role", beans, paramList2, sb, separator);
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
