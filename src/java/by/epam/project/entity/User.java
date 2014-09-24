/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.manager.ClientTypeManager.clientTypeOf;
import java.sql.Date;




/**
 *
 * @author User
 */
public final class User {
    
    private Integer idUser;
    private String login;
    private Integer password;
    private String email;
    private String phone;
    private ClientType role;   
    private Integer idRole;
    private String language;
    private Integer discount;
    private Float balance;
    private Date date;
    
    public User(){}
    
    public User(Criteria criteria){
        setIdUser((Integer) criteria.getParam(PARAM_NAME_ID_USER));
        setLogin((String) criteria.getParam(PARAM_NAME_LOGIN));
        setPassword((String) criteria.getParam(PARAM_NAME_PASSWORD));
        setEmail((String) criteria.getParam(PARAM_NAME_EMAIL));
        setPhone((String) criteria.getParam(PARAM_NAME_PHONE));
        setRole((ClientType) criteria.getParam(PARAM_NAME_ROLE));
        setIdRole((Integer) criteria.getParam(PARAM_NAME_ID_ROLE));
        setLanguage((String) criteria.getParam(PARAM_NAME_LANGUAGE));
        setDiscount((Integer) criteria.getParam(PARAM_NAME_DISCOUNT));
        setBalance((Float) criteria.getParam(PARAM_NAME_BALANCE));
        try {
            setDate((String) criteria.getParam(PARAM_NAME_DATE));
        } catch (BeanInitException ex) {
            date = null;
        }
    }
    
    public void setIdUser(Integer id){
        this.idUser = id;
    }
    
    public Integer getIdUser(){
        return this.idUser;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setPassword(String password){
        this.password = password.hashCode();
    }
    
    public void setPassword(Integer password){
        this.password = password;
    }
    
    public Integer getPassword(){
        return this.password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public void setRole(String role){
        this.role = clientTypeOf(role);
    }
    
    public void setRole(ClientType role){
        this.role = role;
    }

    public ClientType getRole() {
        return this.role;
    }
    
    public void setIdRole(Integer idRole){
        this.idRole = idRole;
    }
    
    public Integer getIdRole(){
        return this.idRole;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setDiscount(Integer discount){
        this.discount = discount;
    }
    
    public Integer getDiscount() {
        return this.discount;
    }
    
    public void setBalance(Float balance){
        this.balance = balance;
    }

    public Float getBalance() {
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