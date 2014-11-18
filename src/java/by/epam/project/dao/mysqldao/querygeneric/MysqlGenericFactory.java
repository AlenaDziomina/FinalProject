package by.epam.project.dao.mysqldao.querygeneric;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;

/**
 * Factory of generator objects of messages to MySQL database according to its
 * specified content.
 * @author Helena.Grouk
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
