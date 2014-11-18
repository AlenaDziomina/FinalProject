/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.mysqldao.querygeneric;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;

/**
 *
 * @author User
 */
public class MysqlGenericFactory {
    
    public static GenericLoadQuery getLoadInstance() {
        return new MysqlGenericLoadQuery();
    }
    
    public static GenericSaveQuery getSaveInstance() {
        return new MysqlGenericSaveQuery();
    }

    public static GenericUpdateQuery getUpdateInstance() {
        return new MysqlGenericUpdateQuery();
    }
    
    public static GenericDeleteQuery getDeleteInstance() {
        return new MysqlGenericDeleteQuery();
    }
    
}
