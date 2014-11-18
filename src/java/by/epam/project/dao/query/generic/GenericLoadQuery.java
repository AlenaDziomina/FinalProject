package by.epam.project.dao.query.generic;

import by.epam.project.dao.query.Params;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 * Common interface of generator LOAD query
 * @author Helena.Grouk
 */
public interface GenericLoadQuery {
    <T> List<T> query(String query, Object[] params, int pageSize, Connection conn, Params.RowMapper<T> mapper) throws DaoException;

}
