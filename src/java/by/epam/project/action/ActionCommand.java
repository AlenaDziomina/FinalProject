/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoUserLogicException;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author User
 */
@FunctionalInterface
public interface ActionCommand {
    
    String execute(SessionRequestContent request) throws DaoUserLogicException;
    
    public static void checkParam(SessionRequestContent request, Criteria criteria, String name){
        
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(name, currParam);
            }
        }
    }
    
    public static void checkParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        
        String param = (String) request.getParameter(reqName);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(critName, currParam);
            }
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
}
