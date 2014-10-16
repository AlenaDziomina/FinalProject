/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AbstractDao;
import by.epam.project.entity.ClientType;
import by.epam.project.dao.DaoFactory;
import by.epam.project.exception.DaoInitException;

/**
 *
 * @author User
 */
public class MysqlDaoFactory extends DaoFactory {

    public static AbstractDao getInstance(ClientType type) throws DaoInitException {
        switch(type) {
            case GUEST: return new MysqlGuestDao();
            case USER: return new MysqlUserDao();
            case ADMIN: return new MysqlAdminDao();
            default: throw new DaoInitException(type.toString());
        }
    }
    
}
