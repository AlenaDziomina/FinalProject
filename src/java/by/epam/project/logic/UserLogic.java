package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.Collections;
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
        User.LoginComparator comparator = new User.LoginComparator();
        Collections.sort(users, comparator);
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

    private Integer createUser(Criteria criteria, AbstractDao dao) throws DaoException{
        Criteria loginCrit = new Criteria();
        loginCrit.addParam(DAO_USER_LOGIN, criteria.getParam(DAO_USER_LOGIN));
        Criteria emailCrit = new Criteria();
        emailCrit.addParam(DAO_USER_EMAIL, criteria.getParam(DAO_USER_EMAIL));
        Criteria roleCrit = new Criteria();
        roleCrit.addParam(DAO_ROLE_NAME, "user");
        Integer idRole = dao.showRoles(roleCrit).get(0).getIdRole();
        criteria.addParam(DAO_ID_ROLE, idRole);
        criteria.addParam(DAO_USER_BALANCE, 0f);
        criteria.addParam(DAO_USER_DISCOUNT, 0);
        if (dao.showUsers(loginCrit).isEmpty() && dao.showUsers(emailCrit).isEmpty()) {
            return dao.createNewUser(criteria);
        }
        return null;
    }

    private Integer updateUser(Criteria criteria, AbstractDao dao) throws DaoException{
        Criteria bean = new Criteria();
        Integer idUser = (Integer) criteria.getParam(DAO_ID_USER);
        bean.addParam(DAO_ID_USER, idUser);
        dao.updateUser(bean, criteria);
        return idUser;
    }

    private void fillUsers(List<User> users, AbstractDao dao) throws DaoException {
        for (User u : users) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_ROLE, u.getRole().getIdRole());
            u.setRole(dao.showRoles(crit).get(0));
        }
    }
}
