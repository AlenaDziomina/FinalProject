/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.*;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author User
 */
public class RoleQuery implements TypedQuery<Role>{
    
    private static final String EM_LOAD_QUERY = "Select * from role where role_name = ?;";

    @Override
    public void save(List<Role> beans, GenericSaveQuery saveDao) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> load(Criteria criteria, GenericLoadQuery loadDao) throws QueryExecutionException {
        int pageSize = 10;
        Object param1 = ((ClientType)criteria.getParam(PARAM_NAME_ROLE)).toString();       
        Object[] params = {param1};
        
        try {
            return loadDao.query(EM_LOAD_QUERY, params, pageSize, new RowMapper<Role>() {
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
    public int update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao) throws QueryExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
