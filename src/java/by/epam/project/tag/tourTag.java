/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import by.epam.project.manager.PriceDiscountManager;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class tourTag extends TagSupport {
    private float price;
    private int discount;
    private int userDiscount;

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
            if (price != 0) {
                JspWriter out = pageContext.getOut();
                out.write(PriceDiscountManager.getFinalPrice(price, discount, userDiscount).toString());
                return EVAL_BODY_INCLUDE;
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setUserDiscount(int userDiscount) {
        this.userDiscount = userDiscount;
    }

}
