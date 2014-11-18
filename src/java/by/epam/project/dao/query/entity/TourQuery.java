package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
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
 * Class of tour query forming.
 * @author Helena.Grouk
 */
public class TourQuery implements TypedQuery<Tour>{
    private static final String ERR_TOUR_SAVE = "Tour not saved.";
    private static final String ERR_TOUR_LOAD = "Tour not loaded.";
    private static final String ERR_TOUR_UPDATE = "Tour not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String FROM = " >= ";
    private static final String TO = " <= ";
    private static final String DB_TOUR = "tour";
    private static final String DB_TOUR_ID_TOUR = "id_tour";
    private static final String DB_TOUR_ID_DIRECTION = "id_direction";
    private static final String DB_TOUR_DATE = "departure_date";
    private static final String DB_TOUR_DAYS_COUNT = "days_count";
    private static final String DB_TOUR_PRICE = "price";
    private static final String DB_TOUR_DISCOUNT = "discount";
    private static final String DB_TOUR_TOTAL_SEATS = "total_seats";
    private static final String DB_TOUR_FREE_SEATS = "free_seats";
    private static final String DB_TOUR_STATUS = "status";
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
    private static final String LOCK_QUERY = 
            " for update";
    
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
            throw new DaoQueryException(ERR_TOUR_SAVE, ex);
        }
    }

    @Override
    public List<Tour> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_TOUR, DB_TOUR_ID_TOUR, criteria, paramList, sb, AND);
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, criteria, paramList, sb, AND);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, criteria, paramList, sb, AND);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, criteria, paramList, sb, AND);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, criteria, paramList, sb, AND);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, criteria, paramList, sb, AND);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, criteria, paramList, sb, AND);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, criteria, paramList, sb, AND);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, criteria, paramList, sb, AND);
                append(DAO_TOUR_DATE_FROM, DB_TOUR_DATE, criteria, paramList, sb, AND, FROM);
                append(DAO_TOUR_DATE_TO, DB_TOUR_DATE, criteria, paramList, sb, AND, TO);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    sb.insert(0, LOAD_QUERY);
                    Boolean forApdate = (Boolean) criteria.getParam(DAO_TOUR_SELECT_FOR_UPDATE);
                    if (forApdate != null && forApdate) {
                        sb.append(LOCK_QUERY);
                    }
                    return sb.toString();
                }
            }  
        }.mapQuery();
        
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
             throw new DaoQueryException(ERR_TOUR_LOAD, ex);
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
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, criteria, paramList1, sb, COMMA);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_TOUR, DB_TOUR_ID_TOUR, beans, paramList2, sb, AND);
                append(DAO_ID_DIRECTION, DB_TOUR_ID_DIRECTION, beans, paramList2, sb, AND);
                append(DAO_TOUR_DATE, DB_TOUR_DATE, beans, paramList2, sb, AND);
                append(DAO_TOUR_DAYS, DB_TOUR_DAYS_COUNT, beans, paramList2, sb, AND);
                append(DAO_TOUR_PRICE, DB_TOUR_PRICE, beans, paramList2, sb, AND);
                append(DAO_TOUR_DISCOUNT, DB_TOUR_DISCOUNT, beans, paramList2, sb, AND);
                append(DAO_TOUR_TOTAL_SEATS, DB_TOUR_TOTAL_SEATS, beans, paramList2, sb, AND);
                append(DAO_TOUR_FREE_SEATS, DB_TOUR_FREE_SEATS, beans, paramList2, sb, AND);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_TOUR_UPDATE, ex);
        }
    } 

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
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
}
