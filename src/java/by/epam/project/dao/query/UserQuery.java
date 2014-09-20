/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;


import by.epam.project.dao.DaoException;
import by.epam.project.dao.MysqlGenericSaveQuery;
import by.epam.project.dao.query.Params.Mapper;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.BeanInitException;
import by.epam.project.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public class UserQuery implements TypedSaveQuery<User>, TypedLoadQuery<User>{
    
    private static final GenericSaveQuery saveDao = new MysqlGenericSaveQuery();
    private static final GenericLoadQuery loadDao = new MysqlGenericLoadQuery();
    private static final String EM_SAVE_QUERY = "Insert into peoples(f_fio, f_data) values (?, ?);";
    private static final String EM_LOAD_QUERY = "Select * from peoples where f_fio = ? or f_fio = ?;";
    
    public UserQuery(){}
    
    @Override
    public void save(List<User> beans) throws QueryExecutionException {
        
            
        try {
            saveDao.query(EM_SAVE_QUERY, Params.fill(beans, new Mapper<User>() {
                @Override
                public Object[] map(User bean) {
                    Object[] objects = new Object[2];
                    objects[0] = bean.getLogin();
                    objects[1] = bean.getDate();
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
        Object param1 = criteria.getParam("login");
        Object param2 = criteria.getParam("login");
        
        Object[] params = {param1, param2};
        
        try {
            return loadDao.query(EM_LOAD_QUERY, params, pageSize, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User bean = new User();
                    bean.setLogin(rs.getString(2));
                    try {
                        bean.setDate(rs.getString(3));
                    } catch (BeanInitException ex) {
                        //nothing to do
                    }
                    return bean;
                }
            });
        } catch (DaoException ex) {
             throw new QueryExecutionException(ex);
        }

    }
                

            
}

    
    

