/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.RoleQuery.DAO_ID_ROLE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Role;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserQuery implements TypedQuery<User>{
    
    public static final String DB_USER = "user";
    public static final String DB_USER_ID_USER = "id_user";
    public static final String DB_USER_ID_ROLE = "id_role";
    public static final String DB_USER_LOGIN = "login";
    public static final String DB_USER_PASSWORD = "password";
    public static final String DB_USER_EMAIL = "email";
    public static final String DB_USER_PHONE = "phone";
    public static final String DB_USER_DISCOUNT = "discount";
    public static final String DB_USER_BALANCE = "balance";
    public static final String DB_USER_LANGUAGE = "lang";
    public static final String DB_USER_STATUS = "status";
    
    public static final String DAO_ID_USER = "idUser";
    public static final String DAO_USER_LOGIN = "login";
    public static final String DAO_USER_PASSWORD = "password";
    public static final String DAO_USER_EMAIL = "email";
    public static final String DAO_USER_PHONE = "phone";
    public static final String DAO_USER_LANGUAGE = "language";
    public static final String DAO_USER_DISCOUNT = "discount";
    public static final String DAO_USER_BALANCE = "balance";
    public static final String DAO_USER_STATUS = "status";
    public static final String DAO_USER_SELECT_FOR_UPDATE = "userSelectForUpdate";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_USER + " (" + DB_USER_LOGIN + ", " 
            + DB_USER_PASSWORD + ", " + DB_USER_EMAIL + ", " 
            + DB_USER_PHONE + ", " + DB_USER_ID_ROLE + ", "
            + DB_USER_LANGUAGE + ", " + DB_USER_DISCOUNT + ", "
            + DB_USER_BALANCE + ") values (?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_USER;

    private static final String UPDATE_QUERY = 
            "Update " + DB_USER + " set ";
    
    private static final String LOCK_QUERY = 
            " for update";
        
    public static User createBean(Criteria criteria) {
        User bean = new User();
        bean.setIdUser((Integer) criteria.getParam(DAO_ID_USER));
        bean.setLogin((String) criteria.getParam(DAO_USER_LOGIN));
        bean.setPassword((Integer) criteria.getParam(DAO_USER_PASSWORD));
        bean.setEmail((String) criteria.getParam(DAO_USER_EMAIL));
        bean.setRole(RoleQuery.createBean(criteria));
        bean.setPhone((String) criteria.getParam(DAO_USER_PHONE));
        bean.setLanguage((String) criteria.getParam(DAO_USER_LANGUAGE));
        bean.setDiscount((Integer) criteria.getParam(DAO_USER_DISCOUNT));
        bean.setBalance((Float) criteria.getParam(DAO_USER_BALANCE));
        bean.setStatus((Short) criteria.getParam(DAO_USER_STATUS));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<User> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (User bean) -> {
                Object[] obj = new Object[8];
                obj[0] = bean.getLogin();
                obj[1] = bean.getPassword();
                obj[2] = bean.getEmail();
                obj[3] = bean.getPhone();
                obj[4] = bean.getRole().getIdRole();
                obj[5] = bean.getLanguage();
                obj[6] = bean.getDiscount();
                obj[7] = bean.getBalance();              
                return obj;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("User not saved.", ex);
        }  
    }

    @Override
    public List<User> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        
        int pageSize = 10;              
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_USER, DB_USER_ID_USER, criteria, paramList, sb, separator);
                append(DAO_USER_LOGIN, DB_USER_LOGIN, criteria, paramList, sb, separator);
                append(DAO_USER_PASSWORD, DB_USER_PASSWORD, criteria, paramList, sb, separator);
                append(DAO_USER_EMAIL, DB_USER_EMAIL, criteria, paramList, sb, separator);
                append(DAO_ID_ROLE, DB_USER_ID_ROLE, criteria, paramList, sb, separator);
                append(DAO_USER_PHONE, DB_USER_PHONE, criteria, paramList, sb, separator);
                append(DAO_USER_LANGUAGE, DB_USER_LANGUAGE, criteria, paramList, sb, separator);
                append(DAO_USER_DISCOUNT, DB_USER_DISCOUNT, criteria, paramList, sb, separator);
                append(DAO_USER_BALANCE, DB_USER_BALANCE, criteria, paramList, sb, separator);
                append(DAO_USER_STATUS, DB_USER_STATUS, criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = LOAD_QUERY;
        } else {
            queryStr = LOAD_QUERY + queryStr;
        }
        Boolean forApdate = (Boolean) criteria.getParam(DAO_USER_SELECT_FOR_UPDATE);
        if (forApdate != null && forApdate) {
            queryStr += LOCK_QUERY;
        }
    
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                User bean = new User();
                bean.setIdUser(rs.getInt(DB_USER_ID_USER));
                bean.setLogin(rs.getString(DB_USER_LOGIN));
                bean.setEmail(rs.getString(DB_USER_EMAIL));
                bean.setRole(new Role(rs.getInt(DB_USER_ID_ROLE)));
                bean.setPhone(rs.getString(DB_USER_PHONE));
                bean.setLanguage(rs.getString(DB_USER_LANGUAGE));
                bean.setDiscount(rs.getInt(DB_USER_DISCOUNT));
                bean.setBalance(rs.getFloat(DB_USER_BALANCE));
                bean.setStatus(rs.getShort(DB_USER_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("User not loaded", ex);
        }
    }
    
    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_USER_PASSWORD, DB_USER_PASSWORD, criteria, paramList1, sb, separator);
                append(DAO_USER_EMAIL, DB_USER_EMAIL, criteria, paramList1, sb, separator);
                append(DAO_ID_ROLE, DB_USER_ID_ROLE, criteria, paramList1, sb, separator);
                append(DAO_USER_PHONE, DB_USER_PHONE, criteria, paramList1, sb, separator);
                append(DAO_USER_LANGUAGE, DB_USER_LANGUAGE, criteria, paramList1, sb, separator);
                append(DAO_USER_DISCOUNT, DB_USER_DISCOUNT, criteria, paramList1, sb, separator);
                append(DAO_USER_BALANCE, DB_USER_BALANCE, criteria, paramList1, sb, separator);
                append(DAO_USER_STATUS, DB_USER_STATUS, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_USER, DB_USER_ID_USER, beans, paramList2, sb, separator);
                append(DAO_USER_LOGIN, DB_USER_LOGIN, beans, paramList2, sb, separator);
                append(DAO_USER_PASSWORD, DB_USER_PASSWORD, beans, paramList2, sb, separator);
                append(DAO_USER_EMAIL, DB_USER_EMAIL, beans, paramList2, sb, separator);
                append(DAO_ID_ROLE, DB_USER_ID_ROLE, beans, paramList2, sb, separator);
                append(DAO_USER_PHONE, DB_USER_PHONE, beans, paramList2, sb, separator);
                append(DAO_USER_LANGUAGE, DB_USER_LANGUAGE, beans, paramList2, sb, separator);
                append(DAO_USER_DISCOUNT, DB_USER_DISCOUNT, beans, paramList2, sb, separator);
                append(DAO_USER_BALANCE, DB_USER_BALANCE, beans, paramList2, sb, separator);
                append(DAO_USER_STATUS, DB_USER_STATUS, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("User not updated.", ex);
        }       
    }      

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    
    

