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

public class TourType {
    
    private Integer idTourType;
    private String nameTourType;

    public TourType() {
    }

    public TourType(Integer idTourType, String nameTourType) {
        this.idTourType = idTourType;
        this.nameTourType = nameTourType;
    }

    public Integer getIdTourType() {
        return idTourType;
    }

    public void setIdTourType(Integer idTourType) {
        this.idTourType = idTourType;
    }

    public String getNameTourType() {
        return nameTourType;
    }

    public void setNameTourType(String nameTourType) {
        this.nameTourType = nameTourType;
    }

}
