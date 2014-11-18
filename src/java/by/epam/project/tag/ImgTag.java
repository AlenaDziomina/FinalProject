/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class ImgTag extends TagSupport {
    private String idImg;
    private String classImg;
    private String nameImg;

    @Override
    public int doStartTag() throws JspException {
        try {
            if (nameImg != null && !nameImg.isEmpty()) {
                JspWriter out = pageContext.getOut();
                out.write("<img class='" + classImg + "' id='" + idImg + "' src='");
                String path = pageContext.getServletContext().getContextPath();
                out.write(path + nameImg);
                pageContext.getOut().write("'>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    public void setClassImg(String classImg) {
        this.classImg = classImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

}
