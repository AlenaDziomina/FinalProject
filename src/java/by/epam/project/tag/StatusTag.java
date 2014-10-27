/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author User
 */
public class StatusTag extends TagSupport {
    private Short status;
    private String ifValid;
    private String ifInvalid;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @return 
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (status != null && status == 1 && ifValid != null && !ifValid.isEmpty()) {
                out.write("<a class='" + ifValid + "'>");
            } else if(status != null && status == 0 && ifInvalid != null && !ifInvalid.isEmpty()) {
                out.write("<a class='" + ifInvalid + "'>");
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            if (status != null && status == 1 && ifValid != null && !ifValid.isEmpty()) {
                out.write("</a>");
            } else if(status != null && status == 0 && ifInvalid != null && !ifInvalid.isEmpty()) {
                out.write("</a>");
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setIfValid(String ifValid) {
        this.ifValid = ifValid;
    }

    public void setIfInvalid(String ifInvalid) {
        this.ifInvalid = ifInvalid;
    }
    
}
