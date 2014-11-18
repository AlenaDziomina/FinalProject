package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.DirectionStayHotel;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.LinkDirectionCity;
import by.epam.project.entity.LinkDirectionCountry;
import by.epam.project.entity.Tour;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.exception.DaoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author User
 */
class DirectionLogic extends AbstractLogic {

    @Override
    List<Direction> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        List<Direction> directions = dao.showDirections(criteria);
        Direction.NameComparator comparator = new Direction.NameComparator();
        Collections.sort(directions, comparator);
        fillDirections(directions, dao);
        getTourCollection(directions, dao, criteria);
        return directions;
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDirection = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        if (idDirection != null) {
            return updateDirection(criteria, dao);
        } else {
            return createDirection(criteria, dao);
        }
    }

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_DIRECTION_STATUS, DELETED);
        Integer res = updateDirection(criteria, dao);
        List<Direction> list = getEntity(criteria, dao);
        for (Direction d : list) {
            List<Tour> tours = (List<Tour>) d.getTourCollection();
            for (Tour t : tours) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_TOUR, t.getIdTour());
                new TourLogic().deleteEntity(crit, dao);
            }
        }
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_DIRECTION_STATUS, ACTIVE);
        Integer res = updateDirection(criteria, dao);
        return res;
    }

    public void fillDirections(List<Direction> directions, AbstractDao dao) throws DaoException {
        getCountryCollection(dao, directions);
        getCityCollection(dao, directions);
        getStayHotelCollection(dao, directions);
        getDescription(dao, directions);
        getTransMode(dao, directions);
        getTourType(dao, directions);
    }

    private Integer createDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        List<Integer> res =  dao.createNewDirection(criteria);
        if (res != null && !res.isEmpty()) {
            Integer idDirection = res.get(0);
            criteria.addParam(DAO_ID_DIRECTION, idDirection);
            dao.createNewDirectionCountryLinks(criteria);
            dao.createNewDirectionCityLinks(criteria);
            dao.createNewDirectionStayHotels(criteria);
            return idDirection;
        } else {
            throw new DaoException("Error in hotel query.");
        }
    }

    private Integer updateDirection(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDirection = (Integer) criteria.getParam(DAO_ID_DIRECTION);
        if (idDirection != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DIRECTION, idDirection);
            dao.updateDirection(beans, criteria);
        }
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, idDirection);
            dao.updateDirectionCountryLinks(crit, criteria);
            dao.updateDirectionCityLinks(crit, criteria);
            dao.updateDirectionStayHotels(crit, criteria);
        }
        return idDirection;
    }

    private void getTransMode(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_TRANSMODE, dir.getTransMode().getIdMode());
            List<TransMode> mode = dao.showTransModes(crit);
            dir.setTransMode(mode.get(0));
        }
    }

    private void getTourType(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_TOURTYPE, dir.getTourType().getIdTourType());
            List<TourType> type = dao.showTourTypes(crit);
            dir.setTourType(type.get(0));
        }
    }

    private void getDescription(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DESCRIPTION, dir.getDescription().getIdDescription());
            List<Description> desc = dao.showDescriptions(crit);
            dir.setDescription(desc.get(0));
        }
    }

    private void getStayHotelCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            List<DirectionStayHotel> stays = dao.showDirectionStayHotel(crit);
            dir.setStayCollection(getHotelInfo(dao, stays));
        }
    }

    private List<DirectionStayHotel> getHotelInfo(AbstractDao dao, List<DirectionStayHotel> stays) throws DaoException {
        for (DirectionStayHotel st : stays) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_HOTEL, st.getHotel().getIdHotel());
            List<Hotel> hotels = dao.showHotels(crit);
            if (hotels != null && !hotels.isEmpty()) {
                st.setHotel(hotels.get(0));
                crit.addParam(DAO_ID_CITY, st.getHotel().getCity().getIdCity());
                List<City> cities = dao.showCities(crit);
                if (cities != null && !cities.isEmpty()) {
                    st.getHotel().setCity(cities.get(0));
                }
            }
        }
        DirectionStayHotel.NameHotelComparator comparator = new DirectionStayHotel.NameHotelComparator();
        Collections.sort(stays, comparator);
        return stays;
    }

    private void getCityCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            List<LinkDirectionCity> links = dao.showLinkDirectionCity(crit);
            dir.setCityCollection(getCityInfo(dao, links));
        }
    }

    private List<City> getCityInfo(AbstractDao dao, List<LinkDirectionCity> links) throws DaoException {
        List<City> list = new ArrayList<>();
        for (LinkDirectionCity link : links) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_CITY, link.getIdCity());
            List<City> cities = dao.showCities(crit);
            if (cities != null) {
                list.addAll(cities);
            }
        }
        City.NameComparator comparator = new City.NameComparator();
        Collections.sort(list, comparator);
        return list;
    }

    private void getCountryCollection(AbstractDao dao, List<Direction> directions) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            List<LinkDirectionCountry> links = dao.showLinkDirectionCountry(crit);
            dir.setCountryCollection(getCountryInfo(dao, links));
        }
    }

    private List<Country> getCountryInfo(AbstractDao dao, List<LinkDirectionCountry> links) throws DaoException {
        List<Country> list = new ArrayList<>();
        for (LinkDirectionCountry link : links) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_COUNTRY, link.getIdCountry());
            List<Country> countries = dao.showCountries(crit);
            if (countries != null) {
                list.addAll(countries);
            }
        }
        Country.NameComparator comparator = new Country.NameComparator();
        Collections.sort(list, comparator);
        return list;
    }

    private void getTourCollection(List<Direction> directions, AbstractDao dao, Criteria criteria) throws DaoException {
        for (Direction dir : directions) {
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_DIRECTION, dir.getIdDirection());
            crit.addParam(DAO_TOUR_STATUS, criteria.getParam(DAO_TOUR_STATUS));
            crit.addParam(DAO_TOUR_DATE_FROM, criteria.getParam(DAO_TOUR_DATE_FROM));
            crit.addParam(DAO_TOUR_DATE_TO, criteria.getParam(DAO_TOUR_DATE_TO));
            List<Tour> list = dao.showTours(crit);
            Tour.DateComparator comparator = new Tour.DateComparator();
            Collections.sort(list, comparator);
            dir.setTourCollection(list);
        }
    }
}
