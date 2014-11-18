/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author Helena.Grouk
 */
public class SessionListenerImpl implements HttpSessionAttributeListener {

    private static final Logger LOGGER = Logger.getLogger(SessionListenerImpl.class);

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
        LOGGER.info("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
        + " : " + ev.getValue());
    }

    /**
     *
     * @param ev
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        // запись в log-файл или иные действия
        LOGGER.info("replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
        + " : " + ev.getValue());
    }
}
