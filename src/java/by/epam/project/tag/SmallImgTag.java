/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author User
 */
public class SmallImgTag extends TagSupport {
    private String imgname;

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }
        
    @Override
    public int doStartTag() throws JspException {
        try {
            if (imgname != null && !imgname.isEmpty()) {
                JspWriter out = pageContext.getOut();
                out.write("<img class='smallimg' id='images' src='");
                String path = pageContext.getServletContext().getContextPath();
                String res = imgname + path;
                out.write(path + imgname);
                pageContext.getOut().write("'>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
