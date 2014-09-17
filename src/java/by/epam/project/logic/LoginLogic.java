/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.controller.ProjectServlet;
import by.epam.project.dao.ConnectionPool;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.PersonSaveQuery;
import by.epam.project.dao.query.QueryExecutionException;
import by.epam.project.entity.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;

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
//            Context initCtx = new InitialContext();
//            Context envCtx1 = (Context) initCtx.lookup("java:comp/env");
//            
//            envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
//            DataSource ds = (DataSource) envCtx.lookup(ConfigurationManager.getProperty("db.name"));
            Connection cn = ConnectionPool.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM peoples");
            ArrayList<String> lst = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                lst.add(name);
            }
            rs.close();
            st.close();
            ConnectionPool.returnConnection(cn);
            
//            List<Person> l1 = new ArrayList();
//            l1.add(new Person("Grouk", Date.valueOf("1984-06-05")));
//            l1.add(new Person("Grouk A.", Date.valueOf("2007-08-26")));
//            try {
//                new PersonSaveQuery().save(l1);
//            } catch (QueryExecutionException ex) {
//                Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                List<Person> l1 = new PersonSaveQuery().load(new Criteria());
                
            } catch (QueryExecutionException ex) {
                Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            return lst.get(1).equals(enterLogin);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProjectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
    }
}
