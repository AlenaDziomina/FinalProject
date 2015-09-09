package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.generic.GenericDeleteQuery;
import by.epam.project.dao.query.generic.GenericUpdateQuery;
import by.epam.project.dao.query.generic.GenericSaveQuery;
import by.epam.project.dao.query.generic.GenericLoadQuery;
import by.epam.project.dao.query.Criteria;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Appender;
import by.epam.project.dao.query.Params;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Description;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of description query forming.
 * @author Helena.Grouk
 */
class DescriptionQuery implements TypedQuery<Description>{
    private static final String ERR_DESCRIPTION_SAVE = "Description not saved.";
    private static final String ERR_DESCRIPTION_LOAD = "Description not loaded.";
    private static final String ERR_DESCRIPTION_UPDATE = "Description not updated.";
    private static final String ERR_NOT_SUPPORTED = "Not supported.";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String COMMA = " , ";
    private static final String DB_DESCRIPTION = "description";
    private static final String DB_DESCRIPTION_ID_DESCRIPTION = "id_description";
    private static final String DB_DESCRIPTION_TEXT = "text";
    private static final String SAVE_QUERY =
            "Insert into " + DB_DESCRIPTION + " (" + DB_DESCRIPTION_TEXT
            + ") values (?);";
    private static final String LOAD_QUERY =
            "Select * from " + DB_DESCRIPTION;
    private static final String UPDATE_QUERY =
            "Update " + DB_DESCRIPTION + " set ";

    @Override
    public List<Integer> save(List<Description> beans, GenericSaveQuery saveGeneric, Connection conn) throws DaoQueryException {
        try {
            return saveGeneric.sendQuery(SAVE_QUERY, conn, Params.fill(beans, (Description bean) -> {
                Object[] objects = new Object[1];
                objects[0] = bean.getText();
                return objects;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException(ERR_DESCRIPTION_SAVE, ex);
        }
    }

    @Override
    public List<Description> load(Criteria criteria, GenericLoadQuery loadGeneric, Connection conn) throws DaoQueryException {
        int pageSize = 10;
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(WHERE);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() {
                Appender.append(DAO_ID_DESCRIPTION, DB_DESCRIPTION_ID_DESCRIPTION, criteria, paramList, sb, AND);
                Appender.append(DAO_DESCRIPTION_TEXT, DB_DESCRIPTION_TEXT, criteria, paramList, sb, AND);
                if (paramList.isEmpty()) {
                    return LOAD_QUERY;
                } else {
                    return sb.insert(0, LOAD_QUERY).toString();
                }
            }
        }.mapQuery();

        try {
            return loadGeneric.sendQuery(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Description bean = new Description();
                bean.setIdDescription(rs.getInt(DB_DESCRIPTION_ID_DESCRIPTION));
                bean.setText(rs.getString(DB_DESCRIPTION_TEXT));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DESCRIPTION_LOAD, ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateGeneric, Connection conn) throws DaoQueryException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(UPDATE_QUERY);
        String queryStr = new QueryMapper() {
            @Override
            public String mapQuery() {
                Appender.append(DAO_DESCRIPTION_TEXT, DB_DESCRIPTION_TEXT, criteria, paramList1, sb, COMMA);
                sb.append(WHERE);
                Appender.append(DAO_ID_DESCRIPTION, DB_DESCRIPTION_ID_DESCRIPTION, beans, paramList2, sb, AND);
                return sb.toString();
            }
        }.mapQuery();
        paramList1.addAll(paramList2);

        try {
            return updateGeneric.sendQuery(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException(ERR_DESCRIPTION_UPDATE, ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteGeneric, Connection conn) throws DaoQueryException {
        throw new DaoQueryException(ERR_NOT_SUPPORTED);
    }
}
