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
    
    public abstract void open() throws DaoException;
    
    public abstract void close() throws DaoException;

    public abstract void rollback() throws DaoException;
    
    
    
    
}
