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

public class Description {
    
    private Integer idDescription;
    private String text;
    

    public Description() {
    }

    public Description(int id, String text) {
        this.idDescription = id;
        this.text = text;
    }

    public Integer getIdDescription() {
        return idDescription;
    }

    public void setIdDescription(Integer idDescription) {
        this.idDescription = idDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
    
}
