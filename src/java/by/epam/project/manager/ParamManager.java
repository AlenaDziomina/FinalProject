/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

/**
 *
 * @author User
 */
public class ParamManager {
    public static Integer getIntParam(SessionRequestContent request, String name){
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            return currParam;
        }
        return null;
    }
    
    public static Date getDateParam(SessionRequestContent request, String name){
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date currDate = formatter.parse(param);
                return currDate;
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }
    
    public static Boolean getBoolParam(SessionRequestContent request, String name) {
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
            Boolean currParam = Boolean.parseBoolean(param);
            return currParam;
        }
        return null;
    }
    
    public static Float getFltParam(SessionRequestContent request, String name){
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
           Float currParam = Float.parseFloat(param);
            return currParam;
        }
        return null;
    }
    
    public static void checkIntParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        
        String param = (String) request.getParameter(reqName);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(critName, currParam);
            }
        }
    }
    
    public static void checkFltParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        
        String param = (String) request.getParameter(reqName);
        if (param != null && !param.isEmpty()) {
            Float currParam = Float.parseFloat(param);
            if (currParam > 0) {
                criteria.addParam(critName, currParam);
            }
        }
    }
    
    public static void checkDatParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        
        String param = (String) request.getParameter(reqName);
        if (param != null && !param.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date currDate = formatter.parse(param);
                criteria.addParam(critName, currDate);
            } catch (ParseException ex) {}
        }
    }
    
    public static void checkArrParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        String[] arr = (String[]) request.getAllParameters(reqName);
        Collection<Integer> set = new HashSet();
        if (arr != null && arr.length > 0) {
            for (String param : arr) {
                if (param != null && !param.isEmpty()) {
                    Integer currParam = Integer.decode(param);
                    if (currParam > 0) {
                        set.add(currParam);
                    }
                }
            }
        }
        criteria.addParam(critName, set);
    }
    
    public static Integer getDateDiff(Date d1, Date d2) {
        Date diff = new Date(d2.getTime() - d1.getTime());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(diff);
        return calendar.get(Calendar.DAY_OF_YEAR);        
    }
    
}
