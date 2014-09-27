/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.dao.query.Criteria;

/**
 *
 * @author User
 */
public interface AdminDao extends AbstractDao {
    
    public Integer toCreateNewCountry(Criteria criteria)throws DaoException;
    public Integer toUpdateCountry(Criteria criteria) throws DaoException;
    public Integer toCreateNewCity(Criteria criteria)throws DaoException;
    public Integer toUpdateCity(Criteria criteria) throws DaoException;
    public Integer toCreateNewHotel(Criteria criteria)throws DaoException;
    public Integer toUpdateHotel(Criteria criteria) throws DaoException;
    public Integer toEditDescription(Criteria criteria) throws DaoException;

//    public void create_tour();
//    public void delete_direction();
//    public void delete_tour();
//    public void apdate_direction();
//    public void apdate_tour();
//    public void issue_contract();
//    public void view_tourist_group();
//    public void view_unformed_contract();
//    public void view_unsold_tours();
//    public void concel_tour_booking();
    
}
