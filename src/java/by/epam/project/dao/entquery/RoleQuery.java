/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author User
 */
public class RoleQuery implements TypedQuery<Role>{
    
    
    private static final String EM_SAVE_QUERY = 
            "Insert into role(role_name) values (?);";
    private static final String EM_LOAD_QUERY = 
            "Select * from role where ";
    private static final String ALL_LOAD_QUERY = 
            "Select * from role;";
    private static final String EM_UPDATE_QUERY = 
            "Update role set ";

    @Override
    public List<Integer> save(List<Role> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(EM_SAVE_QUERY, conn, Params.fill(beans, (Role bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getRoleName();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
    }

    @Override
    public List<Role> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_LOAD_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_ID_ROLE, "id_role", criteria, paramList, sb);
                append(PARAM_NAME_ROLE, "role_name", criteria, paramList, sb);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = ALL_LOAD_QUERY;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, new RowMapper<Role>() {
                @Override
                public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Role bean = new Role();
                    bean.setIdRole(rs.getInt("id_role"));
                    bean.setRoleNAme(rs.getString("role_name"));
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }
    }

    @Override
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {        
        List paramList = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(EM_UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(PARAM_NAME_ROLE, "role_name", criteria, paramList, sb);
                sb.append(" where ");
                append(PARAM_NAME_ID_ROLE, "id_role", beans, paramList2, sb);
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
