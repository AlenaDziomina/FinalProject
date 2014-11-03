/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class TourQuery implements TypedQuery<Tour>{
    
    public static final String DB_TOUR = "tour";
    public static final String DB_TOUR_ID_TOUR = "id_tour";
    public static final String DB_TOUR_ID_DIRECTION = "id_direction";
    public static final String DB_TOUR_DATE = "departure_date";
    public static final String DB_TOUR_DAYS_COUNT = "days_count";
    public static final String DB_TOUR_PRICE = "price";
    public static final String DB_TOUR_DISCOUNT = "discount";
    public static final String DB_TOUR_TOTAL_SEATS = "total_seats";
    public static final String DB_TOUR_FREE_SEATS = "free_seats";
    public static final String DB_TOUR_STATUS = "status";
    
    public static final String DAO_ID_TOUR = "idTour";
    public static final String DAO_TOUR_DATE = "dateTour";
    public static final String DAO_TOUR_DAYS = "daysTour";
    public static final String DAO_TOUR_PRICE = "priceTour";
    public static final String DAO_TOUR_DISCOUNT = "discountTour";
    public static final String DAO_TOUR_TOTAL_SEATS = "totalSeatsTour";
    public static final String DAO_TOUR_FREE_SEATS = "freeSeatsTour";
    public static final String DAO_TOUR_STATUS = "statusTour";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TOUR + " (" + DB_TOUR_ID_DIRECTION + ", "
            + DB_TOUR_DATE + ", " + DB_TOUR_DAYS_COUNT + ", "
            + DB_TOUR_PRICE + ", " + DB_TOUR_DISCOUNT + ", "
            + DB_TOUR_TOTAL_SEATS + ", " + DB_TOUR_FREE_SEATS 
            + ") values (?,?,?,?,?,?,?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TOUR;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_TOUR + " set ";

    public static Tour createBean(Criteria criteria) {
        Tour bean = new Tour();
        bean.setIdTour((Integer)criteria.getParam(DAO_ID_TOUR));
        bean.setDepartDate((Date)criteria.getParam(DAO_TOUR_DATE));
        bean.setDaysCount((Integer)criteria.getParam(DAO_TOUR_DAYS));
        bean.setPrice((Float)criteria.getParam(DAO_TOUR_PRICE));
        bean.setDiscount((Integer)criteria.getParam(DAO_TOUR_DISCOUNT));
        bean.setTotalSeats((Integer)criteria.getParam(DAO_TOUR_TOTAL_SEATS));
        bean.setFreeSeats((Integer)criteria.getParam(DAO_TOUR_FREE_SEATS));
        bean.setDirection(DirectionQuery.createBean(criteria));
        bean.setStatus((Short)criteria.getParam(DAO_TOUR_STATUS));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<Tour> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Tour bean) -> {
                Object[] objects = new Object[7];
                objects[0] = bean.getDirection().getIdDirection();
                objects[1] = bean.getDepartDate();
                objects[2] = bean.getDaysCount();
                objects[3] = bean.getPrice();
                objects[4] = bean.getDiscount();
                objects[5] = bean.getTotalSeats();
                objects[6] = bean.getFreeSeats();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("",ex);
        }
    }

    @Override
    public List<Tour> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_TOUR, DB_TOUR_ID_TOUR, criteria, paramList, sb, separator);
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, criteria, paramList, sb, separator);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, criteria, paramList, sb, separator);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, criteria, paramList, sb, separator);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, criteria, paramList, sb, separator);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, criteria, paramList, sb, separator);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, criteria, paramList, sb, separator);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, criteria, paramList, sb, separator);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, criteria, paramList, sb, separator);
                String from = " >= ";
                String to = " <= ";
                append(DAO_TOUR_DATE_FROM, DB_TOUR_DATE, criteria, paramList, sb, separator, from);
                append(DAO_TOUR_DATE_TO, DB_TOUR_DATE, criteria, paramList, sb, separator, to);
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
                Tour bean = new Tour();
                bean.setIdTour(rs.getInt(DB_TOUR_ID_TOUR));
                bean.setDepartDate(rs.getDate(DB_TOUR_DATE));
                bean.setDaysCount(rs.getInt(DB_TOUR_DAYS_COUNT));
                bean.setPrice(rs.getFloat(DB_TOUR_PRICE));
                bean.setDiscount(rs.getInt(DB_TOUR_DISCOUNT));
                bean.setTotalSeats(rs.getInt(DB_TOUR_TOTAL_SEATS));
                bean.setFreeSeats(rs.getInt(DB_TOUR_FREE_SEATS));
                bean.setDirection(new Direction(rs.getInt(DB_TOUR_ID_DIRECTION)));
                bean.setStatus(rs.getShort(DB_TOUR_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Tour not loaded.", ex);
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
                String separator = " , ";
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, criteria, paramList1, sb, separator);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, criteria, paramList1, sb, separator);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, criteria, paramList1, sb, separator);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, criteria, paramList1, sb, separator);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, criteria, paramList1, sb, separator);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, criteria, paramList1, sb, separator);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, criteria, paramList1, sb, separator);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_TOUR, DB_TOUR_ID_TOUR, beans, paramList2, sb, separator);
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, beans, paramList2, sb, separator);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, beans, paramList2, sb, separator);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, beans, paramList2, sb, separator);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, beans, paramList2, sb, separator);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, beans, paramList2, sb, separator);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, beans, paramList2, sb, separator);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, beans, paramList2, sb, separator);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Tour not updated.", ex);
        }
    } 

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
