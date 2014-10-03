/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Date;

/**
 *
 * @author User
 */

public class Tourist {
    
    private Integer idTourist;
    private Order order;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private String passport;
 
    public Tourist() {
    }
    public Tourist(Integer idTourist) {
        this.idTourist = idTourist;
    }

    public Integer getIdTourist() {
        return idTourist;
    }
    public void setIdTourist(Integer idTourist) {
        this.idTourist = idTourist;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

}
