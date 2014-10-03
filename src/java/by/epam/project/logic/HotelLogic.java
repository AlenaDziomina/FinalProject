/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
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
public class HotelLogic {
    
    public static List<Hotel> getHotels(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            List<Hotel> hotels = dao.showHotels(criteria);
            fillHotels(hotels, dao);
            return hotels;   
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }

    public static Integer redactHotel(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idHotel = (Integer) criteria.getParam(DAO_ID_HOTEL);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();

        try {
            if (idHotel == null) {      
                return createHotel(criteria, dao);
            } else {
                return updateHotel(criteria, dao);
            }
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    private static void fillHotels(List<Hotel> hotels, AbstractDao dao) throws DaoException {
        if (hotels != null) {
            for (Hotel hotel : hotels) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DESCRIPTION, hotel.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(crit1);
                hotel.setDescription(desc.get(0));
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_CITY, hotel.getCity().getIdCity());
                List<City> cities = dao.showCities(crit2);
                hotel.setCity(cities.get(0));
                
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
        Criteria beans1 = new Criteria();
        Criteria beans2 = new Criteria();
        beans1.addParam(DAO_ID_DESCRIPTION, criteria.getParam(DAO_ID_DESCRIPTION));
        beans2.addParam(DAO_ID_HOTEL, criteria.getParam(DAO_ID_HOTEL));
        criteria.remuveParam(DAO_ID_HOTEL);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        Integer idDescription = dao.updateDescription(beans1, criteria).get(0);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.updateHotel(beans2, criteria).get(0);
    }
}

