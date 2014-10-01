/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

/**
 *
 * @author User
 */
public interface AbstractDao {
    
    static final String PARAM_NAME_ID_USER = "id_user";
    static final String PARAM_NAME_LOGIN = "login";
    static final String PARAM_NAME_PASSWORD = "password";
    static final String PARAM_NAME_ROLE = "role";
    static final String PARAM_NAME_EMAIL = "email";
    static final String PARAM_NAME_PHONE = "phone";
    static final String PARAM_NAME_LOCALE = "locale";
    static final String PARAM_NAME_LANGUAGE = "language";
    static final String PARAM_NAME_DISCOUNT = "discount";
    static final String PARAM_NAME_BALANCE = "balance";
    static final String PARAM_NAME_DATE = "date";
    static final String PARAM_NAME_ID_ROLE = "id_role";
    static final String PARAM_NAME_ID_COUNTRY = "id_country";
    static final String PARAM_NAME_NAME_COUNTRY = "name_country";
    static final String PARAM_NAME_STATUS_COUNTRY = "status_country";
    static final String PARAM_NAME_PICTURE_COUNTRY = "picture_country";
    static final String PARAM_NAME_ID_DESCRIPTION = "id_description";
    static final String PARAM_NAME_TEXT_DESCRIPTION = "text_description";
    static final String PARAM_NAME_ID_CITY = "id_city";
    static final String PARAM_NAME_NAME_CITY = "name_city";
    static final String PARAM_NAME_STATUS_CITY = "status_city";
    static final String PARAM_NAME_PICTURE_CITY = "picture_city";
    static final String PARAM_NAME_ID_HOTEL = "id_hotel";
    static final String PARAM_NAME_NAME_HOTEL = "name_hotel";
    static final String PARAM_NAME_STATUS_HOTEL = "status_hotel";
    static final String PARAM_NAME_STARS_HOTEL = "stars_hotel";
    static final String PARAM_NAME_PICTURE_HOTEL = "picture_hotel";
    
    static final String PARAM_NAME_ID_DIRECTION = "id_direction";
    static final String PARAM_NAME_NAME_DIRECTION = "name_direction";
    static final String PARAM_NAME_PICTURE_DIRECTION = "picture_direction";
    static final String PARAM_NAME_STATUS_DIRECTION = "status_direction";
    static final String PARAM_NAME_TEXT_DIRECTION = "text_direction";
    
    static final String PARAM_NAME_ID_TOUR_TYPE = "id_tour_type";
    static final String PARAM_NAME_NAME_TOUR_TYPE = "name_tour_type";
    static final String PARAM_NAME_ID_MODE = "id_mode";
    static final String PARAM_NAME_NAME_MODE = "name_mode";
    
    static final String PARAM_NAME_ID_STAY = "id_stay";
    static final String PARAM_NAME_STAY_NO = "stay_no";
    static final String PARAM_NAME_STATUS_STAY = "status_stay";
    
    
    
    public abstract void open() throws DaoException;
    
    public abstract void close() throws DaoException;

    public abstract void rollback() throws DaoException;
    
    
    
    
}
