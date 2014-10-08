/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import java.util.List;

/**
 *
 * @author User
 */
public interface AdminDao extends AbstractDao {
    
    @Override
    public List<Integer> createNewDescription(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateDescription(Criteria beans, Criteria crit) throws DaoException;
    @Override
    public List<Integer> createNewCountry(Criteria criteria)throws DaoException;
    @Override
    public List<Integer> updateCountry(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewCity(Criteria criteria)throws DaoException;
    @Override
    public List<Integer> updateCity(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewHotel(Criteria criteria)throws DaoException;
    @Override
    public List<Integer> updateHotel(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewDirection(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewDirectionCountryLinks(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewDirectionCityLinks(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewDirectionStayHotels(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateDirection(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateDirectionCountryLinks(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateDirectionCityLinks(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateDirectionStayHotels(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewTour(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> createNewOrder(Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    public List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException;
}
