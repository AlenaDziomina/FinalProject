package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AdminDao;
import by.epam.project.dao.query.BeanListCreator;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryType;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.dao.query.entity.TypedQueryFactory;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 * Class of DAO objects for MySQL database and user with role ADMIN
 * @author Helena.Grouk
 */
class MysqlAdminDao extends MysqlUserDao implements AdminDao {

    protected MysqlAdminDao(){}

    @Override
    public List<Country> findCountries(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.COUNTRYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<City> findCities(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.CITYQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Hotel> findHotels(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.HOTELQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Direction> findDirections(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tour> findTours(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Tour> searchTours(Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.SEARCHQUERY);
        return query.load(criteria, loadGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewDescription(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getDescriptionInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DESCRIPTIONQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }
    @Override
    public List<Integer> updateDescription(Criteria beans, Criteria crit) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DESCRIPTIONQUERY);
        return query.update(beans, crit, updateGeneric, mysqlConn);
    }

    @Override
    public List createNewCountry(Criteria criteria)throws DaoException {
        List list = BeanListCreator.getCountryInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.COUNTRYQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateCountry(Criteria beans, Criteria crit) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.COUNTRYQUERY);
        return query.update(beans, crit, updateGeneric, mysqlConn);
    }

    @Override
    public List createNewCity(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getCityInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.CITYQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateCity(Criteria beans, Criteria crit) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.CITYQUERY);
        return query.update(beans, crit, updateGeneric, mysqlConn);
    }

    @Override
    public List createNewHotel(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getHotelInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.HOTELQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateHotel(Criteria beans, Criteria crit) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.HOTELQUERY);
        return query.update(beans, crit, updateGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirection(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getDirectionInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException {
        List linkList = BeanListCreator.getLinkCountryInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCOUNTRYQUERY);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException {
        List linkList = BeanListCreator.getLinkCityInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCITYQUERY);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException {
        List linkList = BeanListCreator.getStayHotelInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONSTAYHOTELQUERY);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONQUERY);
        return query.update(beans, criteria, updateGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCOUNTRYQUERY);
        query.delete(beans, deleteGeneric, mysqlConn);
        List linkList = BeanListCreator.getLinkCountryInstances(criteria);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONCITYQUERY);
        query.delete(beans, deleteGeneric, mysqlConn);
        List linkList = BeanListCreator.getLinkCityInstances(criteria);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException {
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.DIRECTIONSTAYHOTELQUERY);
        query.delete(beans, deleteGeneric, mysqlConn);
        List linkList = BeanListCreator.getStayHotelInstances(criteria);
        return query.save(linkList, saveGeneric, mysqlConn);
    }

    @Override
    public List<Integer> createNewTour(Criteria criteria) throws DaoException {
        List list = BeanListCreator.getTourInstances(criteria);
        TypedQuery query = TypedQueryFactory.getInctance(QueryType.TOURQUERY);
        return query.save(list, saveGeneric, mysqlConn);
    }

    

}
