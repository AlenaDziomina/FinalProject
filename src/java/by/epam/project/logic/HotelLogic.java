/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.ClientType;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Hotel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author User
 */
public class HotelLogic {
    
    public static List<Hotel> getHotels(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = null;
        
        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method = dao.getClass().getMethod("toShowHotels", Criteria.class);
            List<Hotel> hotels = (List<Hotel>) method.invoke(dao, criteria);
            return hotels;   
        } catch (DaoException ex) {
            if (dao != null) {
                dao.rollback();
            }
            throw ex;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        } finally {
            if (dao != null) {
                dao.close();
            } 
        }
        
        
    }

    public static Integer redactHotel(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idHotel = (Integer) criteria.getParam(PARAM_NAME_ID_HOTEL);
        AbstractDao dao = null;

        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method;
            if (idHotel == null) {      
                method = dao.getClass().getMethod("toCreateNewHotel", Criteria.class);
            } else {
                method = dao.getClass().getMethod("toUpdateHotel", Criteria.class);
            }
            Integer currIdHotel = (Integer) method.invoke(dao, criteria);
            return currIdHotel;    
        } catch (DaoException ex) {
            if (dao != null) {
                dao.rollback();
            }
            throw ex;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex ) {
            if (dao != null) {
                dao.rollback();
            }
            return null;
        } finally {
            if (dao != null) {
                dao.close();
            } 
        }
    }
    
}
