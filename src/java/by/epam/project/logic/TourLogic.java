/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class TourLogic extends AbstractLogic {
    
    @Override
    List<Tour> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Tour> tours = dao.showTours(criteria);
        fillTours(tours, dao);
        return tours;   
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idTour = (Integer) criteria.getParam(DAO_ID_TOUR);
        if (idTour != null) {
            return updateTour(criteria, dao);
        } else {      
            return createTour(criteria, dao);
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
