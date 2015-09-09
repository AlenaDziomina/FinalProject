package by.epam.project.dao.query.generic;

import by.epam.project.dao.query.TypedQuery;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 * Common interface of generator LOAD query
 * @author Helena.Grouk
 */
public interface GenericLoadQuery {
    <T> List<T> sendQuery(String query, Object[] params, int pageSize, Connection conn, TypedQuery.RowMapper<T> mapper) throws DaoException;
}
