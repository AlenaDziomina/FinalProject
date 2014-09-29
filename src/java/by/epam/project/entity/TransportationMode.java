/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.io.Serializable;

/**
 *
 * @author User
 */

public class TransportationMode implements Serializable {
   
    private Integer idMode;
    private String nameMode;
    
    public TransportationMode() {
    }

    public TransportationMode(Integer idMode, String nameMode) {
        this.idMode = idMode;
        this.nameMode = nameMode;
    }

    public Integer getIdMode() {
        return idMode;
    }

    public void setIdMode(Integer idMode) {
        this.idMode = idMode;
    }

    public String getNameMode() {
        return nameMode;
    }

    public void setNameMode(String nameMode) {
        this.nameMode = nameMode;
    }

    
}
