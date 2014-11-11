/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.TouristQuery.DAO_ID_TOURIST;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class TouristLogic extends AbstractLogic {

    @Override
    List getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private Integer updateTourist(Criteria criteria, AbstractDao dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Integer createTourist(Criteria criteria, AbstractDao dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
