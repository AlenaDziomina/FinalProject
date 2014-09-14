/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.controller.ProjectServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASS = "1111";
    
    public static boolean checkLogin(String enterLogin, String enterPass) {
        
        Context envCtx;
        try {
            Context initCtx = new InitialContext();
            Context envCtx1 = (Context) initCtx.lookup("java:comp/env");
            
            envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            DataSource ds = (DataSource) envCtx.lookup("jdbc/newdb");
            Connection cn = ds.getConnection();
            cn.close();
        } catch (NamingException | SQLException ex) {
            java.util.logging.Logger.getLogger(ProjectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
    }
}
