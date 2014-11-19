package by.epam.project.dao.mysqldao.querygeneric;

import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.TypedQuery.RowMapper;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoSqlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Generator of messages to MySQL database of specified LOAD content.
 * @author Helena.Grouk
 */
class MysqlGenericLoadQuery implements GenericLoadQuery {
    private static final Logger LOGGER = Logger.getLogger(MysqlGenericLoadQuery.class);
    private static final String PARAMS_IS_NULL_ERROR = "Query params should not be null";
    private static final String CLOSE_ERROR = "Error in close connection.";

    @Override
    public <T> List<T> sendQuery(String query, Object[] params, int pageSize, Connection conn, RowMapper<T> mapper) throws DaoException {
        if (params == null) {
            throw new DaoException(PARAMS_IS_NULL_ERROR);
        }

        List<T> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
        return result;
    }
}
