package by.epam.project.dao.query.generic;

import by.epam.project.dao.query.Params;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 * Common interface of generator SAVE query
 * @author Helena.Grouk
 */
public interface GenericSaveQuery {
    <T> List<Integer> query(String query, Connection conn, Params params) throws DaoException;
}
