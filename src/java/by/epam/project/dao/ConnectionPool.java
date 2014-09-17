
package by.epam.project.dao;

import static by.epam.project.controller.ProjectServlet.LOCALLOG;
import by.epam.project.manager.ConfigurationManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class ConnectionPool {
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
            LOCALLOG.error("Cannot set connection.");
        }
    }
    
    private ConnectionPool() { }
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
    
    public static void returnConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
