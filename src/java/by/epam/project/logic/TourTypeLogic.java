/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.TourType;
import java.util.List;

/**
 *
 * @author User
 */
public class TourTypeLogic extends AbstractLogic {
    
    @Override
    List<TourType> getEntity (Criteria criteria, AbstractDao dao) throws DaoException {
        List<TourType> types = dao.showTourTypes(criteria);
        return types;   
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        return null;
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
