/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

/**
 *
 * @author User
 */
public abstract class JspParamNames {
    
    //common
    public static final String JSP_PAGE = "page";
    public static final String JSP_LENG = "leng";
    public static final String JSP_LOCALE = "locale";
    public static final String JSP_SELECT_ID = "selectId";
    public static final String JSP_CURR_PAGE_NO = "currPageNo";
    public static final String JSP_PAGE_LIST = "pageList";
    public static final Short DELETED = 0;
    public static final Short ACTIVE = 1;
    
    //user
    public static final String JSP_USER = "user";
    public static final String JSP_ID_USER = "idUser";
    public static final String JSP_USER_LOGIN = "login";
    public static final String JSP_USER_PASSWORD = "password";
    public static final String JSP_USER_EMAIL = "email";
    public static final String JSP_USER_PHONE = "phone";
    public static final String JSP_USER_DISCOUNT = "discount";
    public static final String JSP_USER_BALANCE = "balance";
    public static final String JSP_USER_VALID_STATUS = "validUserStatus";
    public static final String JSP_USER_INVALID_STATUS = "invalidUserStatus";
    public static final String JSP_CURRENT_USER = "currUser";
    public static final String JSP_USER_LIST = "userList";
    
    //role
    public static final String JSP_ROLE_TYPE = "role";

    //description
    public static final String JSP_ID_DESCRIPTION = "idDescription";
    public static final String JSP_DESCRIPTION_TEXT = "textDescription";
    
    //country
    public static final String JSP_ID_COUNTRY = "idCountry";
    public static final String JSP_COUNTRY_NAME = "nameCountry";
    public static final String JSP_COUNTRY_PICTURE = "pictureCountry";
    public static final String JSP_COUNTRY_LIST = "countryList";
    public static final String JSP_COUNTRY_COUNT = "countryCount";
    public static final String JSP_CURRENT_COUNTRY = "currCountry";
    public static final String JSP_COUNTRY_TAG_LIST = "countryTagList";
    public static final String JSP_CURR_COUNTRY_TAGS = "currCountryTag";
    public static final String JSP_CURR_ID_COUNTRY = "currIdCountry";
    public static final String JSP_COUNTRY_VALID_STATUS = "validCountryStatus";
    public static final String JSP_COUNTRY_INVALID_STATUS = "invalidCountryStatus";
    
    
    //city
    public static final String JSP_ID_CITY = "idCity";
    public static final String JSP_CITY_NAME = "nameCity";
    public static final String JSP_CITY_PICTURE = "pictureCity";
    public static final String JSP_CITY_LIST = "cityList";
    public static final String JSP_CITY_COUNT = "cityCount";
    public static final String JSP_CURRENT_CITY = "currCity";
    public static final String JSP_CURR_CITY_LIST = "currCityList";
    public static final String JSP_CITY_TAG_LIST = "cityTagList";
    public static final String JSP_CURR_CITY_TAGS = "currCityTag";
    public static final String JSP_CURR_ID_CITY = "currIdCity";
    public static final String JSP_CITY_VALID_STATUS = "validCityStatus";
    public static final String JSP_CITY_INVALID_STATUS = "invalidCityStatus";
    
    //hotel
    public static final String JSP_ID_HOTEL = "idHotel";
    public static final String JSP_HOTEL_NAME = "nameHotel";
    public static final String JSP_HOTEL_STARS = "starsHotel";
    public static final String JSP_HOTEL_PICTURE = "pictureHotel";
    public static final String JSP_HOTEL_LIST = "hotelList";
    public static final String JSP_HOTEL_COUNT = "hotelCount";
    public static final String JSP_CURRENT_HOTEL = "currHotel";
    public static final String JSP_HOTEL_TAG_LIST = "hotelTagList";
    public static final String JSP_CURR_HOTEL_TAGS = "currHotelTag";
    public static final String JSP_CURR_ID_HOTEL = "currIdHotel";
    public static final String JSP_CURR_HOTEL_STARS = "currStars";
    public static final String JSP_HOTEL_VALID_STATUS = "validHotelStatus";
    public static final String JSP_HOTEL_INVALID_STATUS = "invalidHotelStatus";
    
    //direction
    public static final String JSP_ID_DIRECTION = "idDirection";
    public static final String JSP_DIRECTION_NAME = "nameDirection";
    public static final String JSP_DIRECTION_PICTURE = "pictureDirection";
    public static final String JSP_DIRECTION_TEXT = "textDirection";
    public static final String JSP_DIRECTION_STATUS = "statusDirection";
    public static final String JSP_DIRECTION_LIST = "directionList";
    public static final String JSP_DIRECTION_COUNT = "directionCount";
    public static final String JSP_CURRENT_DIRECTION = "currDirection";
    public static final String JSP_CURR_ID_DIRECTION = "currIdDirection";
    public static final String JSP_DIRECTION_VALID_STATUS = "validDirectionStatus";
    public static final String JSP_DIRECTION_INVALID_STATUS = "invalidDirectionStatus";
    
    //tour_type
    public static final String JSP_ID_TOURTYPE = "idTourType";
    public static final String JSP_TOUR_TYPE_LIST = "tourTypeList";
    public static final String JSP_TOUR_TYPE_COUNT = "tourTypeCount";
    public static final String JSP_CURR_TOUR_TYPE = "currTourType";
    
    //trans_mode
    public static final String JSP_ID_TRANSMODE = "idTransMode";
    public static final String JSP_TRANS_MODE_LIST = "transModeList";
    public static final String JSP_TRANS_MODE_COUNT = "transModeCount";
    public static final String JSP_CURR_TRANS_MODE = "currTransMode";
    
    //tour
    public static final String JSP_TOUR_LIST = "tourList";
    public static final String JSP_ID_TOUR = "idTour";
    public static final String JSP_CURR_ID_TOUR = "currIdTour";
    public static final String JSP_CURR_DEPART_DATE = "departDate";
    public static final String JSP_CURR_ARRIVAL_DATE = "arrivalDate";
    public static final String JSP_TOUR_PRICE = "price";
    public static final String JSP_TOUR_DISCOUNT = "discount";
    public static final String JSP_TOTAL_SEATS = "totalSeats";
    public static final String JSP_FREE_SEATS = "freeSeats";
    public static final String JSP_CURRENT_TOUR = "currTour";
    public static final String JSP_TOUR_VALID_STATUS = "validTourStatus";
    public static final String JSP_TOUR_INVALID_STATUS = "invalidTourStatus";
    public static final String JSP_TOUR_VALID_DATE = "validTourDate";
    public static final String JSP_TOUR_INVALID_DATE = "invalidTourDate";
    
    //search
    public static final String JSP_CURR_PRICE_FROM = "currPriceFrom";
    public static final String JSP_CURR_PRICE_TO = "currPriceTo";
    public static final String JSP_CURR_DEPART_DATE_FROM = "currDepartDateFrom";
    public static final String JSP_CURR_DEPART_DATE_TO = "currDepartDateTo";
    public static final String JSP_CURR_DAYS_COUNT_FROM = "currDaysCountFrom";
    public static final String JSP_CURR_DAYS_COUNT_TO = "currDaysCountTo";
    public static final String JSP_CURR_DISCOUNT_FROM = "currDiscountFrom";
    public static final String JSP_BOX_ALL_DEPART_DATE = "allDepartDate";
    public static final String JSP_BOX_ALL_DAYS_COUNT = "allDaysCount";
    public static final String JSP_BOX_ALL_PRICE = "allPrice";
    public static final String JSP_BOX_ALL_COUNTRIES = "allCountries";
    public static final String JSP_BOX_ALL_CITIES = "allCities";
    public static final String JSP_BOX_ALL_HOTELS = "allHotels";
    public static final String JSP_IS_HIDDEN = "isHidden";
    public static final String JSP_PRICE_STEP = "priceStep";
    public static final String JSP_DISCOUNT_STEP = "discountStep";
    
    //order
    public static final String JSP_CURRENT_ORDER = "currOrder";
    public static final String JSP_CURR_ORDER_SEATS = "currSeats";
    public static final String JSP_ORDER_LIST = "currOrderList";
    public static final String JSP_ORDER_VALID_STATUS = "validOrderStatus";
    public static final String JSP_ORDER_INVALID_STATUS = "invalidOrderStatus";
    public static final String JSP_CURR_ID_ORDER = "currIdOrder";
    
    //tourist
    public static final String JSP_TOURIST_FIRST_NAME = "firstName";
    public static final String JSP_TOURIST_MIDDLE_NAME = "middleName";
    public static final String JSP_TOURIST_LAST_NAME = "lastName";
    public static final String JSP_TOURIST_PASSPORT = "passport";
    
    
}
