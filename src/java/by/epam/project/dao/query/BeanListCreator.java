/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Order;
import by.epam.project.entity.Role;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Helena.Grouk
 */
public class BeanListCreator {
    public static List<LinkDirectionCountry> getLinkCountryInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> cntr = (Collection<Integer>) criteria.getParam(DAO_ID_COUNTRY);
        List<LinkDirectionCountry> list = new ArrayList<>();
        cntr.stream().forEach((c) -> {
            list.add(new LinkDirectionCountry(dir, c));
        });
        return list;
    }

    public static  List<LinkDirectionCity> getLinkCityInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> cntr = (Collection<Integer>) criteria.getParam(DAO_ID_CITY);
        List<LinkDirectionCity> list = new ArrayList<>();
        cntr.stream().forEach((c) -> {
            list.add(new LinkDirectionCity(dir, c));
        });
        return list;
    }

    public static List<DirectionStayHotel> getStayHotelInstances(Criteria criteria) {
        Integer dir = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        Collection<Integer> hotels = (Collection<Integer>) criteria.getParam(DAO_ID_HOTEL);
        List<DirectionStayHotel> list = new ArrayList<>();
        Integer i = 0;
        hotels.stream().forEach((id) -> {
            list.add(new DirectionStayHotel(dir, id, i));
        });
        return list;
    }

    public static List<City> getCityInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createCity(criteria));
        return list;
    }

    public static List<Country> getCountryInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createCountry(criteria));
        return list;
    }

    public static List<Description> getDescriptionInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createDescription(criteria));
        return list;
    }

    public static List<Direction> getDirectionInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createDirection(criteria));
        return list;
    }

    public static List<Hotel> getHotelInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createHotel(criteria));
        return list;
    }

    public static List<Order> getOrderInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createOrder(criteria));
        return list;
    }

    public static List<Role> getRoleInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createRole(criteria));
        return list;
    }

    public static List<Tour> getTourInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createTour(criteria));
        return list;
    }

    public static List<TourType> getTourTypeInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createTourType(criteria));
        return list;
    }

    public static List<Tourist> getTouristInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createTourist(criteria));
        return list;
    }

    public static List<TransMode> getTourTransModeInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createTransMode(criteria));
        return list;
    }

    public static List<User> getUserInstances(Criteria criteria) {
        List list = new ArrayList();
        list.add(createUser(criteria));
        return list;
    }

    private static City createCity(Criteria criteria){
        City bean = new City();
        bean.setIdCity((Integer) criteria.getParam(DAO_ID_CITY));
        bean.setName((String) criteria.getParam(DAO_CITY_NAME));
        bean.setPicture((String) criteria.getParam(DAO_CITY_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_CITY_STATUS));
        bean.setDescription(createDescription(criteria));
        bean.setHotelCollection((Collection<Hotel>) criteria.getParam(DAO_HOTEL_LIST));
        bean.setCountry(createCountry(criteria));
        return bean;
    }

    private static Country createCountry(Criteria criteria){
        Country bean = new Country();
        bean.setIdCountry((Integer) criteria.getParam(DAO_ID_COUNTRY));
        bean.setName((String) criteria.getParam(DAO_COUNTRY_NAME));
        bean.setPicture((String) criteria.getParam(DAO_COUNTRY_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_COUNTRY_STATUS));
        bean.setDescription(createDescription(criteria));
        bean.setCityCollection((Collection<City>) criteria.getParam(DAO_CITY_LIST));
        return bean;
    }

    private static Description createDescription(Criteria criteria){
        Description bean = new Description();
        bean.setIdDescription((Integer) criteria.getParam(DAO_ID_DESCRIPTION));
        bean.setText((String) criteria.getParam(DAO_DESCRIPTION_TEXT));
        return bean;
    }

    private static Direction createDirection(Criteria criteria) {
        Direction bean = new Direction();
        bean.setIdDirection((Integer) criteria.getParam(DAO_ID_DIRECTION));
        bean.setName((String) criteria.getParam(DAO_DIRECTION_NAME));
        bean.setPicture((String) criteria.getParam(DAO_DIRECTION_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_DIRECTION_STATUS));
        bean.setText((String) criteria.getParam(DAO_DIRECTION_TEXT));
        bean.setDescription(createDescription(criteria));
        bean.setTourType(createTourType(criteria));
        bean.setTransMode(createTransMode(criteria));
        return bean;
    }

    private static Hotel createHotel(Criteria criteria){
        Hotel bean = new Hotel();
        bean.setIdHotel((Integer) criteria.getParam(DAO_ID_HOTEL));
        bean.setName((String) criteria.getParam(DAO_HOTEL_NAME));
        bean.setPicture((String) criteria.getParam(DAO_HOTEL_PICTURE));
        bean.setStatus((Short) criteria.getParam(DAO_HOTEL_STATUS));
        bean.setStars((Integer) criteria.getParam(DAO_HOTEL_STARS));
        bean.setDescription(createDescription(criteria));
        bean.setCity(createCity(criteria));
        return bean;
    }

    private static Order createOrder(Criteria criteria) {
        Order bean = new Order();
        bean.setIdOrder((Integer)criteria.getParam(DAO_ID_ORDER));
        bean.setOrderDate((Date)criteria.getParam(DAO_ORDER_DATE));
        bean.setSeats((Integer)criteria.getParam(DAO_ORDER_SEATS));
        bean.setCurrentPrice((Float)criteria.getParam(DAO_ORDER_CURR_PRICE));
        bean.setCurrentDiscount((Integer)criteria.getParam(DAO_ORDER_CURR_DISCOUNT));
        bean.setCurrentUserDiscount((Integer)criteria.getParam(DAO_ORDER_USER_DISCOUNT));
        bean.setFinalPrice((Float)criteria.getParam(DAO_ORDER_FINAL_PRICE));
        bean.setTour(createTour(criteria));
        bean.setUser(createUser(criteria));
        bean.setStatus((Short)criteria.getParam(DAO_ORDER_STATUS));
        return bean;
    }

    private static Role createRole(Criteria criteria) {
        Role bean = new Role();
        bean.setIdRole((Integer)criteria.getParam(DAO_ID_ROLE));
        bean.setRoleName(criteria.getParam(DAO_ROLE_NAME).toString());
        return bean;
    }

    private static Tour createTour(Criteria criteria) {
        Tour bean = new Tour();
        bean.setIdTour((Integer)criteria.getParam(DAO_ID_TOUR));
        bean.setDepartDate((Date)criteria.getParam(DAO_TOUR_DATE));
        bean.setDaysCount((Integer)criteria.getParam(DAO_TOUR_DAYS));
        bean.setPrice((Float)criteria.getParam(DAO_TOUR_PRICE));
        bean.setDiscount((Integer)criteria.getParam(DAO_TOUR_DISCOUNT));
        bean.setTotalSeats((Integer)criteria.getParam(DAO_TOUR_TOTAL_SEATS));
        bean.setFreeSeats((Integer)criteria.getParam(DAO_TOUR_FREE_SEATS));
        bean.setDirection(createDirection(criteria));
        bean.setStatus((Short)criteria.getParam(DAO_TOUR_STATUS));
        return bean;
    }

    private static TourType createTourType(Criteria criteria) {
        TourType bean = new TourType();
        bean.setIdTourType((Integer)criteria.getParam(DAO_ID_TOURTYPE));
        bean.setNameTourType((String)criteria.getParam(DAO_TOURTYPE_NAME));
        return bean;
    }

    private static Object createTourist(Criteria criteria) {
        Tourist bean = new Tourist();
        bean.setIdTourist((Integer) criteria.getParam(DAO_ID_TOURIST));
        bean.setOrder(new Order((Integer) criteria.getParam(DAO_ID_ORDER)));
        bean.setFirstName((String) criteria.getParam(DAO_TOURIST_FNAME));
        bean.setMiddleName((String) criteria.getParam(DAO_TOURIST_MNAME));
        bean.setLastName((String) criteria.getParam(DAO_TOURIST_LNAME));
        bean.setBirthDate((Date) criteria.getParam(DAO_TOURIST_BIRTH));
        bean.setPassport((String) criteria.getParam(DAO_TOURIST_PASSPORT));
        return bean;
    }

    private static TransMode createTransMode(Criteria criteria) {
        TransMode bean = new TransMode();
        bean.setIdMode((Integer)criteria.getParam(DAO_ID_TRANSMODE));
        bean.setNameMode((String)criteria.getParam(DAO_TRANSMODE_NAME));
        return bean;
    }


    private static User createUser(Criteria criteria) {
        User bean = new User();
        bean.setIdUser((Integer) criteria.getParam(DAO_ID_USER));
        bean.setLogin((String) criteria.getParam(DAO_USER_LOGIN));
        bean.setPassword((Integer) criteria.getParam(DAO_USER_PASSWORD));
        bean.setEmail((String) criteria.getParam(DAO_USER_EMAIL));
        bean.setRole(createRole(criteria));
        bean.setPhone((String) criteria.getParam(DAO_USER_PHONE));
        bean.setLanguage((String) criteria.getParam(DAO_USER_LANGUAGE));
        bean.setDiscount((Integer) criteria.getParam(DAO_USER_DISCOUNT));
        bean.setBalance((Float) criteria.getParam(DAO_USER_BALANCE));
        bean.setStatus((Short) criteria.getParam(DAO_USER_STATUS));
        return bean;
    }
}
