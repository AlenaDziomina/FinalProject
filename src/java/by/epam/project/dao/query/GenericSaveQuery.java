/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import by.epam.project.dao.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author User
 */
public interface GenericSaveQuery {
    public abstract <T> List<Integer> query(String query, Connection conn, Params params) throws DaoException;
}
