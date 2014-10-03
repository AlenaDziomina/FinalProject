/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.exception.QueryExecutionException;
import by.epam.project.dao.query.TypedQuery;
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
    
    public static final String DB_ROLE = "role";
    public static final String DB_ROLE_ID_ROLE = "id_role";
    public static final String DB_ROLE_NAME_ROLE = "role_name";
    
    public static final String DAO_ID_ROLE = "idRole";
    public static final String DAO_ROLE_NAME = "nameRole";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_ROLE + "(" 
            + DB_ROLE_NAME_ROLE + ") values (?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_ROLE;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_ROLE + " set ";

    public static final Role createBean(Criteria criteria) {
        
        Role bean = new Role();
        bean.setIdRole((Integer)criteria.getParam(DAO_ID_ROLE));
        bean.setRoleName((String)criteria.getParam(DAO_ROLE_NAME));
        return bean;
    }
    
    @Override
    public List<Integer> save(List<Role> beans, GenericSaveQuery saveDao, Connection conn) throws QueryExecutionException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Role bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getRoleName();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException("Role not saved.",ex);
        }
    }

    @Override
    public List<Role> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws QueryExecutionException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_ROLE, DB_ROLE_ID_ROLE, criteria, paramList, sb, separator);
                append(DAO_ROLE_NAME, DB_ROLE_NAME_ROLE, criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = LOAD_QUERY;
        } else {
            queryStr = LOAD_QUERY + queryStr;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, new RowMapper<Role>() {
                @Override
                public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Role bean = new Role();
                    bean.setIdRole(rs.getInt(DB_ROLE_ID_ROLE));
                    bean.setRoleName(rs.getString(DB_ROLE_NAME_ROLE));
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException("Role not loaded.", ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws QueryExecutionException {        
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_ROLE_NAME, DB_ROLE_NAME_ROLE, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_ROLE, DB_ROLE_ID_ROLE, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new QueryExecutionException("Role not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
