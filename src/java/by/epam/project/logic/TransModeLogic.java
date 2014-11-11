/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.TransMode;
import java.util.List;

/**
 *
 * @author User
 */
public class TransModeLogic extends AbstractLogic {
    
    @Override
    List<TransMode> getEntity (Criteria criteria, AbstractDao dao) throws DaoException {
        List<TransMode> modes = dao.showTransModes(criteria);
        return modes;   
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
