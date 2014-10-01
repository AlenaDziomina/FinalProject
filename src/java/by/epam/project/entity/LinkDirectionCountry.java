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
public final class LinkDirectionCountry {
    
    private Integer idDirection;
    private Integer idCountry;
    
    public LinkDirectionCountry(){}

    public LinkDirectionCountry(Integer dir, Integer c) {
        setIdDirection(dir);
        setIdCountry(c);
    }
    
    public void setIdDirection(Integer idDirection){
        this.idDirection = idDirection;
    }
    public Integer getIdDirection(){
        return this.idDirection;
    }
    
    public void setIdCountry(Integer idCountry){
        this.idCountry = idCountry;
    }
    public Integer getIdCountry(){
        return this.idCountry;
    }
    
    
}
