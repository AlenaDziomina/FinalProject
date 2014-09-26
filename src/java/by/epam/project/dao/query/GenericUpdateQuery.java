/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import by.epam.project.dao.DaoException;
import java.sql.Connection;

/**
 *
 * @author User
 */
public interface GenericUpdateQuery {
    
    public int query(String query, Object[] params, Connection conn) throws DaoException;
    
}
