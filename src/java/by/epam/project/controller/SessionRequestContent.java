/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public final class SessionRequestContent {
    
    private final HashMap<String, Object> requestAttributes = new HashMap();
    private final HashMap<String, String[]> requestParameters = new HashMap();
    private final HashMap<String, Object> sessionAttributes = new HashMap();
    private final List<String> deletedSessionAttributes = new ArrayList();
    private Locale locale;
    
    public SessionRequestContent(){
    }
    
    public SessionRequestContent(HttpServletRequest request){
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
            this.sessionAttributes.put(attrName, request.getSession().getAttribute(attrName));
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
    
    public void setAttribute(String attrName, Object attr) {
        this.requestAttributes.put(attrName, attr);
    }
    
    public void sessionInvalidate(){
        locale = (Locale) getSessionAttribute("locale");
        this.sessionAttributes.clear();
    }
    
    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) {
        
        for (Entry<String, Object> e : this.requestAttributes.entrySet()) {
            request.setAttribute(e.getKey(), e.getValue());
        }

        if (this.sessionAttributes.isEmpty()) {
            request.getSession().invalidate();
            ((HttpServletRequest)request).getSession(true).setAttribute("locale", locale);
        } else {
            HttpSession session = request.getSession();
            for (Entry<String, Object> e : this.sessionAttributes.entrySet()) {
                session.setAttribute(e.getKey(), e.getValue());
            }
            for (String attrName : this.deletedSessionAttributes) {
                session.removeAttribute(attrName);
            }
            
        }
        
    }

    public void setSessionAttribute(String attrName, Object attr) {
        this.sessionAttributes.put(attrName, attr);
    }
    
     public Object getSessionAttribute(String attrName) {
        return this.sessionAttributes.get(attrName);
    }

    public void deleteSessionAttribute(String attrName) {
        this.deletedSessionAttributes.add(attrName);
    }

    
}
