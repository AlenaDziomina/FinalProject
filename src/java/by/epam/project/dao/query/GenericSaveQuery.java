/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import by.epam.project.dao.DaoException;

/**
 *
 * @author User
 */
public interface GenericSaveQuery {
    public abstract <T> void query(String query, Params params) throws DaoException;
}
