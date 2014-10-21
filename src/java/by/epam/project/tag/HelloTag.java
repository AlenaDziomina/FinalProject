/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author User
 */
public class HelloTag extends SimpleTagSupport {
    private String role;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            String to;
            if ("GUEST".equalsIgnoreCase(role)) {
                to = "Hello, " + role;
            } else {
                to = "Welcome, " + role;
            }
            out.write("<hr/>" + to + "<hr/>");
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in HelloTag tag", ex);
        }
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
