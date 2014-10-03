/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import by.epam.project.dao.query.Criteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class LinkDirectionFactory {
    public static List<LinkDirectionCountry> getLinkCountryInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> cntr = (Collection<Integer>) criteria.getParam(DAO_ID_COUNTRY);
        List<LinkDirectionCountry> list = new ArrayList<>();
        for (Integer c : cntr) {
            list.add(new LinkDirectionCountry(dir, c));
        }
        
        return list;
    }

    public static  List<LinkDirectionCity> getLinkCityInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> cntr = (Collection<Integer>) criteria.getParam(DAO_ID_CITY);
        List<LinkDirectionCity> list = new ArrayList<>();
        for (Integer c : cntr) {
            list.add(new LinkDirectionCity(dir, c));
        }
        
        return list;
    }

    public static List<DirectionStayHotel> getStayHotelInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> hotels = (Collection<Integer>) criteria.getParam(DAO_ID_HOTEL);
        List<DirectionStayHotel> list = new ArrayList<>();
        Integer i = 0;
        for (Integer id : hotels) {
            list.add(new DirectionStayHotel(dir, id, i));
        }
        
        return list;
    }

   
}
