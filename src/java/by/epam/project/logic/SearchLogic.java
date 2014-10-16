/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class SearchLogic extends AbstractLogic {
    
    @Override
    List<Tour> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Tour> tours = dao.searchTours(criteria);
        fillTours(tours, dao);
        return tours;   
    }
    
    private static void fillTours(List<Tour> tours, AbstractDao dao) throws DaoException {
        if (tours != null) {
            for (Tour tour : tours) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                List<Direction> dir = dao.showDirections(crit1);
                DirectionLogic.fillDirections(dir, dao);
                tour.setDirection(dir.get(0));
                
            }
        }
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        return null;
    }
}