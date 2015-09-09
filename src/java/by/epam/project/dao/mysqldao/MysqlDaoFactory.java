package by.epam.project.dao.mysqldao;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import by.epam.project.exception.DaoInitException;

/**
 * Factory of MySqlDao objects according to user role
 * @author Helena.Grouk
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

    public static void closeConnections() {
        MysqlConnectionPool.destroy();
    }

}
