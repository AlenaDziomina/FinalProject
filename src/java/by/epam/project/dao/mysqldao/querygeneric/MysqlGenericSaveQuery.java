package by.epam.project.dao.mysqldao.querygeneric;

import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.Params;
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
 * Generator of messages to MySQL database of specified SAVE content.
 * @author Helena.Grouk
 */
class MysqlGenericSaveQuery implements GenericSaveQuery {
    private static final Logger LOGGER = Logger.getLogger(MysqlGenericSaveQuery.class);
    private static final String PARAMS_IS_NULL_ERROR = "Query params should not be null";
    private static final String CLOSE_ERROR = "Error in close connection.";

    @Override
    public  <T> List<Integer> query(String query, Connection conn, Params params) throws DaoException {
        if (params == null) {
            throw new DaoException(PARAMS_IS_NULL_ERROR);
        }
        List<Integer> resultList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (Object[] paramarray : params.params()) {
                for (int i = 0; i < paramarray.length; i++) {
                    ps.setObject(i + 1, paramarray[i]);
                }
                if(ps.executeUpdate()>0){
                    rs = ps.getGeneratedKeys();
                    while (rs.next()){
                        resultList.add(rs.getInt(1));
                    }
                }
                ps.clearParameters();
            }
            return resultList;
        }
        catch (SQLException ex) {
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
