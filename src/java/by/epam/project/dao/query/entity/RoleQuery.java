package by.epam.project.dao.query.entity;

import by.epam.project.exception.DaoException;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.exception.DaoQueryException;
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
    private static final String ERR_ROLE_SAVE = "Role not saved.";
    private static final String ERR_ROLE_LOAD = "Role not loaded.";
    private static final String ERR_ROLE_UPDATE = "Role not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_ROLE = "role";
    private static final String DB_ROLE_ID_ROLE = "id_role";
    private static final String DB_ROLE_NAME_ROLE = "role_name";
    private static final String SAVE_QUERY = 
            "Insert into " + DB_ROLE + " (" 
            + DB_ROLE_NAME_ROLE + ") values (?);";
    private static final String LOAD_QUERY = 
            "Select * from " + DB_ROLE;
    private static final String UPDATE_QUERY = 
            "Update " + DB_ROLE + " set ";
    
    @Override
    public List<Integer> save(List<Role> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Role bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getRoleName();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_ROLE_SAVE, ex);
        }
    }

    @Override
    public List<Role> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ID_ROLE, DB_ROLE_ID_ROLE, criteria, paramList, sb, AND);
                append(DAO_ROLE_NAME, DB_ROLE_NAME_ROLE, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }  
        }.mapQuery();
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Role bean = new Role();
                bean.setIdRole(rs.getInt(DB_ROLE_ID_ROLE));
                bean.setRoleName(rs.getString(DB_ROLE_NAME_ROLE));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_ROLE_LOAD, ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {        
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                append(DAO_ROLE_NAME, DB_ROLE_NAME_ROLE, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                append(DAO_ID_ROLE, DB_ROLE_ID_ROLE, beans, paramList2, sb, AND);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_ROLE_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
    
    public static Role createBean(Criteria criteria) {
        Role bean = new Role();
        bean.setIdRole((Integer)criteria.getParam(DAO_ID_ROLE));
        bean.setRoleName(criteria.getParam(DAO_ROLE_NAME).toString());
        return bean;
    }
}
