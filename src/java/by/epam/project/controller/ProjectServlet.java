package by.epam.project.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import by.epam.project.action.ActionCommand;
import by.epam.project.factory.CommandFactory;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author User
 */
public class ProjectServlet extends HttpServlet {

    public static final Logger LOCALLOG = Logger.getLogger("Logger");
    public static String MYSQLDB;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Log4JInitServlet is initializing log4j");
        String log4jLocation = config.getInitParameter("log4j-properties-location");

        ServletContext sc = config.getServletContext();
        String realPath = sc.getRealPath("/");
        MYSQLDB = (String)sc.getInitParameter("MySQLDataBase");
        if (log4jLocation == null) {
                System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
        } else {
                String log4jProp = realPath + log4jLocation;
                File propFile = new File(log4jProp);
                if (propFile.exists()) {
                        LOCALLOG.info("Initializing log4j with: " + log4jProp);
                        PropertyConfigurator.configure(log4jProp);
                } else {
                        System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                        BasicConfigurator.configure();
                }
        }     

       
          
        super.init(config);
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SessionRequestContent req = new SessionRequestContent(request);
        ActionCommand command = CommandFactory.defineCommand(req);
        
        
        request.setAttribute("referer", request.getHeader("referer"));
        
        
        String page = command.execute(req);
        req.insertAttributes(request);
        // метод возвращает страницу ответа
        // page = null; // поэксперементировать!
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        } else {
            // установка страницы c cообщением об ошибке
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
        
//        
//        
//        request.getRequestDispatcher("/WEB-INF/jsp/sessionprop.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
