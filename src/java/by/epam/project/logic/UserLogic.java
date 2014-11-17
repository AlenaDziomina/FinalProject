/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class UserLogic extends AbstractLogic {
    
    @Override
    List getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<User> users = dao.showUsers(criteria);
        fillUsers(users, dao); 
        return users;
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idUser = (Integer) criteria.getParam(DAO_ID_USER);
        if (idUser != null) {
            return updateUser(criteria, dao);
        } else {
            return createUser(criteria, dao);
        }  
    }
    
    public static Integer updateUser(Criteria criteria, AbstractDao dao) throws DaoException{   
        Criteria bean = new Criteria();
        Integer idUser = (Integer) criteria.getParam(DAO_ID_USER);
        bean.addParam(DAO_ID_USER, idUser);
        dao.updateUser(bean, criteria);
        return idUser;
    }
    
    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_USER_STATUS, DELETED);
        return updateUser(criteria, dao);
    }
    
    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_USER_STATUS, ACTIVE);
        return updateUser(criteria, dao);
    }
    
    public static Integer createUser(Criteria criteria, AbstractDao dao) throws DaoException{   
        Criteria crit1 = new Criteria();
        crit1.addParam(DAO_USER_LOGIN, criteria.getParam(DAO_USER_LOGIN));
        Criteria crit2 = new Criteria();
        crit2.addParam(DAO_USER_EMAIL, criteria.getParam(DAO_USER_EMAIL));
        Criteria crit3 = new Criteria();
        crit3.addParam(DAO_ROLE_NAME, "user");
        Integer idRole = dao.showRoles(crit3).get(0).getIdRole();
        criteria.addParam(DAO_ID_ROLE, idRole);
        criteria.addParam(DAO_USER_BALANCE, 0f);
        criteria.addParam(DAO_USER_DISCOUNT, 0);
        if (dao.showUsers(crit1).isEmpty() && dao.showUsers(crit2).isEmpty()) {
            return dao.createNewUser(criteria);
        } 
        return null;
    }
    
    private static void fillUsers(List<User> users, AbstractDao dao) throws DaoException {
        for (User u : users) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_ROLE, u.getRole().getIdRole());
            u.setRole(dao.showRoles(crit).get(0));
        }
    }

    

    
    
}
