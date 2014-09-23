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
    
    
    public static <T> Params fill(List<T> beans, Mapper<T> mapper) {
        if (beans == null || mapper == null)
            throw new IllegalArgumentException(DATA_OR_MAPPER_IS_NULL_ERROR);
        Params params = new Params();
        for (T bean : beans) {
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
    
    public static interface QueryMapper { 
        
        public String mapQuery();
        
        public static void append(String critName, String columnName, Criteria criteria, List<Object> list, StringBuilder sb){
            
            Object obj = criteria.getParam(critName);
            if (obj != null){
                if (!list.isEmpty()) {
                    sb.append(" and ");
                }
                sb.append(columnName);
                sb.append(" = ?");
                list.add(obj);
            }
        }
    }
  
}
