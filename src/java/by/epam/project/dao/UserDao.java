/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
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
    List<Order> showOrders(Criteria criteria) throws DaoException;
    @Override
    List<Tourist> showTourists(Criteria criteria) throws DaoException;
}
