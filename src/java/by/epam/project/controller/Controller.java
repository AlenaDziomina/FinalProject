package by.epam.project.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import by.epam.project.action.ActionCommand;
import by.epam.project.action.CommandFactory;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author User
 */
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        
        ServletContext sc = config.getServletContext();
        String log4jLocation = config.getInitParameter("log4j-properties-location");
        if (log4jLocation == null) {
            sc.log("No log4j-properties-location init param");
            throw new ServletLogicException("No log4j-properties-location init param");
        }
        String realPath = sc.getRealPath("/");
        String log4jProp = realPath + log4jLocation;
        File propFile = new File(log4jProp);
        if (propFile.exists()) {
            PropertyConfigurator.configure(log4jProp);
            LOGGER.info("Initializing log4j with: " + log4jProp);
        } else {
            sc.log("Init log4j-properties-file not found");
            throw new ServletLogicException("Init log4j-properties-file not found");
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
        String page = command.execute(req);
        req.insertAttributes(request);
        
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            response.sendRedirect(request.getContextPath() + page);
        }

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
