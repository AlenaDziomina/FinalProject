/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.generic;

import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Helena.Grouk
 */
public interface GenericUpdateQuery {
    List<Integer> query(String query, Object[] params, Connection conn) throws DaoException;

}
