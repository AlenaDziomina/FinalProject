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
import java.util.Collections;
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
        Tour.DateComparator comparator = new Tour.DateComparator();
        Collections.sort(tours, comparator);
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

    private void fillTours(List<Tour> tours, AbstractDao dao) throws DaoException {
        if (tours != null) {
            for (Tour tour : tours) {
                Criteria directCrit = new Criteria();
                directCrit.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                List<Direction> dir = dao.showDirections(directCrit);
                tour.setDirection(dir.get(0));

                Criteria typeCrit = new Criteria();
                typeCrit.addParam(DAO_ID_TOURTYPE, tour.getDirection().getTourType().getIdTourType());
                List<TourType> types = dao.showTourTypes(typeCrit);
                tour.getDirection().setTourType(types.get(0));

                Criteria moteCrit = new Criteria();
                moteCrit.addParam(DAO_ID_TRANSMODE, tour.getDirection().getTransMode().getIdMode());
                List<TransMode> modes = dao.showTransModes(moteCrit);
                tour.getDirection().setTransMode(modes.get(0));
            }
        }
    }

    private Integer createTour(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Integer> res =  dao.createNewTour(criteria);
        return res.get(0);
    }

    private Integer updateTour(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria beans = new Criteria();
        Integer idTour = (Integer) criteria.getParam(DAO_ID_TOUR);
        beans.addParam(DAO_ID_TOUR, idTour);
        dao.updateTour(beans, criteria);
        return idTour;
    }
}
