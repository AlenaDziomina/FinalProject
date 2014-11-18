/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query.generic;

import by.epam.project.dao.query.Params;
import by.epam.project.exception.DaoException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author User
 */
public interface GenericSaveQuery {
    <T> List<Integer> query(String query, Connection conn, Params params) throws DaoException;
}
