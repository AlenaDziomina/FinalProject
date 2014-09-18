/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public abstract class DaoFactory {
        
    public static AbstractDao getInstance(ClientType clientType) throws DaoException{
        String dbType = ConfigurationManager.getProperty("db.type").toUpperCase();
        switch (dbType) {
            case "MySQL": return MysqlDaoFactory.getInstance(clientType);
            default: throw new DaoException("Announ data source type.");
        }
    }
    
}
