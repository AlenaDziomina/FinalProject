/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.mysqlquery;

import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoSqlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
public class MysqlGenericUpdateQuery implements GenericUpdateQuery {
    
    private static final Logger LOGGER = Logger.getLogger(MysqlGenericUpdateQuery.class);
    
    private static final String PARAMS_IS_NULL_ERROR =
        "Query params should not be null";
    
    private static final String CLOSE_ERROR = "Error in close connection.";
    
    public MysqlGenericUpdateQuery(){}

    @Override
    public List<Integer> query(String query, Object[] params, Connection conn) throws DaoException {
        if (params == null) {
            throw new DaoException(PARAMS_IS_NULL_ERROR);
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> resultList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }            

            if(ps.executeUpdate()>0){
                rs = ps.getGeneratedKeys();
                while (rs.next()){
                    resultList.add(rs.getInt(1));
                }
            }
            return resultList;
        } catch (SQLException ex) {
            throw new DaoSqlException(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
               
            } catch (SQLException ex) {
                LOGGER.info(CLOSE_ERROR);
            }
        }
        
    }
    
}
