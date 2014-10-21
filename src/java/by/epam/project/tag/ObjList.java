/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 */
public class ObjList<T> {
    List<T> list = new ArrayList();
    private T same;
    private Iterator it; 
    public ObjList(List list) {
        this.list = list;
        it = list.iterator();
    }
    
    public int getSize() {
        return list.size();
    }
    
    public T getSame() {
        return same;
    }
    
    public T getNext(){
        if (it.hasNext()) {
            same = (T) it.next();
            return same;
        } else {
            return null;
        }
        
    }
    
    
        
    
  
}
