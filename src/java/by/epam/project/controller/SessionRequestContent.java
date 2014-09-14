/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller;

import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
public final class SessionRequestContent {
    
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    
    public SessionRequestContent(){
        this.requestAttributes = new HashMap();
        this.requestParameters = new HashMap();
        this.sessionAttributes = new HashMap();
    }
    
    public SessionRequestContent(HttpServletRequest request){
        super();
        extractValues(request);
    }
    
    // метод извлечения информации из запроса
    private void extractValues(HttpServletRequest request) {
        for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements();) {
            String attrName = e.nextElement();
            this.requestAttributes.put(attrName, request.getAttribute(attrName));
        }
        
        this.requestParameters.putAll(request.getParameterMap());
        
        for (Enumeration<String> e = request.getSession().getAttributeNames(); e.hasMoreElements();) {
            String attrName = e.nextElement();
            this.sessionAttributes.put(attrName, request.getAttribute(attrName));
        }

    }
    
    public String getParameter(String name){
        
        String[] params = this.requestParameters.get(name);
        if (null == params || params.length < 1) {
            return null;
        } else {
            return params[0];
        }
        
    }
    
//    // метод добавления в запрос данных для передачи в jsp
//    public void insertAttributes(HttpServletRequest request) {
//    // реализация
//    }
//    // some methods
    
}
