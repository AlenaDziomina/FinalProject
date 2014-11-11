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
    private String href;

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
            if (ifValid != null && ifValid == "tr") {
                if (status != null && status == 1) {
                    out.write("<tr");
                    writeHref(out);
                } else if (status != null && status == 0) {
                    out.write("<tr class='" + ifInvalid + "' ");
                    writeHref(out);
                }
            } else {
                if (status != null && status == 1 && ifValid != null && !ifValid.isEmpty()) {
                    out.write("<a class='" + ifValid + "' ");
                    writeHref(out);
                } else if(status != null && status == 0 && ifInvalid != null && !ifInvalid.isEmpty()) {
                    out.write("<a class='" + ifInvalid + "' ");
                    writeHref(out);
                }
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    
    private void writeHref(JspWriter out) throws IOException {
        if (href != null && !href.isEmpty()) {
            out.write("href='" + href + "' ");
        }
        out.write(">");
    }
    
    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            if (ifValid != null && ifValid == "tr" && status != null) {
                out.write("</tr>");
                writeHref(out);
            } else {
                if (status != null && status == 1 && ifValid != null && !ifValid.isEmpty()) {
                    out.write("</a>");
                } else if(status != null && status == 0 && ifInvalid != null && !ifInvalid.isEmpty()) {
                    out.write("</a>");
                }
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
    
    public void setHref(String href) {
        this.href = href;
    }

    
    
}
