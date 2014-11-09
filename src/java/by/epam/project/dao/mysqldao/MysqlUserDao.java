/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.UserDao;
import by.epam.project.dao.entquery.DirectionQuery;
import by.epam.project.dao.entquery.OrderQuery;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import by.epam.project.dao.entquery.TourQuery;
import by.epam.project.dao.entquery.TouristQuery;
import by.epam.project.dao.entquery.UserQuery;
import static by.epam.project.dao.mysqldao.MysqlDao.saveDao;
import by.epam.project.dao.query.Criteria;
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
        return new UserQuery().update(bean, criteria, updateDao, mysqlConn);
    }
    
    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        Integer status = (Integer) criteria.getParam(DirectionQuery.DAO_DIRECTION_STATUS);
        Boolean allStatus = (Boolean) criteria.getParam(DirectionQuery.DAO_DIRECTION_ALLSTATUS);
        if (allStatus != null && allStatus) {
            criteria.remuveParam(DirectionQuery.DAO_DIRECTION_STATUS);
        } else if (status == null) {
            criteria.addParam(DirectionQuery.DAO_DIRECTION_STATUS, 1);
        }
        
        return new DirectionQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        Integer status = (Integer) criteria.getParam(TourQuery.DAO_TOUR_STATUS);
        Boolean allStatus = (Boolean) criteria.getParam(TourQuery.DAO_TOUR_ALLSTATUS);
        if (allStatus != null && allStatus) {
            criteria.remuveParam(TourQuery.DAO_TOUR_STATUS);
        } else if (status == null) {
            criteria.addParam(TourQuery.DAO_TOUR_STATUS, 1);
        }
        Date dateFrom = (Date) criteria.getParam(DAO_TOUR_DATE_FROM);
        Date dateTo = (Date) criteria.getParam(DAO_TOUR_DATE_TO);
        Boolean allDate = (Boolean) criteria.getParam(TourQuery.DAO_TOUR_ALLDATE);
        if (allDate != null && allDate) {
            criteria.remuveParam(DAO_TOUR_DATE_FROM);
            criteria.remuveParam(DAO_TOUR_DATE_TO);
        } else if (dateFrom == null && dateTo == null) {
            criteria.addParam(DAO_TOUR_DATE_FROM, new Date());
        }
        return new TourQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Integer> createNewOrder(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(OrderQuery.createBean(criteria));
        return new OrderQuery().save(list, saveDao, mysqlConn);
    }

    @Override
    public List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException {
        return new TourQuery().update(beans, criteria, updateDao, mysqlConn);
    }
    
    @Override
    public List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException {
        return new OrderQuery().update(beans, criteria, updateDao, mysqlConn);
    }
    
    @Override
    public List<Integer> createNewTourist(Criteria criteria) throws DaoException {
        List list = new ArrayList<>();
        list.add(TouristQuery.createBean(criteria));
        return new TouristQuery().save(list, saveDao, mysqlConn);
    }
    
    @Override
    public List<Order> showOrders(Criteria criteria) throws DaoException {
        return new OrderQuery().load(criteria, loadDao, mysqlConn);
    }
    
    @Override
    public List<Tourist> showTourists(Criteria criteria) throws DaoException {
        return new TouristQuery().load(criteria, loadDao, mysqlConn);
    }
}
