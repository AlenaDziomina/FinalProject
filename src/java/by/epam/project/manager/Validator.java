/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.TechnicalException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.nashorn.internal.runtime.regexp.RegExp;

/**
 *
 * @author User
 */
public class Validator {
    
    private static final int LOGIN_SIZE = 20;
    private static final String LOGIN_ERROR_MSG = "message.errorLogin";
    private static final int PASSWORD_SIZE = 20;
    private static final String PASSWORD_ERROR_MSG = "message.errorPassword"; //errorRepeatPass
    private static final int PHONE_SIZE = 16;
    private static final String PHONE_ERROR_MSG = "message.errorPhone";
    private static final int EMAIL_SIZE = 60;
    private static final String EMAIL_ERROR_MSG = "message.errorEmail";
    private static final int NAME_SIZE = 60;
    private static final int DIRECT_NAME_SIZE = 80;
    private static final String NAME_ERROR_MSG = "message.errorName";
    private static final int PICTURE_SIZE = 60;
    private static final String PICTURE_ERROR_MSG = "errorPicture";
    private static final String SELECT_COUNTRY_ERROR_MSG = "errorSelectCountry";
    private static final String SELECT_CITY_ERROR_MSG = "errorSelectCity";
    private static final String SELECT_STARS_ERROR_MSG = "errorSelectStars";
    private static final int STARS_MIN = 1;
    private static final int STARS_MAX = 5;
    private static final String SELECT_TOURTYPE_ERROR_MSG = "errorSelectTourType";
    private static final String SELECT_TRANSMODE_ERROR_MSG = "errorSelectTransMode";
    private static final int TEXT_SIZE = 63355;
    private static final String TEXT_ERROR_MSG = "errorTextDir";
    private static final String DISCOUNT_ERROR_MSG = "errorDiscount";
    private static final int DISCOUNT_MIN = 0;
    private static final int DISCOUNT_MAX = 100;
    private static final String TOTALSEATS_ERROR_MSG = "erroTotalSeats";
    private static final int TOTALSEATS_MIN = 0;
    private static final int TOTALSEATS_MAX = Integer.MAX_VALUE;
    private static final String FREESEATS_ERROR_MSG = "errorFreeSeats";
    private static final int FREESEATS_MIN = 0;
    private static final float PRICE_MIN = 0;
    private static final float PRICE_MAX = Float.MAX_VALUE;
    private static final String PRICE_ERROR_MSG = "errorPrice";
    private static final String SEATS_ERROR_MSG = "errorOrderSeats";
    private static final int SEATS_MIN = 1;
    private static final int SEATS_MAX = Integer.MAX_VALUE;
    private static final String FIO_ERROR_MSG = "errorFio"; //errorTouristList
    private static final int FIO_SIZE = 60;
    private static final String PASSPORT_ERROR_MSG = "errorPassport";
    private static final int PASSPORT_SIZE = 9;

    public static void validateCity(City city) throws TechnicalException {
        if ( ! isStringValid(city.getName(), NAME_SIZE)) {
            throw new TechnicalException(NAME_ERROR_MSG);
        }
        if ( ! isStringValid(city.getPicture(), PICTURE_SIZE)) {
            throw new TechnicalException(PICTURE_ERROR_MSG);
        }
        if ( ! isSelectedElem(city.getCountry().getIdCountry())) {
            throw new TechnicalException(SELECT_COUNTRY_ERROR_MSG);
        }
    }

    public static void validateCountry(Country country) throws TechnicalException {
        if ( ! isStringValid(country.getName(), NAME_SIZE)) {
            throw new TechnicalException(NAME_ERROR_MSG);
        }
        if ( ! isStringValid(country.getPicture(), PICTURE_SIZE)) {
            throw new TechnicalException(PICTURE_ERROR_MSG);
        }
    }

    public static void validateDirection(Direction direction) throws TechnicalException {
        if ( ! isStringValid(direction.getName(), DIRECT_NAME_SIZE)) {
            throw new TechnicalException(NAME_ERROR_MSG);
        }
        if ( ! isStringValid(direction.getPicture(), PICTURE_SIZE)) {
            throw new TechnicalException(PICTURE_ERROR_MSG);
        }
        if ( ! isStringValid(direction.getText(), TEXT_SIZE)) {
            throw new TechnicalException(TEXT_ERROR_MSG);
        }
        if ( ! isSelectedElem(direction.getTourType().getIdTourType())) {
            throw new TechnicalException(SELECT_TOURTYPE_ERROR_MSG);
        }
        if ( ! isSelectedElem(direction.getTransMode().getIdMode())) {
            throw new TechnicalException(SELECT_TRANSMODE_ERROR_MSG);
        }
        
    }

    public static void validateHotel(Hotel hotel) throws TechnicalException {
        if ( ! isStringValid(hotel.getName(), NAME_SIZE)) {
            throw new TechnicalException(NAME_ERROR_MSG);
        }
        if ( ! isStringValid(hotel.getPicture(), PICTURE_SIZE)) {
            throw new TechnicalException(PICTURE_ERROR_MSG);
        }
        if ( ! isSelectedElem(hotel.getCity().getIdCity())) {
            throw new TechnicalException(SELECT_CITY_ERROR_MSG);
        }
        if ( ! isSelectedElem(hotel.getStars()) || ! isValidStars(hotel.getStars())) {
            throw new TechnicalException(SELECT_STARS_ERROR_MSG);
        }
    }
    
    private static boolean isValidStars(Integer stars) {
        return stars <= STARS_MAX && stars >= STARS_MIN;
    }

    public static void validateOrder(Order order) throws TechnicalException {
        if ( ! isSeatsValid(order.getSeats())) {
            throw new TechnicalException(SEATS_ERROR_MSG);
        }
    }

    private static boolean isSeatsValid(Integer seats) {
        return seats != null && seats >= SEATS_MIN && seats <= SEATS_MAX;
    }
    
    public static void validateTourist(Tourist tourist) throws TechnicalException {
        validateFIO(tourist.getFirstName());
        validateFIO(tourist.getMiddleName());
        validateFIO(tourist.getLastName());
        validatePassport(tourist.getPassport());
    }
    
    private static void validatePassport(String passport) throws TechnicalException {
        if (passport == null || passport.isEmpty() || passport.length() > PASSPORT_SIZE) {
            throw new TechnicalException(PASSPORT_ERROR_MSG);
        }
        String regex = "[A-Z]{2}\\\\d{7}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(passport);
        if (! m.matches()) {
            throw new TechnicalException(PASSPORT_ERROR_MSG);
        }
    }
     
    private static void validateFIO(String name) throws TechnicalException {
        if (name == null || name.isEmpty() || name.length() > FIO_SIZE) {
            throw new TechnicalException(FIO_ERROR_MSG);
        }
        String regex = "[а-яёA-ЯЁ a-zA-Z\\'\\-]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        if (! m.matches()) {
            throw new TechnicalException(FIO_ERROR_MSG);
        }
    }

    public static void validateTour(Tour tour) throws TechnicalException {
        if ( ! isValidDiscount(tour.getDiscount())) {
            throw new TechnicalException(DISCOUNT_ERROR_MSG);
        }
        if ( ! isValidTotalSeats(tour.getTotalSeats())) {
            throw new TechnicalException(TOTALSEATS_ERROR_MSG);
        }
        if ( ! isValidFreeSeats(tour.getTotalSeats(), tour.getFreeSeats())) {
            throw new TechnicalException(FREESEATS_ERROR_MSG);
        }
        if ( ! isValidPrice(tour.getPrice())) {
            throw new TechnicalException(PRICE_ERROR_MSG);
        }
    }
    
    private static boolean isValidPrice(Float price) {
        return price != null && price >= PRICE_MIN && price <= PRICE_MAX;
    }

    private static boolean isValidFreeSeats(Integer totalSeats, Integer freeSeats) {
        return freeSeats != null && freeSeats >= FREESEATS_MIN && freeSeats <= totalSeats;
    }
    
    private static boolean isValidTotalSeats(Integer totalSeats) {
        return totalSeats != null && totalSeats >= TOTALSEATS_MIN && totalSeats <= TOTALSEATS_MAX;
    }
    
    private static boolean isValidDiscount(Integer discount) {
        return discount != null && discount >= DISCOUNT_MIN && discount <= DISCOUNT_MAX;
    }
    
    public static void validateLogin(String login) throws TechnicalException {
        if (login == null || login.isEmpty() || login.length() > LOGIN_SIZE) {
            throw new TechnicalException(LOGIN_ERROR_MSG);
        }
        String regex = "[\\S]{4,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(login);
        if (! m.matches()) {
            throw new TechnicalException(LOGIN_ERROR_MSG);
        }
    }

    public static void validatePassword(String password) throws TechnicalException {
        if (password == null || password.isEmpty() || password.length() > PASSWORD_SIZE) {
            throw new TechnicalException(PASSWORD_ERROR_MSG);
        }
        String regex = "[\\w]{4,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if (! m.matches()) {
            throw new TechnicalException(PASSWORD_ERROR_MSG);
        }
    }

    public static void validateUser(User currUser) throws TechnicalException {
        validatePhone(currUser.getPhone());
        validateEmail(currUser.getEmail());
    }

    private static void validatePhone(String phone) throws TechnicalException {
        if (phone == null || phone.isEmpty() || phone.length() > PHONE_SIZE) {
            throw new TechnicalException(PHONE_ERROR_MSG);
        }
        String regex = "\\+375\\-\\d{2}\\-\\d{3}\\-\\d{2}-\\d{2}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        if (! m.matches()) {
            throw new TechnicalException(PHONE_ERROR_MSG);
        }
    }

    private static void validateEmail(String email) throws TechnicalException {
        if (email == null || email.isEmpty() || email.length() > EMAIL_SIZE) {
            throw new TechnicalException(EMAIL_ERROR_MSG);
        }
        String regex = "\\w+@\\w+\\.[a-z]{2,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (! m.matches()) {
            throw new TechnicalException(EMAIL_ERROR_MSG);
        }
    }

    private static boolean isStringValid(String str, int size) {
        return str != null && !str.isEmpty() && str.length() <= size;
    }

    private static boolean isSelectedElem(Integer id) {
        return id != null && id != 0;
    }

   

    

    

    

    

    

    

    
}
