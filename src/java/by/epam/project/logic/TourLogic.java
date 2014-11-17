/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
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
                
//                Criteria crit2 = new Criteria();
//                crit2.addParam(DAO_ID_TOUR, tour.getIdTour());
//                List<Order> orders = dao.showOrders(crit2);
//                tour.setOrderCollection(orders);
                
                Criteria crit3 = new Criteria();
                crit3.addParam(DAO_ID_TOURTYPE, tour.getDirection().getTourType().getIdTourType());
                List<TourType> types = dao.showTourTypes(crit3);
                tour.getDirection().setTourType(types.get(0));
                
                Criteria crit4 = new Criteria();
                crit4.addParam(DAO_ID_TRANSMODE, tour.getDirection().getTransMode().getIdMode());
                List<TransMode> modes = dao.showTransModes(crit4);
                tour.getDirection().setTransMode(modes.get(0));
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
    
    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_TOUR_STATUS, DELETED);
        Integer res = updateTour(criteria, dao);
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_TOUR_STATUS, ACTIVE);
        Integer res = updateTour(criteria, dao);
        return res;
    }
}
