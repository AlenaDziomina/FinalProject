/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author User
 */
public interface GenericLoadQuery {
    public abstract <T> List<T> query(String query, Object[] params, int pageSize, Connection conn, Params.RowMapper<T> mapper) throws DaoException;
    
}
