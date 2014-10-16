/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ID_ROLE;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_EMAIL;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
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
        if (idUser == null) {
            return createUser(criteria, dao);
        } else {
            return updateUser(criteria, dao);
        }  
    }
    
    public static Integer updateUser(Criteria criteria, AbstractDao dao) throws DaoException{   
        Criteria bean = new Criteria();
        Integer idUser = (Integer) criteria.getParam(DAO_ID_USER);
        bean.addParam(DAO_ID_USER, idUser);
        dao.updateUser(bean, criteria);
        return idUser;
    }
    
    public static Integer createUser(Criteria criteria, AbstractDao dao) throws DaoException{   
        Criteria crit1 = new Criteria();
        crit1.addParam(DAO_USER_LOGIN, criteria.getParam(DAO_USER_LOGIN));
        Criteria crit2 = new Criteria();
        crit2.addParam(DAO_USER_EMAIL, criteria.getParam(DAO_USER_EMAIL));
        
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
