package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 * Common interface of DAO for users wirh role USER
 * @author Helena.Grouk
 */
public interface UserDao extends AbstractDao {

    @Override
    List<Integer> updateUser(Criteria bean, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewOrder(Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateTour(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> updateOrder(Criteria beans, Criteria criteria) throws DaoException;
    @Override
    List<Integer> createNewTourist(Criteria criteria) throws DaoException;
    @Override
    List<Order> findOrders(Criteria criteria) throws DaoException;
    @Override
    List<Tourist> findTourists(Criteria criteria) throws DaoException;
}
