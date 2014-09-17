/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class Params {
    
    private static final String DATA_OR_MAPPER_IS_NULL_ERROR = "Parameters " +
        "list or mapper should not be null";

    private List<Object[]> paramsList = new LinkedList<>();
    
    private Params() {
        super();
    }
    
    public List<Object[]> params() {
        return paramsList;
    }
    
    /**
     * Заполнение массива параметров из списка <code>data</code> типа
     * <code>&lt;T&gt;</code>. Для преобразование элемента списка в массив
     * объектов используется трансформатор 
     * {@link ru.rt.integration.rms.services.connector.mdm.dao.Params.Mapper}
     * @param <T> тип элементов преобразуемого списка
     * @param data список, преобразованными элементами которого заполняется 
     *      массив параметров
     * @param mapper преобразователь элемента списка в массив параметров
     * @return заполненный массив параметров
     * @throws IllegalArgumentException в случае передачи <code>null</code>
     *      в качестве одного из параметров метода
     */
    public static <T> Params fill(List<T> data, Mapper<T> mapper) {
        if (data == null || mapper == null)
            throw new IllegalArgumentException(DATA_OR_MAPPER_IS_NULL_ERROR);
        Params params = new Params();
        for (T bean : data) {
            params.paramsList.add(mapper.map(bean));
        }
        return params;
    }

    
    public static interface Mapper<T> {
        public Object[] map(T bean);
    }
    
    public static interface RowMapper<T> { 
        public T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }
  
}
