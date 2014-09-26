/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;


import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params.Mapper;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserQuery implements TypedQuery<User>{
        
   
    private static final String EM_SAVE_QUERY = 
            "Insert into user(id_role, login, password, email, phone, discount, balance, lang) values (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from user u left join role r on (u.id_role = r.id_role) where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from user u left join role r on (u.id_role = r.id_role);";
    private static final String EM_UPDATE_QUERY = 
            "Update user set ";
    
    public UserQuery(){}
    
    @Override
    public void save(List<User> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
    
        try {
            saveDao.query(EM_SAVE_QUERY, Params.fill(beans, new Mapper<User>() {
                @Override
                public Object[] map(User bean) {
                    Object[] objects = new Object[8];
                    objects[0] = bean.getIdRole();
                    objects[1] = bean.getLogin();
                    objects[2] = bean.getPassword();
                    objects[3] = bean.getEmail();
                    objects[4] = bean.getPhone();
                    objects[5] = bean.getDiscount();
                    objects[6] = bean.getBalance();
                    objects[7] = bean.getLanguage();
                    return objects;
                }
            }), conn);
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
        
    }

    @Override
    public List<User> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        
        int pageSize = 10;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_ID_USER, "id_user", criteria, paramList, sb);
                append(PARAM_NAME_LOGIN, "login", criteria, paramList, sb);
                append(PARAM_NAME_PASSWORD, "password", criteria, paramList, sb);
                append(PARAM_NAME_EMAIL, "email", criteria, paramList, sb);
                append(PARAM_NAME_ID_ROLE, "id_role", criteria, paramList, sb);
                append(PARAM_NAME_DISCOUNT, "discount", criteria, paramList, sb);
                append(PARAM_NAME_BALANCE, "balance", criteria, paramList, sb);
                append(PARAM_NAME_PHONE, "phone", criteria, paramList, sb);
                append(PARAM_NAME_LANGUAGE, "lang", criteria, paramList, sb);
                
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User bean = new User();
                    bean.setIdUser(rs.getInt("id_user"));
                    bean.setLogin(rs.getString("login"));
                    bean.setRole(rs.getString("role_name"));
                    bean.setDiscount(rs.getInt("discount"));
                    bean.setBalance(rs.getFloat("balance"));
                    bean.setEmail(rs.getString("email"));
                    bean.setPhone(rs.getString("phone"));
                    bean.setLanguage(rs.getString("lang"));
                    
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }

    }
    

    @Override
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {
        
        int pageSize = 10;
                
        List paramList = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_UPDATE_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_PASSWORD, "password", criteria, paramList, sb);
                append(PARAM_NAME_ID_ROLE, "id_role", criteria, paramList, sb);
                append(PARAM_NAME_DISCOUNT, "discount", criteria, paramList, sb);
                append(PARAM_NAME_BALANCE, "balance", criteria, paramList, sb);
                append(PARAM_NAME_PHONE, "phone", criteria, paramList, sb);
                append(PARAM_NAME_LANGUAGE, "lang", criteria, paramList, sb);
                sb.append(" where ");
                append(PARAM_NAME_ID_USER, "id_user", beans, paramList2, sb);
                append(PARAM_NAME_LOGIN, "login", beans, paramList2, sb);
                append(PARAM_NAME_EMAIL, "email", beans, paramList2, sb);
                
                return sb.toString();
            }  
        }.mapQuery();
        paramList.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }
        
    }

    
                

            
}

    
    

