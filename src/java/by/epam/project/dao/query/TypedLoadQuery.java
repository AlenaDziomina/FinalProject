
package by.epam.project.dao.query;

import java.util.List;

/**
 * Реализация паттерна QueryObject - класс-запрос, извлекающий список данных типа T.
 * @param <T> тип элементов извлекаемого списка данных
 */
public interface TypedLoadQuery <T> {
 
    /**
     * Загрузка списка данных типа T, удовлетворяющих критерию criteria
     * @param criteria критерий выбора данных
     * @return список данных типа T для филиала billingId
     * @throws QueryExecutionException при невозможности извлечь данные из хранилища
     */
    public List<T> load(Criteria criteria) throws QueryExecutionException; 
}
