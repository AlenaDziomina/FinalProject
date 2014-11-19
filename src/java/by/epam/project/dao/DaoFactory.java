package by.epam.project.dao;

import by.epam.project.dao.mysqldao.MysqlDaoFactory;
import by.epam.project.exception.DaoInitException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Factory of DAO according to database.
 * @author Helena.Grouk
 */
public abstract class DaoFactory {

    public static AbstractDao getInstance(ClientType clientType) throws DaoInitException{
        String dbType = ConfigurationManager.getProperty("db.type").toUpperCase();
        switch (dbType) {
            case "MYSQL": return MysqlDaoFactory.getInstance(clientType);
            default: throw new DaoInitException(dbType);
        }
    }

    public static void closeConnections() {
        String dbType = ConfigurationManager.getProperty("db.type").toUpperCase();
        switch (dbType) {
            case "MYSQL": MysqlDaoFactory.closeConnections();
        }
    }
}
