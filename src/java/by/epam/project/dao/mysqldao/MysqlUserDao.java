/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.UserDao;
import by.epam.project.dao.entquery.OrderQuery;
import by.epam.project.dao.entquery.TourQuery;
import by.epam.project.dao.entquery.TouristQuery;
import by.epam.project.dao.entquery.UserQuery;
import static by.epam.project.dao.mysqldao.MysqlDao.saveDao;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
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
}
