/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;


import by.epam.project.dao.DaoException;
import by.epam.project.dao.GenericLoadDao;
import by.epam.project.dao.GenericSaveDao;
import by.epam.project.dao.MysqlGenericLoadDao;
import by.epam.project.dao.MysqlGenericSaveDao;
import by.epam.project.dao.query.Params.Mapper;
import by.epam.project.dao.query.Params.RowMapper;
import by.epam.project.entity.BeanInitException;
import by.epam.project.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PersonSaveQuery implements TypedSaveQuery<Person> , TypedLoadQuery<Person>{
    
    private static final GenericSaveDao saveDao = new MysqlGenericSaveDao();
    private static final GenericLoadDao loadDao = new MysqlGenericLoadDao();
    private static final String EM_SAVE_QUERY = "Insert into peoples(f_fio, f_data) values (?, ?);";
    private static final String EM_LOAD_QUERY = "Select * from peoples;";
    
    public PersonSaveQuery(){}
    
    @Override
    public void save(List<Person> beans) throws QueryExecutionException {
        
            
        try {
            saveDao.query(EM_SAVE_QUERY, Params.fill(beans, new Mapper<Person>() {
                @Override
                public Object[] map(Person bean) {
                    Object[] objects = new Object[2];
                    objects[0] = bean.getFio();
                    objects[1] = bean.getDate();
                    return objects;
                }
            }));
        } catch (DaoException ex) {
            throw new QueryExecutionException(ex);
        }
        
    }

    @Override
    public List<Person> load(Criteria criteria) throws QueryExecutionException {
        
        int pageSize = 10;
        Object[] params = new Object[0];
        try {
            return loadDao.query(EM_LOAD_QUERY,params, pageSize, new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Person bean = new Person();
                    bean.setFio(rs.getString(2));
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

    
    

