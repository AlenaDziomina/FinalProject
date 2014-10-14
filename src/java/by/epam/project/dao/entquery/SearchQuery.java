/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.DirectionCityQuery.DAO_DIRCITY_ID_CITY;
import static by.epam.project.dao.entquery.DirectionCityQuery.DB_DIRCITY;
import static by.epam.project.dao.entquery.DirectionCityQuery.DB_DIRCITY_ID_CITY;
import static by.epam.project.dao.entquery.DirectionCityQuery.DB_DIRCITY_ID_DIRECTION;
import static by.epam.project.dao.entquery.DirectionCountryQuery.DAO_DIRCOUNTRY_ID_COUNTRY;
import static by.epam.project.dao.entquery.DirectionCountryQuery.DB_DIRCOUNTRY;
import static by.epam.project.dao.entquery.DirectionCountryQuery.DB_DIRCOUNTRY_ID_COUNTRY;
import static by.epam.project.dao.entquery.DirectionCountryQuery.DB_DIRCOUNTRY_ID_DIRECTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DB_DIRECTION;
import static by.epam.project.dao.entquery.DirectionQuery.DB_DIRECTION_ID_DIRECTION;
import static by.epam.project.dao.entquery.DirectionQuery.DB_DIRECTION_ID_TOURTYPE;
import static by.epam.project.dao.entquery.DirectionQuery.DB_DIRECTION_ID_TRANSMODE;
import static by.epam.project.dao.entquery.DirectionQuery.DB_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionStayHotelQuery.DAO_DIRSTAY_ID_HOTEL;
import static by.epam.project.dao.entquery.DirectionStayHotelQuery.DB_DIRSTAY;
import static by.epam.project.dao.entquery.DirectionStayHotelQuery.DB_DIRSTAY_ID_DIRECTION;
import static by.epam.project.dao.entquery.DirectionStayHotelQuery.DB_DIRSTAY_ID_HOTEL;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STARS;
import static by.epam.project.dao.entquery.HotelQuery.DB_HOTEL;
import static by.epam.project.dao.entquery.HotelQuery.DB_HOTEL_ID_HOTEL;
import static by.epam.project.dao.entquery.HotelQuery.DB_HOTEL_STARS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_DATE;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_DAYS_COUNT;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_DISCOUNT;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_FREE_SEATS;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_ID_DIRECTION;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_PRICE;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_STATUS;
import static by.epam.project.dao.entquery.TourQuery.DB_TOUR_TOTAL_SEATS;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import static by.epam.project.dao.query.Params.QueryMapper.appendArr;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */

public class SearchQuery implements TypedQuery<Tour> {
    
    public static final String DAO_TOUR_DATE_FROM = "departDateFrom";
    public static final String DAO_TOUR_DATE_TO = "departDateTo";
    public static final String DAO_TOUR_DAYS_FROM = "tourDaysFrom";
    public static final String DAO_TOUR_DAYS_TO = "tourDaysTo";
    public static final String DAO_TOUR_PRICE_FROM = "tourPriceFrom";
    public static final String DAO_TOUR_PRICE_TO = "tourPriceTo";
    public static final String DAO_TOUR_DISCOUNT_FROM = "tourDiscountFrom";
    public static final String DAO_TOUR_FREE_SEATS_FROM = "tourDiscountTo";
    
    private static final String LOAD_QUERY_TOUR = 
            "select * from " + DB_TOUR + " t ";
    private static final String LOAD_QUERY_DIRECT = 
            " left join " + DB_DIRECTION + " d on t." + DB_TOUR_ID_DIRECTION + " = d." + DB_DIRECTION_ID_DIRECTION;
    private static final String LOAD_QUERY_COUNTRY = 
            " left join " + DB_DIRCOUNTRY + " dc on d." + DB_DIRECTION_ID_DIRECTION + " = dc." + DB_DIRCOUNTRY_ID_DIRECTION;
    private static final String LOAD_QUERY_CITY = 
            " left join " + DB_DIRCITY + " ds on d." + DB_DIRECTION_ID_DIRECTION + " = ds." + DB_DIRCITY_ID_DIRECTION;
    private static final String LOAD_QUERY_STAY = 
            " left join " + DB_DIRSTAY + " dh on d." + DB_DIRECTION_ID_DIRECTION + " = dh." + DB_DIRSTAY_ID_DIRECTION;
    private static final String LOAD_QUERY_HOTEL =
            " left join " + DB_HOTEL + " h on h." + DB_HOTEL_ID_HOTEL + " = dh." + DB_DIRSTAY_ID_HOTEL;

    private static boolean formQueryTour(Criteria crit, List params, StringBuilder sb, StringBuilder sbw) {
        
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                String from = " >= ";
                String to = " <= ";
                append(DAO_TOUR_DATE_FROM, DB_TOUR_DATE, crit, list, str, separator, from);
                append(DAO_TOUR_DATE_TO, DB_TOUR_DATE, crit, list, str, separator, to);
                append(DAO_TOUR_DAYS_FROM, DB_TOUR_DAYS_COUNT, crit, list, str, separator, from);
                append(DAO_TOUR_DAYS_TO, DB_TOUR_DAYS_COUNT, crit, list, str, separator, to);
                append(DAO_TOUR_PRICE_FROM, DB_TOUR_PRICE, crit, list, str, separator, from);
                append(DAO_TOUR_PRICE_TO, DB_TOUR_PRICE, crit, list, str, separator, to);
                append(DAO_TOUR_DISCOUNT_FROM, DB_TOUR_DISCOUNT, crit, list, str, separator, from);
                append(DAO_TOUR_FREE_SEATS_FROM, DB_TOUR_FREE_SEATS, crit, list, str, separator, from);
                append(DAO_TOUR_STATUS, DB_TOUR_STATUS, crit, list, str, separator);
                
                return str.toString();
            }  
        }.mapQuery();
        
        sb.append(LOAD_QUERY_TOUR);
        sbw.append(" where ");
                
        if (list.isEmpty()) {
            return false;
        } 
        
        params.addAll(list);
        sbw.append(qu);
        return true;
    }
    
    private static boolean formQueryDirect(Criteria crit, List params, StringBuilder sb, StringBuilder sbw) {
        
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        String separator = " and ";
        String from = " >= ";
        String to = " <= ";
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, crit, list, str, separator);
                append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, crit, list, str, separator);
                append(DAO_DIRECTION_STATUS, DB_DIRECTION_STATUS, crit, list, str, separator);
                return str.toString();
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        }
        
        sb.append(LOAD_QUERY_DIRECT);
        if (!params.isEmpty()) {
            sbw.append(separator);
        }
        sbw.append(qu);
        params.addAll(list);
        return true;
    }
    
    private static boolean formQueryCountry(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f2) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String separator = " and ";
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " or ";
                appendArr(DAO_DIRCOUNTRY_ID_COUNTRY, DB_DIRCOUNTRY_ID_COUNTRY, crit, list, str, separator);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        }
        
        if (!f2) {
            sb.append(LOAD_QUERY_DIRECT);
        }
        sb.append(LOAD_QUERY_COUNTRY);
        if (!params.isEmpty()) {
            sbw.append(separator);
        }
        sbw.append(qu);
        params.addAll(list);
        return true;
    }
    
    private static boolean formQueryCity(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f23) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String separator = " and ";
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " or ";
                appendArr(DAO_DIRCITY_ID_CITY, DB_DIRCITY_ID_CITY, crit, list, str, separator);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        }
        
        if (!f23) {
            sb.append(LOAD_QUERY_DIRECT);
        }
        sb.append(LOAD_QUERY_CITY);
        if (!params.isEmpty()) {
            sbw.append(separator);
        }
        sbw.append(qu);
        params.addAll(list);
        return true;
    }
    
    private static boolean formQueryStay(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f234) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String separator = " and ";
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " or ";
                appendArr(DAO_DIRSTAY_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, crit, list, str, separator);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        }
        
        if (!f234) {
            sb.append(LOAD_QUERY_DIRECT);
        }
        sb.append(LOAD_QUERY_STAY);
        if (!params.isEmpty()) {
            sbw.append(separator);
        }
        sbw.append(qu);
        params.addAll(list);
        return true;
    }
    
    private static boolean formQueryHotel(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f2345, Boolean f5) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String separator = " and ";
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " or ";
                appendArr(DAO_HOTEL_STARS, DB_HOTEL_STARS, crit, list, str, separator);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        }
        
        if (!f2345) {
            sb.append(LOAD_QUERY_DIRECT);
        }
        if (!f5) {
            sb.append(LOAD_QUERY_STAY);
        }
        sb.append(LOAD_QUERY_HOTEL);
        if (!params.isEmpty()) {
            sbw.append(separator);
        }
        sbw.append(qu);
        params.addAll(list);
        return true;
    }
    
    @Override
    public List<Tour> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbw = new StringBuilder();
        
        Boolean f1 = formQueryTour(criteria, params, sb, sbw);
        Boolean f2 = formQueryDirect(criteria, params, sb, sbw);
        Boolean f3 = formQueryCountry(criteria, params, sb, sbw, f2);
        Boolean f4 = formQueryCity(criteria, params, sb, sbw, f2|f3);
        Boolean f5 = formQueryStay(criteria, params, sb, sbw, f2|f3|f4);
        Boolean f6 = formQueryHotel(criteria, params, sb, sbw, f2|f3|f4|f5, f5);
        
        if (f1|f2|f3|f4|f5|f6) {
            sb.append(sbw);
        }
       
        try {
            return loadDao.query(sb.toString(), params.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Tour bean = new Tour();
                bean.setIdTour(rs.getInt(DB_TOUR_ID_TOUR));
                bean.setDepartDate(rs.getDate(DB_TOUR_DATE));
                bean.setDaysCount(rs.getInt(DB_TOUR_DAYS_COUNT));
                bean.setPrice(rs.getFloat(DB_TOUR_PRICE));
                bean.setDiscount(rs.getInt(DB_TOUR_DISCOUNT));
                bean.setTotalSeats(rs.getInt(DB_TOUR_TOTAL_SEATS));
                bean.setFreeSeats(rs.getInt(DB_TOUR_FREE_SEATS));
                bean.setDirection(DirectionQuery.createBean(criteria));
                bean.setStatus(rs.getShort(DB_TOUR_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Tour not loaded.", ex);
        }
    }

    @Override
    public List<Integer> save(List<Tour> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
