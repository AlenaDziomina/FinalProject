
package by.epam.project.dao.query;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.util.List;

/**
 * @author Helena.Grouk
 * @param <T> тип элементов извлекаемого списка данных
 */
public interface TypedQuery <T> {

    List<T> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException;
    List<Integer> save(List<T> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException;
    List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException;
    List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException;
}
