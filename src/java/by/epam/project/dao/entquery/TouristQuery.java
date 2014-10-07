/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.entquery;

import static by.epam.project.dao.entquery.OrderQuery.DAO_ID_ORDER;

import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.query.GenericDeleteQuery;
import by.epam.project.dao.query.GenericLoadQuery;
import by.epam.project.dao.query.GenericSaveQuery;
import by.epam.project.dao.query.GenericUpdateQuery;
import by.epam.project.dao.query.Params;
import static by.epam.project.dao.query.Params.QueryMapper.append;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TouristQuery implements TypedQuery<Tourist>{
    
    public static final String DB_TOURIST = "tourist";
    public static final String DB_TOURIST_ID_TOURIST = "id_tourist";
    public static final String DB_TOURIST_ID_ORDER = "id_order";
    public static final String DB_TOURIST_FNAME = "first_name";
    public static final String DB_TOURIST_MNAME = "middle_name";
    public static final String DB_TOURIST_LNAME = "last_name";
    public static final String DB_TOURIST_BIRTH = "birth_date";
    public static final String DB_TOURIST_PASSPORT = "passport";
    public static final String DB_TOURIST_STATUS = "status";

    public static final String DAO_ID_TOURIST = "idTourist";
    public static final String DAO_TOURIST_FNAME = "firstNameTourist";
    public static final String DAO_TOURIST_MNAME = "middleNameTourist";
    public static final String DAO_TOURIST_LNAME = "lastNameTourist";
    public static final String DAO_TOURIST_BIRTH = "birthDateTourist";
    public static final String DAO_TOURIST_PASSPORT = "passportTourist";
    public static final String DAO_TOURIST_STATUS = "statusTourist";
    
    private static final String SAVE_QUERY = 
            "Insert into " + DB_TOURIST + " (" + DB_TOURIST_ID_ORDER + ", "
            + DB_TOURIST_FNAME + ", " + DB_TOURIST_MNAME + ", " 
            + DB_TOURIST_LNAME + ", " + DB_TOURIST_BIRTH + ", "
            + DB_TOURIST_PASSPORT + ") values (?, ?, ?, ?, ?, ?);";
    
    private static final String LOAD_QUERY = 
            "Select * from " + DB_TOURIST;
    
    private static final String UPDATE_QUERY = 
            "Update " + DB_TOURIST + " set ";

    

    @Override
    public List<Integer> save(List<Tourist> beans, GenericSaveQuery saveDao, Connection conn) throws DaoQueryException {
        try {
            return saveDao.query(SAVE_QUERY, conn, Params.fill(beans, (Tourist bean) -> {
                Object[] obj = new Object[6];
                obj[0] = bean.getOrder().getIdOrder();
                obj[1] = bean.getFirstName();
                obj[2] = bean.getMiddleName();
                obj[3] = bean.getLastName();
                obj[4] = bean.getBirthDate();
                obj[5] = bean.getPassport();
                return obj;
            }));
        } catch (DaoException ex) {
            throw new DaoQueryException("Tourist not saved.", ex);
        }
    }
    
    @Override
    public List<Tourist> load(Criteria criteria, GenericLoadQuery loadDao, Connection conn) throws DaoQueryException {
        int pageSize = 50;
                
        List paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " and ";
                append(DAO_ID_TOURIST, DB_TOURIST_ID_TOURIST, criteria, paramList, sb, separator);
                append(DAO_ID_ORDER, DB_TOURIST_ID_ORDER, criteria, paramList, sb, separator);
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, criteria, paramList, sb, separator);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, criteria, paramList, sb, separator);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, criteria, paramList, sb, separator);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, criteria, paramList, sb, separator);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, criteria, paramList, sb, separator);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, criteria, paramList, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        
        if (paramList.isEmpty()) {
            queryStr = LOAD_QUERY;
        } else {
            queryStr = LOAD_QUERY + queryStr;
        }
        
        try {
            return loadDao.query(queryStr, paramList.toArray(), pageSize, conn, (ResultSet rs, int rowNum) -> {
                Tourist bean = new Tourist();
                bean.setIdTourist(rs.getInt(DB_TOURIST_ID_TOURIST));
                bean.setOrder(new Order(rs.getInt(DB_TOURIST_ID_ORDER)));
                bean.setFirstName(rs.getString(DB_TOURIST_FNAME));
                bean.setMiddleName(rs.getString(DB_TOURIST_MNAME));
                bean.setLastName(rs.getString(DB_TOURIST_LNAME));
                bean.setBirthDate(rs.getDate(DB_TOURIST_BIRTH));
                bean.setPassport(rs.getString(DB_TOURIST_PASSPORT));
                bean.setStatus(rs.getShort(DB_TOURIST_STATUS));
                return bean;
            });
        } catch (DaoException ex) {
             throw new DaoQueryException("Tourist not loaded.", ex);
        }
    }

    @Override
    public List<Integer> update(Criteria beans, Criteria criteria, GenericUpdateQuery updateDao, Connection conn) throws DaoQueryException {
        List paramList1 = new ArrayList<>();
        List paramList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(" where ");
        String queryStr = new Params.QueryMapper() {
            @Override
            public String mapQuery() { 
                String separator = " , ";
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, criteria, paramList1, sb, separator);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, criteria, paramList1, sb, separator);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, criteria, paramList1, sb, separator);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, criteria, paramList1, sb, separator);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, criteria, paramList1, sb, separator);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, criteria, paramList1, sb, separator);
                sb.append(" where ");
                separator = " and ";
                append(DAO_ID_TOURIST, DB_TOURIST_ID_TOURIST, beans, paramList2, sb, separator);
                append(DAO_ID_ORDER, DB_TOURIST_ID_ORDER, beans, paramList2, sb, separator);
                append(DAO_TOURIST_FNAME, DB_TOURIST_FNAME, beans, paramList2, sb, separator);
                append(DAO_TOURIST_MNAME, DB_TOURIST_MNAME, beans, paramList2, sb, separator);
                append(DAO_TOURIST_LNAME, DB_TOURIST_LNAME, beans, paramList2, sb, separator);
                append(DAO_TOURIST_BIRTH, DB_TOURIST_BIRTH, beans, paramList2, sb, separator);
                append(DAO_TOURIST_PASSPORT, DB_TOURIST_PASSPORT, beans, paramList2, sb, separator);
                append(DAO_TOURIST_STATUS, DB_TOURIST_STATUS, beans, paramList2, sb, separator);
                return sb.toString();
            }  
        }.mapQuery();
        paramList1.addAll(paramList2);
        
        try {
            return updateDao.query(queryStr, paramList1.toArray(), conn);
        } catch (DaoException ex) {
             throw new DaoQueryException("Tourist not updated.", ex);
        }
    }

    @Override
    public List<Integer> delete(Criteria criteria, GenericDeleteQuery deleteDao, Connection conn) throws DaoQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
