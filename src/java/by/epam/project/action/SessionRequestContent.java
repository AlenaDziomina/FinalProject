/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Object of {@code SessionRequestContent} contains all parameters and
 * attributes of request and session.
 * @author Helena.Grouk
 */
public class SessionRequestContent {
    
    private HashMap<String, Object> requestAttributes = new HashMap();
    private HashMap<String, String[]> requestParameters = new HashMap();
    private HashMap<String, Object> sessionAttributes = new HashMap();
    private List<String> deletedSessionAttributes = new ArrayList();
    private Locale locale;
    
    /**
     * Allocates a new {@code SessionRequestContent} object.
     */
    public SessionRequestContent(){
    }
    
    /**
     * Allocates a new {@code SessionRequestContent} object.
     * @param request parameter based on which the object is formed
     */
    public SessionRequestContent(HttpServletRequest request){
        extractValues(request);
    }
    
    /**
     * Extract information from the request
     * @param request 
     */
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
    
    /**
     * Get parameter of request
     * @param name name of parameter(case-sensitive)
     * @return a string representation of the parameter
     */
    public String getParameter(String name){
        String[] params = this.requestParameters.get(name);
        if (null != params && params.length >= 1) {
            return params[0];
        } else {
            return null;
        }
    }
    
    /**
     * Set parameter. If parameter is olready exists - replace old parameter 
     * by new value.
     * @param paramName name of setting parameter
     * @param param string representation of the parameter
     */
    public void setParameter(String paramName, String param){
        String[] prms = requestParameters.get(paramName);
        if (prms != null) {
            List<String> list = new ArrayList<>(Arrays.asList(prms));
            list.add(param);
            requestParameters.replace(paramName, (String[]) list.toArray());
        } else {
            prms = new String[]{param};
            requestParameters.put(paramName, prms);
        }
    }
    
    /**
     * Get array of parameters.
     * @param name name of parameter
     * @return string array representation of the parameter
     */
    public String[] getAllParameters(String name){
        String[] params = this.requestParameters.get(name);
        if (null != params && params.length >= 1) {
            return params;
        } else {
            return null;
        }
    }
    
    /**
     * Get attribute of request
     * @param attrName attribute name
     * @return object of attribute
     */
    public Object getAttribute(String attrName) {
        return this.requestAttributes.get(attrName);
    }
    
    /**
     * Set attribute. If attribute is olready exists - replace old attribute 
     * by new value.
     * @param attrName attribute name
     * @param attr attribute object
     */
    public void setAttribute(String attrName, Object attr) {
        if (this.requestAttributes.containsKey(attrName)){
            this.requestAttributes.replace(attrName, attr);
        } else {
            this.requestAttributes.put(attrName, attr);
        }
    }
    
    /**
     * Get session attribute.
     * @param attrName attribute name
     * @return attribute object
     */
    public Object getSessionAttribute(String attrName) {
        return this.sessionAttributes.get(attrName);
    }
    
    /**
     * Set session attribute. If attribute is olready exists - replace old 
     * attribute by new value.
     * @param attrName attribute name
     * @param attr attribute object
     */
    public void setSessionAttribute(String attrName, Object attr) {
        if (this.sessionAttributes.containsKey(attrName)) {
            this.sessionAttributes.replace(attrName, attr);
        } else {
            this.sessionAttributes.put(attrName, attr);
        }
    }

    /**
     * Remove session attribute in list of deleted attributes for later deletion
     * @param attrName attribute name
     */
    public void deleteSessionAttribute(String attrName) {
        this.sessionAttributes.remove(attrName);
        this.deletedSessionAttributes.add(attrName);
    }
      
    /**
     * Cleaning of all parameters except the session locale.
     */
    public void sessionInvalidate(){
        locale = (Locale) getSessionAttribute("locale");
        this.sessionAttributes.clear();
    }
    
   /**
    * Reload parameters and attributes from {@code SessionRequestContent} 
    * to {@code HttpServletRequest} request for subsequent transmission 
    * to the jsp page.
    * If list of session attributes is empty - session of request invaledated
    * and set only old session locale.
    * Attributes of deleting list are removed from request.
    * @param request mutable object
    */
    public void insertAttributes(HttpServletRequest request) {
        this.requestAttributes.entrySet().stream().forEach((e) -> {
            request.setAttribute(e.getKey(), e.getValue());
        });
        if (!this.sessionAttributes.isEmpty()) {
            HttpSession session = request.getSession();
            this.sessionAttributes.entrySet().stream().forEach((e) -> {
                session.setAttribute(e.getKey(), e.getValue());
            });
            this.deletedSessionAttributes.stream().forEach((attrName) -> {
                session.removeAttribute(attrName);
            });
        } else {
            request.getSession().invalidate();
            ((HttpServletRequest)request).getSession(true).setAttribute("locale", locale);
        }
    }
}
