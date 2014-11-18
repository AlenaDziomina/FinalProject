package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoLogicException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.PriceDiscountManager;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author User
 */
public class OrderLogic extends AbstractLogic {

    @Override
    List getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Order> orders = dao.showOrders(criteria);
        fillOrders(orders, dao);
        Order.DateComparator comparator = new Order.DateComparator();
        Collections.sort(orders, comparator);
        return orders;
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idOrder = (Integer) criteria.getParam(DAO_ID_ORDER);
        if (idOrder != null) {
            return updateOrder(criteria, dao);
        } else {
            return createOrder(criteria, dao);
        }
    }

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idOrder = (Integer) criteria.getParam(DAO_ID_ORDER);
        deleteTouristList(criteria, dao);
        fineUser(criteria, dao);
        Criteria bean = new Criteria();
        bean.addParam(DAO_ID_ORDER, idOrder);
        Criteria crit = new Criteria();
        crit.addParam(DAO_ORDER_STATUS, DELETED);
        dao.updateOrder(bean, crit);
        return idOrder;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }

    private void fillOrders(List<Order> orders, AbstractDao dao) throws DaoException {
        if (orders != null) {
            for (Order order : orders) {
                Criteria tourCrit = new Criteria();
                tourCrit.addParam(DAO_ID_TOUR, order.getTour().getIdTour());
                tourCrit.addParam(DAO_TOUR_ALLDATE, true);
                tourCrit.addParam(DAO_TOUR_ALLSTATUS, true);
                List<Tour> tours = dao.showTours(tourCrit);
                if (!tours.isEmpty()) {
                    Tour tour = tours.get(0);
                    order.setTour(tour);
                    Criteria directCrit = new Criteria();
                    directCrit.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                    directCrit.addParam(DAO_DIRECTION_ALLSTATUS, true);
                    List<Direction> dirs = dao.showDirections(directCrit);
                    if (!dirs.isEmpty()) {
                        new DirectionLogic().fillDirections(dirs, dao);
                        tour.setDirection(dirs.get(0));
                    }
                }

                Criteria userCrit = new Criteria();
                userCrit.addParam(DAO_ID_USER, order.getUser().getIdUser());
                List<User> users = dao.showUsers(userCrit);
                if (! users.isEmpty()) {
                    order.setUser(users.get(0));
                }

                Criteria touristCrit = new Criteria();
                touristCrit.addParam(DAO_ID_ORDER, order.getIdOrder());
                List<Tourist> tourists = dao.showTourists(touristCrit);
                Tourist.FioComparator comparator = new Tourist.FioComparator();
                Collections.sort(tourists, comparator);
                order.setTouristCollection(tourists);
            }
        }
    }

    private Integer createOrder(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria[] userCrit = prepareUserUpdate(criteria, dao);
        Criteria[] tourCrit = prepareTourUpdate(criteria, dao);
        Criteria[] companyCrit = prepareCompanyUpdate(criteria, dao);
        List<Integer> res = dao.createNewOrder(criteria);
        Integer idOrder = res.get(0);
        createTouristList(criteria, idOrder, dao);
        dao.updateUser(userCrit[0], userCrit[1]);
        dao.updateTour(tourCrit[0], tourCrit[1]);
        dao.updateUser(companyCrit[0], companyCrit[1]);
        return idOrder;
    }

    private Criteria[] prepareUserUpdate(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria[] crit = new Criteria[]{new Criteria(), new Criteria()};
        crit[0].addParam(DAO_ID_USER, criteria.getParam(DAO_ID_USER));
        crit[0].addParam(DAO_USER_SELECT_FOR_UPDATE, true);
        List<User> users = dao.showUsers(crit[0]);
        if (users.isEmpty()) {
            throw new DaoException("User not found.");
        }
        User user = users.get(0);
        Float userBalance = user.getBalance();
        Float finalPrice = (Float) criteria.getParam(DAO_ORDER_FINAL_PRICE);
        if (userBalance < finalPrice) {
            throw new DaoLogicException("message.userBalance");
        }
        crit[1].addParam(DAO_USER_BALANCE, userBalance - finalPrice);
        Integer discountForOrder = PriceDiscountManager.getDiscountForOrder(user.getDiscount(), finalPrice);
        crit[1].addParam(DAO_USER_DISCOUNT, discountForOrder);
        return crit;
    }

    private Criteria[] prepareTourUpdate(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria[] crit = new Criteria[]{new Criteria(), new Criteria()};
        crit[0].addParam(DAO_ID_TOUR, criteria.getParam(DAO_ID_TOUR));
        crit[0].addParam(DAO_TOUR_SELECT_FOR_UPDATE, true);
        List<Tour> tours = dao.showTours(crit[0]);
        if (tours.isEmpty()) {
            throw new DaoException("Tour not found.");
        }
        Integer freeSeats = tours.get(0).getFreeSeats();
        Integer orderSeats = (Integer) criteria.getParam(DAO_ORDER_SEATS);
        if (freeSeats < orderSeats) {
            throw new DaoLogicException("message.tourFreeSeats");
        }
        crit[1].addParam(DAO_TOUR_FREE_SEATS, freeSeats - orderSeats);
        return crit;
    }

    private Criteria[] prepareCompanyUpdate(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idCompany = Integer.decode(ConfigurationManager.getProperty("db.company.id"));
        Criteria[] crit = new Criteria[]{new Criteria(), new Criteria()};
        crit[0].addParam(DAO_ID_USER, idCompany);
        crit[0].addParam(DAO_USER_SELECT_FOR_UPDATE, true);
        List<User> companys = dao.showUsers(crit[0]);
        if (companys.isEmpty()) {
            throw new DaoException("Company not found.");
        }
        Float companyBalance = companys.get(0).getBalance();
        Float finalPrice = (Float) criteria.getParam(DAO_ORDER_FINAL_PRICE);
        crit[1].addParam(DAO_USER_BALANCE, companyBalance + finalPrice);
        return crit;
    }

    private void createTouristList(Criteria criteria, Integer idOrder, AbstractDao dao) throws DaoException {
        List<Tourist> list = (List<Tourist>) criteria.getParam(DAO_ORDER_TOURIST_LIST);
        for (Tourist t : list) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_ORDER, idOrder);
            crit.addParam(DAO_TOURIST_FNAME, t.getFirstName());
            crit.addParam(DAO_TOURIST_MNAME, t.getMiddleName());
            crit.addParam(DAO_TOURIST_LNAME, t.getLastName());
            crit.addParam(DAO_TOURIST_PASSPORT, t.getPassport());
            dao.createNewTourist(crit);
        }
    }

    private Integer updateOrder(Criteria criteria, AbstractDao dao) throws DaoException {
        updateTouristList(criteria, dao);
        Integer idOrder = (Integer) criteria.getParam(DAO_ID_ORDER);
        return idOrder;
    }

    private void fineUser(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria bean = new Criteria();
        bean.addParam(DAO_ID_USER, criteria.getParam(DAO_ID_USER));
        bean.addParam(DAO_USER_SELECT_FOR_UPDATE, true);
        List<User> users = dao.showUsers(bean);
        if (users.isEmpty()) {
            throw new DaoException("User not found.");
        }
        User user = users.get(0);
        Integer userDiscount = user.getDiscount();
        Integer discountForDeleteOrder = PriceDiscountManager.getDiscountForDeleteOrder(userDiscount);
        Criteria crit = new Criteria();
        crit.addParam(DAO_USER_DISCOUNT, discountForDeleteOrder);
        dao.updateUser(bean, crit);
    }

    private void updateTouristList(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Tourist> list = (List<Tourist>) criteria.getParam(DAO_ORDER_TOURIST_LIST);
        for (Tourist t : list) {
            Criteria bean = new Criteria();
            bean.addParam(DAO_ID_TOURIST, t.getIdTourist());
            Criteria crit = new Criteria();
            crit.addParam(DAO_TOURIST_FNAME, t.getFirstName());
            crit.addParam(DAO_TOURIST_MNAME, t.getMiddleName());
            crit.addParam(DAO_TOURIST_LNAME, t.getLastName());
            crit.addParam(DAO_TOURIST_PASSPORT, t.getPassport());
            dao.updateTourist(bean, crit);
        }
    }

    private void deleteTouristList(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Tourist> list = (List<Tourist>) criteria.getParam(DAO_ORDER_TOURIST_LIST);
        for (Tourist t : list) {
            Criteria bean = new Criteria();
            bean.addParam(DAO_ID_TOURIST, t.getIdTourist());
            Criteria crit = new Criteria();
            crit.addParam(DAO_TOURIST_STATUS, DELETED);
            dao.updateTourist(bean, crit);
        }
    }
}
