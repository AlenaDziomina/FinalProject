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
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of search tour query forming.
 * @author Helena.Grouk
 */
class SearchQuery implements TypedQuery<Tour> {
    private static final String ERR_SEARCH_TOUR_LOAD = "Searching tour result not loaded.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String OR = " or ";
    private static final String FROM = " >= ";
    private static final String TO = " <= ";
    private static final String DB_DIRCITY = "direction_cities";
    private static final String DB_DIRCITY_ID_CITY = "id_city";
    private static final String DB_DIRCITY_ID_DIRECTION = "id_direction";
    private static final String DB_DIRCOUNTRY = "direction_countries";
    private static final String DB_DIRCOUNTRY_ID_COUNTRY = "id_country";
    private static final String DB_DIRCOUNTRY_ID_DIRECTION = "id_direction";
    private static final String DB_DIRECTION = "direction";
    private static final String DB_DIRECTION_ID_DIRECTION = "id_direction";
    private static final String DB_DIRECTION_ID_TOURTYPE = "id_tour_type";
    private static final String DB_DIRECTION_ID_TRANSMODE = "id_mode";
    private static final String DB_DIRECTION_STATUS = "status";
    private static final String DB_DIRSTAY = "direction_stay_hotels";
    private static final String DB_DIRSTAY_ID_HOTEL = "id_hotel";
    private static final String DB_DIRSTAY_ID_DIRECTION = "id_direction";
    private static final String DB_HOTEL = "hotel";
    private static final String DB_HOTEL_ID_HOTEL = "id_hotel";
    private static final String DB_HOTEL_STARS = "stars";
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
    private static final String LOAD_QUERY_TOUR_GROUP = 
            " group by " + DB_TOUR_ID_TOUR;

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
        sb.append(LOAD_QUERY_TOUR_GROUP);
       
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
                bean.setDirection(new Direction(rs.getInt(DB_TOUR_ID_DIRECTION)));
                bean.setStatus(rs.getShort(DB_TOUR_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_SEARCH_TOUR_LOAD, ex);
        }
    }

    @Override
    public List<Integer> save(List<Tour> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    private static boolean formQueryTour(Criteria crit, List params, StringBuilder sb, StringBuilder sbw) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_TOUR_DATE_FROM, DB_TOUR_DATE, crit, list, str, AND, FROM);
                Appender.append(DAO_TOUR_DATE_TO, DB_TOUR_DATE, crit, list, str, AND, TO);
                Appender.append(DAO_TOUR_DAYS_FROM, DB_TOUR_DAYS_COUNT, crit, list, str, AND, FROM);
                Appender.append(DAO_TOUR_DAYS_TO, DB_TOUR_DAYS_COUNT, crit, list, str, AND, TO);
                Appender.append(DAO_TOUR_PRICE_FROM, DB_TOUR_PRICE, crit, list, str, AND, FROM);
                Appender.append(DAO_TOUR_PRICE_TO, DB_TOUR_PRICE, crit, list, str, AND, TO);
                Appender.append(DAO_TOUR_DISCOUNT_FROM, DB_TOUR_DISCOUNT, crit, list, str, AND, FROM);
                Appender.append(DAO_TOUR_FREE_SEATS_FROM, DB_TOUR_FREE_SEATS, crit, list, str, AND, FROM);
                Appender.append(DAO_TOUR_STATUS, "t." + DB_TOUR_STATUS, crit, list, str, AND);
                return str.toString();
            }  
        }.mapQuery();
        sb.append(LOAD_QUERY_TOUR);
        sbw.append(WHERE);
        if (!list.isEmpty()) {
            params.addAll(list);
            sbw.append(qu);
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean formQueryDirect(Criteria crit, List params, StringBuilder sb, StringBuilder sbw) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_ID_TOURTYPE, DB_DIRECTION_ID_TOURTYPE, crit, list, str, AND);
                Appender.append(DAO_ID_TRANSMODE, DB_DIRECTION_ID_TRANSMODE, crit, list, str, AND);
                Appender.append(DAO_DIRECTION_STATUS, "d." + DB_DIRECTION_STATUS, crit, list, str, AND);
                return str.toString();
            }  
        }.mapQuery();
        
        if (!list.isEmpty()) {
            sb.append(LOAD_QUERY_DIRECT);
            if (!params.isEmpty()) {
                sbw.append(AND);
            }
            sbw.append(qu);
            params.addAll(list);
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean formQueryCountry(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f2) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.appendArr(DAO_DIRCOUNTRY_ID_COUNTRY, DB_DIRCOUNTRY_ID_COUNTRY, crit, list, str, OR);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (!list.isEmpty()) {
            if (!f2) {
                sb.append(LOAD_QUERY_DIRECT);
            }
            sb.append(LOAD_QUERY_COUNTRY);
            if (!params.isEmpty()) {
                sbw.append(AND);
            }
            sbw.append(qu);
            params.addAll(list);
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean formQueryCity(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f23) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.appendArr(DAO_DIRCITY_ID_CITY, DB_DIRCITY_ID_CITY, crit, list, str, OR);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (!list.isEmpty()) {
            if (!f23) {
                sb.append(LOAD_QUERY_DIRECT);
            }
            sb.append(LOAD_QUERY_CITY);
            if (!params.isEmpty()) {
                sbw.append(AND);
            }
            sbw.append(qu);
            params.addAll(list);
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean formQueryStay(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f234) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.appendArr(DAO_DIRSTAY_ID_HOTEL, DB_DIRSTAY_ID_HOTEL, crit, list, str, OR);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (list.isEmpty()) {
            return false;
        } else {
            if (!f234) {
                sb.append(LOAD_QUERY_DIRECT);
            }
            sb.append(LOAD_QUERY_STAY);
            if (!params.isEmpty()) {
                sbw.append(AND);
            }
            sbw.append(qu);
            params.addAll(list);
            return true;
        }
    }
    
    private static boolean formQueryHotel(Criteria crit, List params, StringBuilder sb, StringBuilder sbw, Boolean f2345, Boolean f5) {
        List list = new ArrayList<>();
        StringBuilder str = new StringBuilder(" ( ");
        String qu = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                Appender.append(DAO_HOTEL_STARS, DB_HOTEL_STARS, crit, list, str, OR);
                return str.toString() + " ) ";
            }  
        }.mapQuery();
        
        if (!list.isEmpty()) {
            if (!f2345) {
                sb.append(LOAD_QUERY_DIRECT);
            }
            if (!f5) {
                sb.append(LOAD_QUERY_STAY);
            }
            sb.append(LOAD_QUERY_HOTEL);
            if (!params.isEmpty()) {
                sbw.append(AND);
            }
            sbw.append(qu);
            params.addAll(list);
            return true;
        } else {
            return false;
        }
    }
}
