package by.epam.project.dao.mysqldao;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.GuestDao;
import by.epam.project.dao.query.BeanListCreator;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryType;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.dao.query.entity.TypedQueryFactory;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Role;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
class MysqlGuestDao extends MysqlDao implements GuestDao {
    
    protected MysqlGuestDao(){}

    @Override
    public List<Role> showRoles(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.ROLEQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<User> showUsers(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.USERQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public Integer createNewUser(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getUserInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.USERQUERY);
        List<Integer> res = query.save(list, saveGeneric, mysqlConn);
        return res.get(0);
    }

    @Override
    public List<Description> showDescriptions(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DESCRIPTIONQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<Country> showCountries(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_COUNTRY_STATUS) == null) {
            criteria.addParam(DAO_COUNTRY_STATUS, ACTIVE);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.COUNTRYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<City> showCities(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_CITY_STATUS) == null) {
            criteria.addParam(DAO_CITY_STATUS, ACTIVE);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.CITYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Hotel> showHotels(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_HOTEL_STATUS) == null) {
            criteria.addParam(DAO_HOTEL_STATUS, ACTIVE);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.HOTELQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<TourType> showTourTypes (Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURTYPEQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<TransMode> showTransModes (Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TRANSMODEQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Direction> showDirections(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_DIRECTION_STATUS) == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<LinkDirectionCountry> showLinkDirectionCountry(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCOUNTRYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
    
    @Override
    public List<LinkDirectionCity> showLinkDirectionCity(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCITYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<DirectionStayHotel> showDirectionStayHotel(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONSTAYHOTELQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tour> showTours(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_TOUR_STATUS) == null) {
            criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        }
        if (criteria.getParam(DAO_TOUR_DATE_FROM) == null && criteria.getParam(DAO_TOUR_DATE_TO) == null) {
            criteria.addParam(DAO_TOUR_DATE_FROM, new Date());
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tour> searchTours(Criteria criteria) throws DaoException {
        if (criteria.getParam(DAO_DIRECTION_STATUS) == null) {
            criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        }
        if (criteria.getParam(DAO_TOUR_STATUS) == null) {
            criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        }
        Date currDate = new Date();
        Date dateFrom = (Date) criteria.getParam(DAO_TOUR_DATE_FROM);
        if (dateFrom == null || dateFrom.before(currDate)) {
            criteria.addParam(DAO_TOUR_DATE_FROM, currDate);
        }
        Date dateTo = (Date) criteria.getParam(DAO_TOUR_DATE_TO);
        if (dateTo != null && dateTo.before(currDate)) {
            criteria.addParam(DAO_TOUR_DATE_TO, currDate);
        }
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.SEARCHQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }
   
}
