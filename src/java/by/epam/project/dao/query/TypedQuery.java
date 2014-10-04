
package by.epam.project.dao.query;

import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.util.List;

/**
 * Реализация паттерна QueryObject - класс-запрос, извлекающий список данных типа T.
 * @param <T> тип элементов извлекаемого списка данных
 */
public interface TypedQuery <T> {
 
    public List<T> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException; 
    public List<Integer> save(List<T> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException; 
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException; 
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException;
}
