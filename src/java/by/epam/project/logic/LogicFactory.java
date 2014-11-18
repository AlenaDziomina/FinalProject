package by.epam.project.logic;

import by.epam.project.exception.TechnicalException;

/**
 *
 * @author User
 */
public class LogicFactory {
    public static AbstractLogic getInctance(LogicType type) throws TechnicalException {
        switch(type) {
            case CITYLOGIC: return new CityLogic();
            case COUNTRYLOGIC: return new CountryLogic();
            case DIRECTIONLOGIC: return new DirectionLogic();
            case HOTELLOGIC: return new HotelLogic();
            case ORDERLOGIC: return new OrderLogic();
            case SEARCHLOGIC: return new SearchLogic();
            case TOURLOGIC: return new TourLogic();
            case TOURTYPELOGIC: return new TourTypeLogic();
            case TOURISTLOGIC: return new TouristLogic();
            case TRANSMODELOGIC: return new TransModeLogic();
            case USERLOGIC: return new UserLogic();
            default: throw new TechnicalException(type.toString());
        }
    }
}
