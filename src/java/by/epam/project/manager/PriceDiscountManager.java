/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import by.epam.project.entity.Order;
import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class PriceDiscountManager {
    
    public static Float getFinalPrice(float price, int discount) {
        Float finalPrice = price * (100 - discount) / 100;
        return round(finalPrice, 2);
    }
    
    public static Float getFinalPrice(float price, int discount, int userDiscount) {
        Float finalPrice = price * (100 - discount) * (100 - userDiscount) / 10000;
        return round(finalPrice, 2);
    }
    
    public static Float getFinalPrice(float price, int discount, int userDiscount, int count) {
        Float finalPrice = price * (100 - discount) * (100 - userDiscount) / 10000;
        finalPrice = round(finalPrice, 2) * count;
        return finalPrice;
    }
    
    public static Float getFinalPrice(Order order) {
        Float price = order.getCurrentPrice();
        Integer discount = order.getCurrentDiscount();
        Integer userDiscount = order.getCurrentUserDiscount();
        Integer count = order.getSeats();
        Float totalPrice = getFinalPrice(price, discount, userDiscount, count);
        return totalPrice;
    }

    public static Integer getDiscountForOrder(Integer discount, Float finalPrice) {
        
        int coef = Math.floorDiv(Math.round(finalPrice), 1000);
        if (coef <= 0) {
            coef = 1;
        }
        Integer maxDiscount = Integer.decode(ConfigurationManager.getProperty("user.discount.max"));
        Integer discountForOrder = discount + coef;
        if (discountForOrder > 50) {
            return maxDiscount;
        } else {
            return discountForOrder;
        }
    }

    public static Integer getDiscountForDeleteOrder(Integer discount) {
        Integer discountForDeleteOrder = Integer.decode(ConfigurationManager.getProperty("user.discount.fine"));
        if (discountForDeleteOrder > discount) {
            return 0;
        } else {
            return discount - discountForDeleteOrder;
        }
    }
    
    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
