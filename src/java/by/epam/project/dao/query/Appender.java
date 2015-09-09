package by.epam.project.dao.query;

import java.util.Collection;
import java.util.List;

/**
 * Class of static methods to add a valid criteria parameter to query parameter
 * list and to append its name to query string.
 * @author Helena.Grouk
 */
public class Appender {





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
