package by.epam.project.dao.mysqldao;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.UserDao;
import by.epam.project.dao.query.BeanListCreator;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryType;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.dao.query.entity.TypedQueryFactory;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import java.util.Date;
import java.util.List;

/**
 * Class of DAO objects for MySQL database and user with role USER
 * @author Helena.Grouk
 */
class MysqlUserDao extends MysqlGuestDao implements UserDao {

    protected MysqlUserDao(){}

    @Override
    public List<Integer> updateUser(Criteria bean, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.USERQUERY);
        return query.update(bean, criteria, updateGeneric, mysqlConn);
    }

    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        Short status = (Short) criteria.getParam(DAO_DIRECTION_STATUS);
        Boolean allStatus = (Boolean) criteria.getParam(DAO_DIRECTION_ALLSTATUS);
        if (allStatus != null && allStatus) {
            criteria.remuveParam(DAO_DIRECTION_STATUS);
        } else if (status == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        Short status = (Short) criteria.getParam(DAO_TOUR_STATUS);
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
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewOrder(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getOrderInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.ORDERQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURQUERY);
        return query.update(beans, criteria, updateGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.ORDERQUERY);
        return query.update(beans, criteria, updateGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewTourist(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getTouristInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURISTQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Order> showOrders(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.ORDERQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tourist> showTourists(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURISTQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
}
