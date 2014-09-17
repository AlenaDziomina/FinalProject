/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.sql.Date;




/**
 *
 * @author User
 */
public class Person {
    
    private String fio;
    private Date date;
    public Person(){}
    public Person(String fio, Date date) {
        this.fio = fio;
        this.date = date;
    }
    public String getFio(){
        return fio;
    }
    public void setFio(String fio){
        this.fio = fio;
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
