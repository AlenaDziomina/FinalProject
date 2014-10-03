/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

/**
 *
 * @author User
 */
public final class User {
    
    private Integer idUser;
    private Role role;   
    private String login;
    private Integer password;
    private String email;
    private String phone;
    private Integer discount;
    private Float balance;
    private String language;
    private Short status;
 
    public User(){}
    
    public User(Integer idUser){
        setIdUser(idUser);
    }
    
    public void setIdUser(Integer id){
        this.idUser = id;
    }  
    public Integer getIdUser(){
        return this.idUser;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
    public Role getRole() {
        return this.role;
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
    
    public void setStatus(Short status){
        this.status = status;
    }
    
//    public Date getDate(){
//        return date;
//    }
//    
//    public void setDate(String date) throws BeanInitException{
//        if (date == null || date.isEmpty()) {
//            throw new BeanInitException();
//        } else {
//            this.date = Date.valueOf(date);
//        }
//    }
}
