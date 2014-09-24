/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import by.epam.project.dao.ConnectionPool;
import by.epam.project.dao.DaoException;
import static by.epam.project.controller.ProjectServlet.LOCALLOG;
import by.epam.project.dao.query.Params.RowMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MysqlGenericLoadQuery implements GenericLoadQuery {
    
    private static final String PARAMS_IS_NULL_ERROR =
        "Query params should not be null";

    private static final String DATASOURCE_IS_NULL =
        "The MDM DataSource is null, may be connection is not established or broken";

    
    public MysqlGenericLoadQuery() {
        super();        
    }

   
    @Override
    public <T> List<T> query(String query, Object[] params, int pageSize, RowMapper<T> mapper) throws DaoException {
        
        if (params == null)
            throw new DaoException(PARAMS_IS_NULL_ERROR);
        
        List<T> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setFetchSize(pageSize);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }            
            rs = ps.executeQuery();
            int i = 0;  
            while(rs.next()) {
                result.add(mapper.mapRow(rs, i++));                    
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
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
        
        return result;
    
        }
}
