
package by.epam.project.dao.query;

import java.util.List;

/**
 * Реализация паттерна QueryObject - класс-запрос, извлекающий список данных типа T.
 * @param <T> тип элементов извлекаемого списка данных
 */
public interface TypedQuery <T> {
 
    public List<T> load(Criteria criteria, GenericLoadQuery loadDao) throws QueryExecutionException; 
    public void save(List<T> beans, GenericSaveQuery saveDao) throws QueryExecutionException; 
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao) throws QueryExecutionException; 
}
