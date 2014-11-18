package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.TransMode;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author User
 */
class TransModeLogic extends AbstractLogic {

    @Override
    List<TransMode> getEntity (Criteria criteria, AbstractDao dao) throws DaoException {
        List<TransMode> modes = dao.showTransModes(criteria);
        TransMode.NameComparator comparator = new TransMode.NameComparator();
        Collections.sort(modes, comparator);
        return modes;
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
}
