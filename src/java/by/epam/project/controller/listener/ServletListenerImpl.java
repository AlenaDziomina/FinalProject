/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller.listener;

import by.epam.project.dao.DaoFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Helena.Grouk
 */
public class ServletListenerImpl implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ServletListenerImpl.class);
    @Override
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();
        String admin = context.getInitParameter("admin");
        context.log("Context Initialized with parameter: " + admin);
    }
    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        DaoFactory.closeConnections();
    }
}
