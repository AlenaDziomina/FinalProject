/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class HotelLogic extends AbstractLogic {
    
    @Override
    List<Hotel> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Hotel> hotels = dao.showHotels(criteria);
        fillHotels(hotels, dao);
        return hotels;   
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idHotel = (Integer) criteria.getParam(DAO_ID_HOTEL);
        if (idHotel != null) {
            return updateHotel(criteria, dao);
        } else {      
            return createHotel(criteria, dao);
        }
    }
    
    private static void fillHotels(List<Hotel> hotels, AbstractDao dao) throws DaoException {
        if (hotels != null) {
            for (Hotel hotel : hotels) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DESCRIPTION, hotel.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(crit1);
                if (!desc.isEmpty()) {
                    hotel.setDescription(desc.get(0));
                }
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_CITY, hotel.getCity().getIdCity());
                List<City> cities = dao.showCities(crit2);
                if (! cities.isEmpty()) {
                    hotel.setCity(cities.get(0));
                }
            }
        }
    }
    
    private static Integer createHotel(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.createNewHotel(criteria).get(0);   
    }
    
    private static Integer updateHotel(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
        }
        Integer idHotel = (Integer) criteria.getParam(DAO_ID_HOTEL);
        if (idHotel != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_HOTEL, idHotel);
            dao.updateHotel(beans, criteria);
        }
        return idHotel;
    }
    
    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_HOTEL_STATUS, DELETED);
        Integer res = updateHotel(criteria, dao);
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_HOTEL_STATUS, ACTIVE);
        Integer res = updateHotel(criteria, dao);
        return res;
    }
}

