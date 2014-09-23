/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;


import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Params.Mapper;
import by.epam.project.dao.query.Params.QueryMapper;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserQuery implements TypedSaveQuery<User>, TypedLoadQuery<User>{
        
    private static final GenericSaveQuery saveDao = new MysqlGenericSaveQuery();
    private static final GenericLoadQuery loadDao = new MysqlGenericLoadQuery();
    private static final String EM_SAVE_QUERY = 
            "Insert into user(id_role, login, password, email, phone, discount, balance, lang) values (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from user u left join role r on (u.id_role = r.id_role) where ";
    
    public UserQuery(){}
    
    @Override
    public void save(List<User> beans) throws QueryExecutionException {
    
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
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
        
    }

    @Override
    public List<User> load(Criteria criteria) throws QueryExecutionException {
        
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
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, new RowMapper<User>() {
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
    
//    private String queryMapper(Criteria criteria, List<Object> list){
//        
//        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
//        
//        
//        
//        
//        
//        Object obj = 1;
//        if (null != criteria.getParam(PARAM_NAME_LOGIN) , ){
//            sb.append("login = ?");
//            list.add(sb)
//            flag = true;
//        }
//        if (criteria.getParam(PARAM_NAME_PASSWORD) != null){
//            if (flag) {
//                sb.append(", ");
//            }
//            sb.append("login = ?");
//            flag = true;
//        }
//        
//        if (criteria.getParam(PARAM_NAME_EMAIL) != null){
//            if (flag) {
//                sb.append(", ");
//            }
//            sb.append("email = ?");
//            flag = true;
//        }
//        
//        
//        
//        return null;
//    }

//    private String createSaveQuery(List<User> beans) {
//        StringBuilder sb = new StringBuilder(EM_SAVE_QUERY);
//        User bean = beans.get(0);
//        int flag = 0;
//        if (bean.getIdUser() != null) {
//            sb.append("id_user");
//            flag++;
//        }
//        if (bean.getIdRole() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("id_role");
//            flag++;
//        }
//        if (bean.getLogin() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("login");
//            flag++;
//        }
//        if (bean.getPassword() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("password");
//            flag++;
//        }
//        if (bean.getEmail() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("email");
//            flag++;
//        }
//        if (bean.getDiscount() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("discount");
//            flag++;
//        }
//        if (bean.getBalance() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("balance");
//            flag++;
//        }
//        if (bean.getLanguage() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("lang");
//            flag++;
//        }
//        if (bean.getIdRole() != null) {
//            if (flag > 0) {
//                sb.append(", ");
//            }
//            sb.append("status");
//            flag++;
//        }
//
//        sb.append(") values (");
//        while (flag > 1) {
//            sb.append("?, ");
//            flag--;
//        }
//        if (flag == 1) {
//            sb.append("?);");
//        }
//        
//        return sb.toString();
//       
//       
//    }

    
                

            
}

    
    

