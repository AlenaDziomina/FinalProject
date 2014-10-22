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
public class LinkDirectionCity {
    
    private Integer idDirection;
    private Integer idCity;
    
    public LinkDirectionCity(){}

    public LinkDirectionCity(Integer dir, Integer c) {
        setIdDirection(dir);
        setIdCity(c);
    }
    
    public void setIdDirection(Integer idDirection){
        this.idDirection = idDirection;
    }
    public Integer getIdDirection(){
        return this.idDirection;
    }
    
    public void setIdCity(Integer idCity){
        this.idCity = idCity;
    }
    public Integer getIdCity(){
        return this.idCity;
    }
    
    
}
