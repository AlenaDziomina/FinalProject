package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Appender;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of order query forming.
 * @author Helena.Grouk
 */
class OrderQuery implements TypedQuery<Order> {
    private static final String ERR_ORDER_SAVE = "Order not saved.";
    private static final String ERR_ORDER_LOAD = "Order not loaded.";
    private static final String ERR_ORDER_UPDATE = "Order not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_ORDER = "orders";
    private static final String DB_ORDER_ID_ORDER = "id_order";
    private static final String DB_ORDER_ID_USER = "id_user";
    private static final String DB_ORDER_ID_TOUR = "id_tour";
    private static final String DB_ORDER_SEATS = "seats";
    private static final String DB_ORDER_CURR_PRICE = "current_price";
    private static final String DB_ORDER_CURR_DISCOUNT = "current_discount";
    private static final String DB_ORDER_USER_DISCOUNT = "current_user_discount";
    private static final String DB_ORDER_FINAL_PRICE = "final_price";
    private static final String DB_ORDER_DATE = "orderDate";
    private static final String DB_ORDER_STATUS = "status";
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

    @Override
    public List<Integer> save(List<Order> beans, GenericSaveQuery saveGeneric, Connection conn) throws DaoQueryException {
        try {
            return saveGeneric.sendQuery(SAVE_QUERY, conn, Params.fill(beans, (Order bean) -> {
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
            throw new DaoQueryException(ERR_ORDER_SAVE, ex);
        }
    }

    @Override
    public List<Order> load(Criteria criteria, GenericLoadQuery loadGeneric, Connection conn) throws DaoQueryException {
        int pageSize = 50;

        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() {
                Appender.append(DAO_ID_ORDER, DB_ORDER_ID_ORDER, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_USER, DB_ORDER_ID_USER, criteria, paramList, sb, AND);
                Appender.append(DAO_ID_TOUR, DB_ORDER_ID_TOUR, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_SEATS, DB_ORDER_SEATS, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_DATE, DB_ORDER_DATE, criteria, paramList, sb, AND);
                Appender.append(DAO_ORDER_STATUS, DB_ORDER_STATUS, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }
        }.mapQuery();

        try {
            return loadGeneric.sendQuery(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
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
             throw new DaoQueryException(ERR_ORDER_LOAD, ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateGeneric, Connection conn) throws DaoQueryException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() {
                Appender.append(DAO_ORDER_SEATS, DB_ORDER_SEATS, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_DATE, DB_ORDER_DATE, criteria, paramList1, sb, COMMA);
                Appender.append(DAO_ORDER_STATUS, DB_ORDER_STATUS, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_ORDER, DB_ORDER_ID_ORDER, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_USER, DB_ORDER_ID_USER, beans, paramList2, sb, AND);
                Appender.append(DAO_ID_TOUR, DB_ORDER_ID_TOUR, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_SEATS, DB_ORDER_SEATS, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_CURR_PRICE, DB_ORDER_CURR_PRICE, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_CURR_DISCOUNT, DB_ORDER_CURR_DISCOUNT, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_USER_DISCOUNT, DB_ORDER_USER_DISCOUNT, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_FINAL_PRICE, DB_ORDER_FINAL_PRICE, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_DATE, DB_ORDER_DATE, beans, paramList2, sb, AND);
                Appender.append(DAO_ORDER_STATUS, DB_ORDER_STATUS, beans, paramList2, sb, AND);
                return sb.toString();
            }
        }.mapQuery();
        paramList1.addAll(paramList2);

        try {
            return updateGeneric.sendQuery(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_ORDER_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteGeneric, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }


}
