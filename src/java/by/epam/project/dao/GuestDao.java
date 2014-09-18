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
public interface GuestDao extends AbstractDao {
    
    //metods
    public void to_registrate();
    public void to_login();
    public void to_view_discounts();
    
}
