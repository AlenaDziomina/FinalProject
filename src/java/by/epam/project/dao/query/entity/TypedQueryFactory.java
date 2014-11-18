package by.epam.project.dao.query.entity;

import by.epam.project.dao.query.QueryType;
import by.epam.project.dao.query.TypedQuery;
import by.epam.project.exception.DaoInitException;

/**
 *
 * @author Helena.Grouk
 */
public class TypedQueryFactory {
    public static TypedQuery getInctance(QueryType type) throws DaoInitException {
        switch(type) {
            case CITYQUERY: return new CityQuery();
            case COUNTRYQUERY: return new CountryQuery();
            case DESCRIPTIONQUERY: return new DescriptionQuery();
            case DIRECTIONCITYQUERY: return new DirectionCityQuery();
            case DIRECTIONCOUNTRYQUERY: return new DirectionCountryQuery();
            case DIRECTIONQUERY: return new DirectionQuery();
            case DIRECTIONSTAYHOTELQUERY: return new DirectionStayHotelQuery();
            case HOTELQUERY: return new HotelQuery();
            case ORDERQUERY: return new OrderQuery();
            case ROLEQUERY: return new RoleQuery();
            case SEARCHQUERY: return new SearchQuery();
            case TOURQUERY: return new TourQuery();
            case TOURTYPEQUERY: return new TourTypeQuery();
            case TOURISTQUERY: return new TouristQuery();
            case TRANSMODEQUERY: return new TransModeQuery();
            case USERQUERY: return new UserQuery();
            default: throw new DaoInitException(type.toString());
        }
    }
}
