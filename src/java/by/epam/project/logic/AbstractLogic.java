/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.exception.DaoAccessException;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoInitException;
import by.epam.project.exception.DaoLogicException;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.manager.MessageManager;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
public abstract class AbstractLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AbstractLogic.class);
    abstract List getEntity(Criteria criteria, AbstractDao dao) throws DaoException;
    abstract Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException;
    
    public List doGetEntity(Criteria criteria) throws TechnicalException {
        AbstractDao dao = null;
        try {
            ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
            dao = DaoFactory.getInstance(role);
            dao.open();
            List res = getEntity(criteria, dao);
            dao.commit();
            return res;   
        } catch (DaoAccessException ex) {
            throw new TechnicalException( MessageManager.getProperty("message.errordaoaccess"), ex);
        } catch (DaoConnectException | DaoInitException ex) {
            throw new TechnicalException(MessageManager.getProperty("message.errordaoconnect"), ex);
        } catch (DaoQueryException ex) {
            try {
                if (dao != null) {
                    dao.rollback();
                }
            } catch (DaoException ex1) {
                LOGGER.error(ex1.getMessage());
            }
            throw new TechnicalException(MessageManager.getProperty("message.errordaoquery"), ex);
        } catch (DaoException ex){
            try {
                if (dao != null) {
                    dao.rollback();
                }
            } catch (DaoException ex1) {
                LOGGER.error(ex1.getMessage());
            }
            throw new TechnicalException(MessageManager.getProperty("message.daoerror"), ex);
        } finally {
            try {
                if (dao != null) {
                    dao.close();
                }
            } catch (DaoException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
    
    public Integer doRedactEntity(Criteria criteria) throws TechnicalException, LogicException {
        AbstractDao dao = null;
        try {
            ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Integer res = redactEntity(criteria, dao);
            dao.commit();
            return res;
        } catch (DaoAccessException ex) {
            throw new TechnicalException( MessageManager.getProperty("message.errordaoaccess"), ex);
        } catch (DaoConnectException | DaoInitException ex) {
            throw new TechnicalException(MessageManager.getProperty("message.errordaoconnect"), ex);
        } catch (DaoQueryException ex) {
            try {
                if (dao != null) {
                    dao.rollback();
                }
            } catch (DaoException ex1) {
                LOGGER.error(ex1.getMessage());
            }
            throw new TechnicalException(MessageManager.getProperty("message.errordaoquery"), ex);
        } catch (DaoLogicException ex) {
            try {
                if (dao != null) {
                    dao.rollback();
                }
            } catch (DaoException ex1) {
                LOGGER.error(ex1.getMessage());
            }
            throw new LogicException(MessageManager.getProperty(ex.getMessage()));
        } catch (DaoException ex){
            try {
                if (dao != null) {
                    dao.rollback();
                }
            } catch (DaoException ex1) {
                LOGGER.error(ex1.getMessage());
            }
            throw new TechnicalException(ex);
        } finally {
            try {
                if (dao != null) {
                    dao.close();
                }
            } catch (DaoException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
    
    
}
