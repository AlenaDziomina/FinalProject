/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

/**
 *
 * @author User
 */
public interface UserDao extends GuestDao {
    //metods
    
    public void to_view_orders(); 
    public void to_view_ballance(); 
    public void to_book_tour();
    public void to_pay_tour();

}
