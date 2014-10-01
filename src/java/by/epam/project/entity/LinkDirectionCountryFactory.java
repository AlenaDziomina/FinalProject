/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import by.epam.project.dao.query.Criteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class LinkDirectionCountryFactory {
    public static List<LinkDirectionCountry> getInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(PARAM_NAME_ID_DIRECTION);
        Collection<Integer> cntr = (Collection<Integer>) criteria.getParam(PARAM_NAME_ID_COUNTRY);
        List<LinkDirectionCountry> list = new ArrayList<>();
        for (Integer c : cntr) {
            list.add(new LinkDirectionCountry(dir, c));
        }
        
        return list;
    }

   

   
}
