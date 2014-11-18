package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
class TouristLogic extends AbstractLogic {

    @Override
    List getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idOrder = (Integer) criteria.getParam(DAO_ID_TOURIST);
        if (idOrder != null) {
            return updateTourist(criteria, dao);
        } else {
            return createTourist(criteria, dao);
        }
    }

    private Integer updateTourist(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new DaoException("Not supported.");
    }

    private Integer createTourist(Criteria criteria, AbstractDao dao) throws DaoException {
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

}
