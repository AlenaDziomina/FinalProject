/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
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
public class OrderQuery implements TypedQuery<Order> {
    
    public static final String DB_ORDER = "orders";
    public static final String DB_ORDER_ID_ORDER = "id_order";
    public static final String DB_ORDER_ID_USER = "id_user";
    public static final String DB_ORDER_ID_TOUR = "id_tour";
    public static final String DB_ORDER_SEATS = "seats";
    public static final String DB_ORDER_CURR_PRICE = "current_price";
    public static final String DB_ORDER_CURR_DISCOUNT = "current_discount";
    public static final String DB_ORDER_USER_DISCOUNT = "current_user_discount";
    public static final String DB_ORDER_FINAL_PRICE = "final_price";
    public static final String DB_ORDER_DATE = "orderDate";
    public static final String DB_ORDER_STATUS = "status";
    
    public static final String DAO_ID_ORDER = "idOrder";
    public static final String DAO_ORDER_SEATS = "seatsOrder";
    public static final String DAO_ORDER_CURR_PRICE = "currPriceOrder";
    public static final String DAO_ORDER_CURR_DISCOUNT = "currDiscountOrder";
    public static final String DAO_ORDER_USER_DISCOUNT = "currUserDiscountOrder";
    public static final String DAO_ORDER_FINAL_PRICE = "finalPriceOrder";
    public static final String DAO_ORDER_DATE = "dateOrder";
    public static final String DAO_ORDER_STATUS = "statusOrder";
    public static final String DAO_ORDER_TOURIST_LIST = "orderTouristList";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_ORDER + " (" + DB_ORDER_ID_USER + ", "
            + DB_ORDER_ID_TOUR + ", " + DB_ORDER_SEATS + ", " 
            + DB_ORDER_CURR_PRICE + ", " + DB_ORDER_CURR_DISCOUNT + ", "
            + DB_ORDER_USER_DISCOUNT + ", " + DB_ORDER_FINAL_PRICE + ", "
            + DB_ORDER_DATE + ") values (?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_ORDER;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_ORDER + " set ";

    public static Order createBean(Criteria criteria) {
        Order bean = new Order();
        bean.setIdOrder((Integer)criteria.getParam(DAO_ID_ORDER));
        bean.setOrderDate((Date)criteria.getParam(DAO_ORDER_DATE));
        bean.setSeats((Integer)criteria.getParam(DAO_ORDER_SEATS));
        bean.setCurrentPrice((Float)criteria.getParam(DAO_ORDER_CURR_PRICE));
        bean.setCurrentDiscount((Integer)criteria.getParam(DAO_ORDER_CURR_DISCOUNT));
        bean.setCurrentUserDiscount((Integer)criteria.getParam(DAO_ORDER_USER_DISCOUNT));
        bean.setFinalPrice((Float)criteria.getParam(DAO_ORDER_FINAL_PRICE));
        bean.setTour(TourQuery.createBean(criteria));
        bean.setUser(UserQuery.createBean(criteria));
        bean.setStatus((Short)criteria.getParam(DAO_ORDER_STATUS));
        return bean;
    }
    
     @Override
    public List<Integer> save(List<Order> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Order bean) -> {
                Object[] obj = new Object[8];
                obj[0] = bean.getUser().getIdUser();
                obj[1] = bean.getTour().getIdTour();
                obj[2] = bean.getSeats();
                obj[3] = bean.getCurrentPrice();
                obj[4] = bean.getCurrentDiscount();
                obj[5] = bean.getCurrentUserDiscount();
                obj[6] = bean.getFinalPrice();
                obj[7] = bean.getOrderDate();
                return obj;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Order not saved.", ex);
        }
    }
    
    @Override
    public List<Order> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_ORDER, DB_ORDER_ID_ORDER, criteria, paramList, sb, separator);
                append(DAO_ID_USER, DB_ORDER_ID_USER, criteria, paramList, sb, separator);
                append(DAO_ID_TOUR, DB_ORDER_ID_TOUR, criteria, paramList, sb, separator);
                append(DAO_ORDER_SEATS, DB_ORDER_SEATS, criteria, paramList, sb, separator);
                append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, criteria, paramList, sb, separator);
                append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, criteria, paramList, sb, separator);
                append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, criteria, paramList, sb, separator);
                append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, criteria, paramList, sb, separator);
                append(DAO_ORDER_DATE, DB_ORDER_DATE, criteria, paramList, sb, separator);
                append(DAO_ORDER_STATUS, DB_ORDER_STATUS, criteria, paramList, sb, separator);
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
                Order bean = new Order();
                bean.setIdOrder(rs.getInt(DB_ORDER_ID_ORDER));
                bean.setUser(new User(rs.getInt(DB_ORDER_ID_USER)));
                bean.setTour(new Tour(rs.getInt(DB_ORDER_ID_TOUR)));
                bean.setSeats(rs.getInt(DB_ORDER_SEATS));
                bean.setCurrentPrice(rs.getFloat(DB_ORDER_CURR_PRICE));
                bean.setCurrentDiscount(rs.getInt(DB_ORDER_CURR_DISCOUNT));
                bean.setCurrentUserDiscount(rs.getInt(DB_ORDER_USER_DISCOUNT));
                bean.setFinalPrice(rs.getFloat(DB_ORDER_FINAL_PRICE));
                bean.setOrderDate(rs.getDate(DB_ORDER_DATE));
                bean.setStatus(rs.getShort(DB_ORDER_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Order not loaded.", ex);
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
                append(DAO_ORDER_SEATS, DB_ORDER_SEATS, criteria, paramList1, sb, separator);
                append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, criteria, paramList1, sb, separator);
                append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, criteria, paramList1, sb, separator);
                append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, criteria, paramList1, sb, separator);
                append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, criteria, paramList1, sb, separator);
                append(DAO_ORDER_DATE, DB_ORDER_DATE, criteria, paramList1, sb, separator);
                append(DAO_ORDER_STATUS, DB_ORDER_STATUS, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_ORDER, DB_ORDER_ID_ORDER, beans, paramList2, sb, separator);
                append(DAO_ID_USER, DB_ORDER_ID_USER, beans, paramList2, sb, separator);
                append(DAO_ID_TOUR, DB_ORDER_ID_TOUR, beans, paramList2, sb, separator);
                append(DAO_ORDER_SEATS, DB_ORDER_SEATS, beans, paramList2, sb, separator);
                append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, beans, paramList2, sb, separator);
                append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, beans, paramList2, sb, separator);
                append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, beans, paramList2, sb, separator);
                append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, beans, paramList2, sb, separator);
                append(DAO_ORDER_DATE, DB_ORDER_DATE, beans, paramList2, sb, separator);
                append(DAO_ORDER_STATUS, DB_ORDER_STATUS, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Order not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
