
package by.epam.project.dao.query;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Common interface of all guery formators.
 * @author Helena.Grouk
 * @param <T> тип элементов извлекаемого списка данных
 */
public interface TypedQuery <T> {

    List<T> load(Criteria criteria, GenericLoadQuery loadGeneric, Connection conn) throws DaoQueryException;
    List<Integer> save(List<T> beans, GenericSaveQuery saveGeneric, Connection conn) throws DaoQueryException;
    List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateGeneric, Connection conn) throws DaoQueryException;
    List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteGeneric, Connection conn) throws DaoQueryException;

    /**
     * Functional interface of processing of the query result in entity.
     * @param <T> type of entity
     */
    static interface RowMapper<T> {
        T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    /**
     * Functional interface of processing criteria in query
     */
    static interface QueryMapper {
        String mapQuery();
    }
}
