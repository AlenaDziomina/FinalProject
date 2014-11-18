/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author User
 */

public class TransMode implements Serializable {

    private Integer idMode;
    private String nameMode;

    public TransMode() {
    }

    public TransMode(Integer idMode) {
        this.idMode = idMode;
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

    public static class NameComparator implements Comparator<TransMode> {
        @Override
        public int compare(TransMode one, TransMode two) {
            return one.getNameMode().compareTo(two.getNameMode());
        }
    }

}
