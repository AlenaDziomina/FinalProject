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
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author User
 */
public class OptionTag extends TagSupport {
    private Short status;
    private String valClass;
    private String invalClass;
    private Integer value;

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
            if (status == 1) {
                out.write("<option class='" + valClass + "' value='" + value + "'>");
            } else if(status == 0) {
                out.write("<option class='" + invalClass + "' value='" + value + "'>");
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
            out.write("</option>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setValClass(String valClass) {
        this.valClass = valClass;
    }

    public void setInvalClass(String invalClass) {
        this.invalClass = invalClass;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
}
