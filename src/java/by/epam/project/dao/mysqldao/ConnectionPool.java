
package by.epam.project.dao.mysqldao;

import by.epam.project.manager.ConfigurationManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
class ConnectionPool {
    
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    
    private static final String DATASOURCE_NAME;
    static {
        DATASOURCE_NAME = ConfigurationManager.getProperty("db.name");
    }
    
    private static DataSource dataSource;
    
    static {
        try {
            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            dataSource = (DataSource) envCtx.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            LOGGER.error("Cannot set connection.");
        }
    }
    
    private ConnectionPool() { }
    static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
    
    static void returnConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
