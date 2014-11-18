/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
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
