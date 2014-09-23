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
public class Role {
    private Integer idRole;
    private String roleName;
    
    public Role(){}
    
    public void setIdRole(Integer id) {
        this.idRole = id;
    }
    
    public Integer getIdRole(){
        return this.idRole;
    }
    
    public void setRoleNAme(String name){
        this.roleName = name;
    }
    
    public String getRoleName(){
        return this.roleName;
    }
    
}
