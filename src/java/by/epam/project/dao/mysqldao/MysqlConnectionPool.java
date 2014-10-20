/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao;

import by.epam.project.exception.DaoConnectException;
import by.epam.project.manager.ConfigurationManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
class MysqlConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(MysqlConnectionPool.class);
    private static final String DB_USER_NAME;
    private static final  String DB_PASSWORD ;
    private static final  String DB_URL;
    private static final  String DB_DRIVER;
    private static final  Integer DB_MAX_CONNECTIONS;
    
    static {
        DB_USER_NAME = ConfigurationManager.getProperty("db.username");
        DB_PASSWORD = ConfigurationManager.getProperty("db.password");
        DB_URL = ConfigurationManager.getProperty("db.url");
        DB_DRIVER = ConfigurationManager.getProperty("db.driver");
        DB_MAX_CONNECTIONS = Integer.decode(ConfigurationManager.getProperty("db.maxconnect"));
    }
    
    private static MysqlConnectionPool pool;
    private static BlockingQueue<ProxyConnection> queue;
    private static final Lock LOCK = new ReentrantLock();
    
    private MysqlConnectionPool() throws DaoConnectException{
        if (DB_MAX_CONNECTIONS == null || DB_MAX_CONNECTIONS <= 0) {
            throw new DaoConnectException();
        }
        
        queue = new ArrayBlockingQueue<>(DB_MAX_CONNECTIONS);
        for (int i = 0; i < DB_MAX_CONNECTIONS; i++) {
            queue.offer(createNewConnection());
        }
    }

       
    private ProxyConnection createNewConnection() throws DaoConnectException{
        ProxyConnection conn = null;
        try {
            Class.forName(DB_DRIVER);
            Connection connection = (Connection) DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            connection.setAutoCommit(false);
            conn = new ProxyConnection(connection);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DaoConnectException(ex);
        }
        return conn;
    }
    
    private static void checkPool() throws DaoConnectException {
        if (pool == null) {
            LOCK.lock();
            try {
                if (pool == null) {
                    pool = new MysqlConnectionPool();
                }
            } finally {
                LOCK.unlock();
            }
        }
    }
    
    static Connection getConnection() throws DaoConnectException {
        checkPool();
        
        Connection conn = null;
        try {
            conn = queue.take();
        } catch (InterruptedException ex) {
            throw new DaoConnectException(ex);
        }
       
        return conn;
    }
    
    static void returnConnection(Connection conn) throws DaoConnectException {
        try {
            if (conn == null || !conn.isValid(10000)) {
                throw new DaoConnectException();
            }
        } catch (SQLException ex) {
            throw new DaoConnectException(ex);
        }
        
        try {
            queue.offer((ProxyConnection) conn);
        } catch (Exception ex) {
            throw new DaoConnectException(ex);
        }
    }
    
    
}
