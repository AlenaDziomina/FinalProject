/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.query.Criteria;

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
    
    public Description(Criteria criteria) {
        this.idDescription = (Integer) criteria.getParam(PARAM_NAME_ID_DESCRIPTION);
        this.text = (String) criteria.getParam(PARAM_NAME_TEXT_DESCRIPTION);
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
