/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class PageTableTag extends TagSupport {
    private String head;
    private int rows;
    private int pageNo;
    private int pages;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public int doStartTag() throws JspTagException {
        if (pages <= 0) {
            return SKIP_BODY;
        }

        try {
            JspWriter out = pageContext.getOut();
            out.write("<table class='parameterRowB' border='1'><colgroup span='2' title='title' />");
            if (head != null && !head.isEmpty()) {
                out.write("<thead><tr><th scope='col'>" + head + "</th></tr></thead>");
            }
            if (pageNo != 0) {
                out.write("<thead><tr><th scope='col'>");
                for (int i = 1; i <= pages; i++) {
                    if (i != pageNo) {
                        out.write("<a class='small labelH' href='controller?command=showpage&currPageNo=" + i + "'>" + i + "</a>");
                    } else {
                        out.write("<a class='small labelH'>" + pageNo + "</a>");
                    }
                }
                out.write("</th></tr></thead>");
            }
            out.write("<tbody><tr><td>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        if (rows-- > 1) {
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            if (pageNo != 0) {
                out.write("<thead><tr><th scope='col'>");
                for (int i = 1; i <= pages; i++) {
                    if (i != pageNo) {
                        out.write("<a class='small labelH' href='controller?command=showpage&currPageNo=" + i + "'>" + i + "</a>");
                    } else {
                        out.write("<a class='small labelH'>" + pageNo + "</a>");
                    }
                }
                out.write("</th></tr></thead>");
            }
            out.write("</td></tr></tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

}
