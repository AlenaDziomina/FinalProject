/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import static by.epam.project.controller.ProjectServlet.LOCALLOG;
import by.epam.project.dao.ConnectionPool;
import by.epam.project.dao.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MysqlGenericUpdateQuery implements GenericUpdateQuery {
    
    private static final String PARAMS_IS_NULL_ERROR =
        "Query params should not be null";
    
    public MysqlGenericUpdateQuery(){}

    @Override
    public int query(String query, Object[] params) throws DaoException {
        if (params == null)
            throw new DaoException(PARAMS_IS_NULL_ERROR);
        
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        int updCount = 0;
        try {
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }            

            updCount = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            try {
                
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
                if (conn != null) {
                    ConnectionPool.returnConnection(conn);
                }
                
            } catch (SQLException ex) {
                LOCALLOG.info("Error in close connection.");
            }
        }
        
        return updCount;
    }
    
}
