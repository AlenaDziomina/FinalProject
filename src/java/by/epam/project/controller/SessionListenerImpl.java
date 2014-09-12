/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller;

import static by.epam.project.controller.ProjectServlet.LOCALLOG;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author User
 */
public class SessionListenerImpl implements HttpSessionAttributeListener {
    
    /**
     *
     * @param ev
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent ev) {
    }
    
    /**
     *
     * @param ev
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent ev) {
        // запись в log-файл или иные действия
        LOCALLOG.info("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
        + " : " + ev.getValue());
    }

    /**
     *
     * @param ev
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        // запись в log-файл или иные действия
        LOCALLOG.info("replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
        + " : " + ev.getValue());
    }
}
