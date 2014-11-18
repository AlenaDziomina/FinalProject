package by.epam.project.tag;

import java.io.IOException;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class OptionTag extends TagSupport {
    private Short status;
    private Date date;
    private String valClass;
    private String invalClass;
    private String invalDateClass;
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
            if (status == 0) {
                out.write("<option class='" + invalClass + "' value='" + value + "'>");

            } else if(status == 1) {
                if (date != null) {
                    Date currDate = new Date();
                    if (date.after(currDate)) {
                        out.write("<option class='" + valClass + "' value='" + value + "'>");
                    } else {
                        out.write("<option class='" + invalDateClass + "' value='" + value + "'>");
                    }
                } else {
                    out.write("<option class='" + valClass + "' value='" + value + "'>");
                }
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInvalDateClass(String invalDateClass) {
        this.invalDateClass = invalDateClass;
    }

}
