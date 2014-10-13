/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
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
public class SearchLogic {
    
    public static List<Tour> getTours(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            List<Tour> tours = dao.searchTours(criteria);
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
                
            }
        }
    }
}