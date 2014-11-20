package by.epam.project.dao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import java.util.List;

/**
 * Common interface of DAO for users wirh role ADMIN
 * @author Helena.Grouk
 */
public interface AdminDao extends AbstractDao {

    @Override
    List<Integer> createNewDescription(Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateDescription(Criteria beans, Criteria crit) throws DaoException;
    @Override
    List<Integer> createNewCountry(Criteria criteria)throws DaoException;
    @Override
    List<Integer> updateCountry(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewCity(Criteria criteria)throws DaoException;
    @Override
    List<Integer> updateCity(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewHotel(Criteria criteria)throws DaoException;
    @Override
    List<Integer> updateHotel(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewDirection(Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewTour(Criteria criteria) throws DaoException;

}
