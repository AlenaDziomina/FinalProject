/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

/**
 * Class of description objects.
 * @author Helena.Grouk
 */

public class Description {
    
    private Integer idDescription;
    private String text;
    
    public Description() {
    }

    public Description(Integer id) {
        this.idDescription = id;
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
