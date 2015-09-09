package by.epam.project.entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Class of user objects.
 * @author Helena.Grouk
 */
public class User {
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
        this.idUser = idUser;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User eq = (User)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idUser, eq.idUser)) {
            return false;
        }
        if(role.equals(eq.role)) {
            return false;
        }
        if(login == null ? eq.login != null : !login.equals(eq.login)) {
            return false;
        }
        if(!Objects.equals(password, eq.password)) {
            return false;
        }
        if(email == null ? eq.email != null : !email.equals(eq.email)) {
            return false;
        }
        if(phone == null ? eq.phone != null : !phone.equals(eq.phone)) {
            return false;
        }
        if(!Objects.equals(discount, eq.discount)) {
            return false;
        }
        if(!Objects.equals(balance, eq.balance)) {
            return false;
        }
        if(language == null ? eq.language != null : !language.equals(eq.language)) {
            return false;
        }
        return Objects.equals(status, eq.status);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idUser);
        hash = 29 * hash + Objects.hashCode(this.role);
        hash = 29 * hash + Objects.hashCode(this.login);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.phone);
        hash = 29 * hash + Objects.hashCode(this.discount);
        hash = 29 * hash + Objects.hashCode(this.balance);
        hash = 29 * hash + Objects.hashCode(this.language);
        hash = 29 * hash + Objects.hashCode(this.status);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idUser: ");
        str.append(idUser);
        str.append(", role: ");
        str.append(role);
        str.append(", login: ");
        str.append(login);
        str.append(", password: ");
        str.append(password);
        str.append(", email: ");
        str.append(email);
        str.append(", phone: ");
        str.append(phone);
        str.append(", discount: ");
        str.append(discount);
        str.append(", balance: ");
        str.append(balance);
        str.append(", language: ");
        str.append(language);
        str.append(", status: ");
        str.append(status);
        return str.toString();
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
    public Short getStatus(){
        return this.status;
    }
    public static class LoginComparator implements Comparator<User> {
        @Override
        public int compare(User one, User two) {
            return one.getLogin().compareTo(two.getLogin());
        }
    }
}
