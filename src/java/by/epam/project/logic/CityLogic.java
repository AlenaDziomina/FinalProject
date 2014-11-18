package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Helena.Grouk
 */
class CityLogic extends AbstractLogic {

    @Override
    List<City> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<City> cities = dao.findCities(criteria);
        City.NameComparator comparator = new City.NameComparator();
        Collections.sort(cities, comparator);
        fillCities(cities, dao);
        return cities;
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        if (idCity != null) {
            return updateCity(criteria, dao);
        } else {
            return createCity(criteria, dao);
        }
    }

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_CITY_STATUS, DELETED);
        Integer res = updateCity(criteria, dao);
        List<City> list = getEntity(criteria, dao);
        for (City c : list) {
            List<Hotel> hotels = (List<Hotel>) c.getHotelCollection();
            for (Hotel h : hotels) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_HOTEL, h.getIdHotel());
                new HotelLogic().deleteEntity(crit, dao);
            }
        }
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_CITY_STATUS, ACTIVE);
        Integer res = updateCity(criteria, dao);
        return res;
    }

    private void fillCities(List<City> cities, AbstractDao dao) throws DaoException {
        if (cities != null) {
            for (City city : cities) {
                Criteria descCrit = new Criteria();
                descCrit.addParam(DAO_ID_DESCRIPTION, city.getDescription().getIdDescription());
                List<Description> desc = dao.findDescriptions(descCrit);
                if (!desc.isEmpty()) {
                    city.setDescription(desc.get(0));
                }

                Criteria countryCrit = new Criteria();
                countryCrit.addParam(DAO_ID_COUNTRY, city.getCountry().getIdCountry());
                List<Country> countries = dao.findCountries(countryCrit);
                if (!countries.isEmpty()) {
                    city.setCountry(countries.get(0));
                }

                Criteria hotelCrit = new Criteria();
                hotelCrit.addParam(DAO_ID_CITY, city.getIdCity());
                List<Hotel> hotels = dao.findHotels(hotelCrit);
                Hotel.NameComparator comparator = new Hotel.NameComparator();
                Collections.sort(hotels, comparator);
                city.setHotelCollection(hotels);
            }
        }
    }

    private Integer createCity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.createNewCity(criteria).get(0);
    }

    private Integer updateCity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
        }
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        if (idCity != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_CITY, criteria.getParam(DAO_ID_CITY));
            dao.updateCity(beans, criteria);
        }
        return idCity;
    }
}
