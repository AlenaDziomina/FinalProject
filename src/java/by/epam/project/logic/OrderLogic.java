/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_ALLSTATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import by.epam.project.dao.entquery.OrderQuery;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ID_ORDER;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_FINAL_PRICE;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_SEATS;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_TOURIST_LIST;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import by.epam.project.dao.entquery.TourQuery;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_ALLDATE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_ALLSTATUS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_FREE_SEATS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_SELECT_FOR_UPDATE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.TouristQuery.DAO_TOURIST_FNAME;
import static by.epam.project.dao.entquery.TouristQuery.DAO_TOURIST_LNAME;
import static by.epam.project.dao.entquery.TouristQuery.DAO_TOURIST_MNAME;
import static by.epam.project.dao.entquery.TouristQuery.DAO_TOURIST_PASSPORT;
import by.epam.project.dao.entquery.UserQuery;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_BALANCE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_DISCOUNT;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_SELECT_FOR_UPDATE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoLogicException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.ParamManager;
import by.epam.project.manager.PriceDiscountManager;
import java.util.Date;
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
            throw new DaoLogicException("Company not found.");
        }
        Float companyBalance = companys.get(0).getBalance();
        Float finalPrice = (Float) criteria.getParam(DAO_ORDER_FINAL_PRICE);
        crit[1].addParam(DAO_USER_BALANCE, companyBalance + finalPrice);
        return crit;
    }
    
    private void createTouristList(Criteria criteria, Integer idOrder, AbstractDao dao) {
        List<Tourist> list = (List<Tourist>) criteria.getParam(DAO_ORDER_TOURIST_LIST);
        for (Tourist t : list) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_ORDER, idOrder);
            crit.addParam(DAO_TOURIST_FNAME, t.getFirstName());
            crit.addParam(DAO_TOURIST_MNAME, t.getMiddleName());
            crit.addParam(DAO_TOURIST_LNAME, t.getLastName());
            crit.addParam(DAO_TOURIST_PASSPORT, t.getPassport());
            //dao.createNewTourist(crit);
        }
    }
    
    private Integer updateOrder(Criteria criteria, AbstractDao dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fillOrders(List<Order> orders, AbstractDao dao) throws DaoException {
        if (orders != null) {
            for (Order order : orders) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_TOUR, order.getTour().getIdTour());
                crit1.addParam(DAO_TOUR_ALLDATE, true);
                crit1.addParam(DAO_TOUR_ALLSTATUS, true);
                List<Tour> tours = dao.showTours(crit1);
                if (!tours.isEmpty()) {
                    Tour tour = tours.get(0);
                    order.setTour(tour);
                    Criteria crit4 = new Criteria();
                    crit4.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                    crit4.addParam(DAO_DIRECTION_ALLSTATUS, true);
                    List<Direction> dirs = dao.showDirections(crit4);
                    if (!dirs.isEmpty()) {
                        tour.setDirection(dirs.get(0));
                    }
                }
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_USER, order.getUser().getIdUser());
                List<User> users = dao.showUsers(crit2);
                if (! users.isEmpty()) {
                    order.setUser(users.get(0));
                }
                
                Criteria crit3 = new Criteria();
                crit3.addParam(DAO_ID_ORDER, order.getIdOrder());
                List<Tourist> tourists = dao.showTourists(crit3);
                order.setTouristCollection(tourists);
                
            }
        }
    }
    
}
