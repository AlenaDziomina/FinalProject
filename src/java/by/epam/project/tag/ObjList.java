/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.tag;

import by.epam.project.manager.ConfigurationManager;
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
    private Integer pages;
    private static final Integer pageStep;
    static {
        pageStep = Integer.decode(ConfigurationManager.getProperty("page.step"));
    }
    private Integer currPageNo;
    List<T> currPageList;
    
    public ObjList(List list) {
        this.list = list;
        pages = (list.size() + (pageStep - 1)) / pageStep;
        currPageNo = 1;
        setCurrPageList();
    }
    
    public int getCurrPageNo(){
        return currPageNo;
    }
    
    public void setCurrPageNo(Integer no){
        currPageNo = no;
        setCurrPageList();
    }
    
    private void setCurrPageList() {
        currPageList = new ArrayList<>();
        int first = (currPageNo - 1) * pageStep;
        int last = first + pageStep;
        if (last > list.size()) {
            last = list.size();
        }
        currPageList = list.subList(first , last);
        it = currPageList.iterator();
    }    
    public List<T> getCurrPageList(int no) {
        return currPageList;
    }
    
    public int getSize() {
        return currPageList.size();
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
    
    public int getPages(){
        return pages;
    }
    
    
}
