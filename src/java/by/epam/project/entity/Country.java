/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;

/**
 * Class of country objects.
 * @author Helena.Grouk
 */

public class Country {

    private Integer idCountry;
    private String name;
    private Short status;
    private String picture;
    private Description description;
    private Collection<City> cityCollection;

    public Country() {
    }

    public Country(Integer idCountry){
        setIdCountry(idCountry);
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public static class NameComparator implements Comparator<Country> {
        @Override
        public int compare(Country one, Country two) {
            return one.getName().compareTo(two.getName());
        }
    }

}
