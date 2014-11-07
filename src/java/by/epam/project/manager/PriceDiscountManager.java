/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import by.epam.project.entity.Order;

/**
 *
 * @author User
 */
public class PriceDiscountManager {
    
    public static Float getFinalPrice(float price, int discount, int userDiscount, int count) {
        Float totalPrice = price * (100 - discount) * (100 - userDiscount) * count / 10000;
        return totalPrice;
    }
    
    public static Float getFinalPrice(Order order) {
        Float price = order.getCurrentPrice();
        Integer discount = order.getCurrentDiscount();
        Integer userDiscount = order.getCurrentUserDiscount();
        Integer count = order.getSeats();
        Float totalPrice = getFinalPrice(price, discount, userDiscount, count);
        return totalPrice;
    }
}
