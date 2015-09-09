package by.epam.project.dao.query.generic;

import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 * Common interface of generator DELETE query
 * @author Helena.Grouk
 */
public interface GenericDeleteQuery {
    <T> List<Integer> sendQuery(String query, Object[] params, Connection conn) throws DaoException;
}
