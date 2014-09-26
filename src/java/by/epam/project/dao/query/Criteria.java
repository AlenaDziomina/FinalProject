/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class Criteria {
    
    private final Map params = new HashMap<>();
    
    public Criteria() {
    }
    
    public void addParam(String name,Object value) {
        params.put(name, value);
    }
    
    public Object getParam(String name){
        String p = name;
        return params.get(name);
    }

    public Collection<Object> getAll(){        
        return params.values();
    }
    
    public void remuveParam(String name){
        params.remove(name);
    }
   
}
