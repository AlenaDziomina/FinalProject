/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import static by.epam.project.dao.MysqlDao.saveDao;
import by.epam.project.dao.entquery.CountryQuery;
import by.epam.project.dao.entquery.DescriptionQuery;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlAdminDao extends MysqlUserDao implements MysqlDao,  AdminDao {
    
    private final Connection mysqlConn;
    
    protected MysqlAdminDao() throws DaoException{
        mysqlConn = MysqlDao.getConnection();
    }
    
    @Override
    public void close() throws DaoException {
        MysqlDao.returnConnection(mysqlConn);
    }
    
    @Override
    public Integer toEditDescription(Criteria criteria) throws DaoException {
        Description desc = new Description(criteria);
        
        try {
            if (desc.getIdDescription() == null) {
                if (desc.getText() == null || desc.getText().isEmpty()) {
                    return null;
                } else {
                    List list = new ArrayList<>();
                    list.add(desc);
                    List<Integer> res = new DescriptionQuery().save(list, saveDao, mysqlConn);
                    if (res == null || res.isEmpty()) {
                        throw new DaoException("Error in description query.");
                    } else {
                        return res.get(0);
                    }
                }
            } else {
                Criteria beans = new Criteria();
                beans.addParam(PARAM_NAME_TEXT_DESCRIPTION, desc.getText());
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_ID_DESCRIPTION, desc.getIdDescription());
                new DescriptionQuery().update(beans, crit, updateDao, mysqlConn);
                return desc.getIdDescription();
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in description query.");
        }
        
    }
    
    @Override
    public Integer toCreateNewCountry(Criteria criteria)throws DaoException {

        try {
            Integer idDescription = toEditDescription(criteria);
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            List list = new ArrayList<>();
            list.add(new Country(criteria));
            List<Integer> res = new CountryQuery().save(list, saveDao, mysqlConn);
            if (res == null || res.isEmpty()) {
                throw new DaoException("Error in country query.");
            } else {
                return res.get(0);
            }
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
        
    }

    @Override
    public Integer toUpdateCountry(Criteria criteria) throws DaoException {
        try {
            Integer idDescription = toEditDescription(criteria);
            Integer idCountry = (Integer) criteria.getParam(PARAM_NAME_ID_COUNTRY);
            Criteria beans = new Criteria();
            beans.addParam(PARAM_NAME_NAME_COUNTRY, criteria.getParam(PARAM_NAME_NAME_COUNTRY));
            beans.addParam(PARAM_NAME_PICTURE_COUNTRY, criteria.getParam(PARAM_NAME_PICTURE_COUNTRY));
            beans.addParam(PARAM_NAME_STATUS_COUNTRY, criteria.getParam(PARAM_NAME_STATUS_COUNTRY));
            beans.addParam(PARAM_NAME_ID_DESCRIPTION, idDescription);
            Criteria crit = new Criteria();
            crit.addParam(PARAM_NAME_ID_COUNTRY, idCountry);
            new CountryQuery().update(beans, crit, updateDao, mysqlConn);
            return idCountry;
        } catch (QueryExecutionException ex) {
            throw new DaoException("Error in query.");
        }
    }

}
