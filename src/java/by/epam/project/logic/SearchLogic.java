package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author User
 */
class SearchLogic extends AbstractLogic {

    @Override
    List<Tour> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Tour> tours = dao.searchTours(criteria);
        fillTours(tours, dao);
        Tour.DateComparator comparator = new Tour.DateComparator();
        Collections.sort(tours, comparator);
        return tours;
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }


    private void fillTours(List<Tour> tours, AbstractDao dao) throws DaoException {
        if (tours != null) {
            for (Tour tour : tours) {
                Criteria directCrit = new Criteria();
                directCrit.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
                List<Direction> dir = dao.showDirections(directCrit);
                if (!dir.isEmpty()) {
                    new DirectionLogic().fillDirections(dir, dao);
                    tour.setDirection(dir.get(0));
                }
            }
        }
    }
}