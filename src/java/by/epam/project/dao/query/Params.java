/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
        
        public static void append(String daoName, String dbName, Criteria criteria, List<Object> list, StringBuilder sb, String separator){
            
            Object obj = criteria.getParam(daoName);
            if (obj != null){
                if (!list.isEmpty()) {
                    sb.append(separator);
                }
                sb.append(dbName);
                sb.append(" = ? ");
                list.add(obj);
            }
        }
        
        public static void append(String daoName, String dbName, Criteria criteria, List<Object> list, StringBuilder sb, String separator, String operator){
            
            Object obj = criteria.getParam(daoName);
            if (obj != null){
                if (!list.isEmpty()) {
                    sb.append(separator);
                }
                sb.append(dbName);
                sb.append(operator);
                sb.append("? ");
                list.add(obj);
            }
        }
        
        public static void appendArr(String daoName, String dbName, Criteria criteria, List<Object> list, StringBuilder sb, String separator){
            
            Collection objArr = (Collection) criteria.getParam(daoName);
            if (objArr != null){
                objArr.stream().forEach((obj) -> {
                    if (!list.isEmpty()) {
                        sb.append(separator);
                    }
                    sb.append(dbName);
                    sb.append(" = ? ");
                    list.add(obj);
                });
            }
        }
    }
  
}
