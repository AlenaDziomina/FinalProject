/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.UserDao;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.entity.DirectionQuery;
import by.epam.project.dao.query.entity.OrderQuery;
import by.epam.project.dao.query.entity.TourQuery;
import by.epam.project.dao.query.entity.TouristQuery;
import by.epam.project.dao.query.entity.UserQuery;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlUserDao extends MysqlGuestDao implements UserDao {
   
    protected MysqlUserDao(){}
    
    @Override
    public List<Integer> updateUser(Criteria bean, Criteria criteria) throws DaoException {
        return new UserQuery().update(bean, criteria, updateGeneric, mysqlConn);
    }
    
    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        Integer status = (Integer) criteria.getParam(DAO_DIRECTION_STATUS);
        Boolean allStatus = (Boolean) criteria.getParam(DAO_DIRECTION_ALLSTATUS);
        if (allStatus != null && allStatus) {
            criteria.remuveParam(DAO_DIRECTION_STATUS);
        } else if (status == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
        
        return new DirectionQuery().load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        Integer status = (Integer) criteria.getParam(DAO_TOUR_STATUS);
        Boolean allStatus = (Boolean) criteria.getParam(DAO_TOUR_ALLSTATUS);
        if (allStatus != null && allStatus) {
            criteria.remuveParam(DAO_TOUR_STATUS);
        } else if (status == null) {
            criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        }
        Date dateFrom = (Date) criteria.getParam(DAO_TOUR_DATE_FROM);
        Date dateTo = (Date) criteria.getParam(DAO_TOUR_DATE_TO);
        Boolean allDate = (Boolean) criteria.getParam(DAO_TOUR_ALLDATE);
        if (allDate != null && allDate) {
            criteria.remuveParam(DAO_TOUR_DATE_FROM);
            criteria.remuveParam(DAO_TOUR_DATE_TO);
        } else if (dateFrom == null && dateTo == null) {
            criteria.addParam(DAO_TOUR_DATE_FROM, new Date());
        }
        return new TourQuery().load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<Integer> createNewOrder(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(OrderQuery.createBean(criteria));
        return new OrderQuery().save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException {
        return new TourQuery().update(beans, criteria, updateGeneric, mysqlConn);
    }
    
    @Override
    public List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException {
        return new OrderQuery().update(beans, criteria, updateGeneric, mysqlConn);
    }
    
    @Override
    public List<Integer> createNewTourist(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(TouristQuery.createBean(criteria));
        return new TouristQuery().save(list, saveGeneric, mysqlConn);
    }
    
    @Override
    public List<Order> showOrders(Criteria criteria) throws DaoException {
        return new OrderQuery().load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<Tourist> showTourists(Criteria criteria) throws DaoException {
        return new TouristQuery().load(criteria, loadGeneric, mysqlConn);
    }
}
