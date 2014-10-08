/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class TourLogic {
    
    public static List<Tour> getTours(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            List<Tour> tours = dao.showTours(criteria);
            fillTours(tours, dao);
            return tours;   
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }

    private static void fillTours(List<Tour> tours, AbstractDao dao) throws DaoException {
        if (tours != null) {
            for (Tour tour : tours) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                List<Direction> dir = dao.showDirections(crit1);
                tour.setDirection(dir.get(0));
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_TOUR, tour.getIdTour());
                List<Order> orders = dao.showOrders(crit2);
                tour.setOrderCollection(orders);
                
            }
        }
    }
    
    public static Integer redactTour(Criteria criteria) throws DaoException {
        
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idTour = (Integer) criteria.getParam(DAO_ID_TOUR);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {   
            if (idTour == null) {      
                return createTour(criteria, dao);
            } else {
                return updateTour(criteria, dao);
            } 
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }

    private static Integer createTour(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Integer> res =  dao.createNewTour(criteria);   
        return res.get(0);
    }
    
    private static Integer updateTour(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria beans = new Criteria();
        Integer idTour = (Integer) criteria.getParam(DAO_ID_TOUR);
        beans.addParam(DAO_ID_TOUR, idTour);
        dao.updateTour(beans, criteria);
        return idTour;
    }
}
