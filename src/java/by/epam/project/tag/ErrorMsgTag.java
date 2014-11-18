/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class ErrorMsgTag extends TagSupport {
    private String msg;
    private String classErr;

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
            if (msg != null && !msg.isEmpty()) {
                JspWriter out = pageContext.getOut();
                out.write("<div id='" + classErr + "'>");
                return EVAL_BODY_INCLUDE;
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            if (msg != null && !msg.isEmpty()) {
                JspWriter out = pageContext.getOut();
                out.write("</div>");
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setClassErr(String classErr) {
        this.classErr = classErr;
    }

}
