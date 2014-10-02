/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;

/**
 *
 * @author User
 */
public interface AdminDao extends AbstractDao {
    
    @Override
    public Integer toCreateNewCountry(Criteria criteria)throws DaoException;
    @Override
    public Integer toUpdateCountry(Criteria criteria) throws DaoException;
    @Override
    public Integer toCreateNewCity(Criteria criteria)throws DaoException;
    @Override
    public Integer toUpdateCity(Criteria criteria) throws DaoException;
    @Override
    public Integer toCreateNewHotel(Criteria criteria)throws DaoException;
    @Override
    public Integer toUpdateHotel(Criteria criteria) throws DaoException;
    @Override
    public Integer toEditDescription(Criteria criteria) throws DaoException;
    @Override
    public Integer toCreateNewDirection(Criteria criteria) throws DaoException;
    
}
