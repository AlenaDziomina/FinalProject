/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import by.epam.project.dao.ClientType;
import static by.epam.project.manager.ClientTypeManager.clientTypeOf;
import java.sql.Date;




/**
 *
 * @author User
 */
public class User {
    
    private int id;
    private String login;
    private String email;
    private ClientType role;
    private String language;
    private int discount;
    private float balance;
    private Date date;
    
    public User(){}
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setRole(String role){
        this.role = clientTypeOf(role);
    }

    public ClientType getRole() {
        return this.role;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setDiscount(int discount){
        this.discount = discount;
    }
    
    public int getDiscount() {
        return this.discount;
    }
    
    public void setBalance(float balance){
        this.balance = balance;
    }

    public float getBalance() {
        return this.balance;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(String date) throws BeanInitException{
        if (date == null || date.isEmpty()) {
            throw new BeanInitException();
        } else {
            this.date = Date.valueOf(date);
        }
    }
}
