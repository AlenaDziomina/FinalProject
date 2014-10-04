/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.action.city.GoCreateNewCity;
import by.epam.project.action.country.GoCreateNewCountry;
import by.epam.project.action.direction.GoCreateNewDirection;
import by.epam.project.action.hotel.GoCreateNewHotel;
import by.epam.project.action.city.GoEditCity;
import by.epam.project.action.country.GoEditCountry;
import by.epam.project.action.hotel.GoEditHotel;
import by.epam.project.action.user.GoLoginCommand;
import by.epam.project.action.user.GoRegistrationCommand;
import by.epam.project.action.city.GoShowCity;
import by.epam.project.action.country.GoShowCountry;
import by.epam.project.action.direction.GoShowDirections;
import by.epam.project.action.hotel.GoShowHotel;
import by.epam.project.action.city.IfCitySelected;
import by.epam.project.action.country.IfCountrySelected;
import by.epam.project.action.user.LocalCommand;
import by.epam.project.action.user.LoginCommand;
import by.epam.project.action.user.LogoutCommand;
import by.epam.project.action.user.RegistrationCommand;
import by.epam.project.action.city.SaveRedactCity;
import by.epam.project.action.country.SaveRedactCountry;
import by.epam.project.action.direction.SaveRedactDirection;
import by.epam.project.action.hotel.SaveRedactHotel;
import by.epam.project.action.city.ShowCitiesOfCountry;
import by.epam.project.action.city.ShowCity;
import by.epam.project.action.country.ShowCountry;
import by.epam.project.action.direction.ShowDirection;
import by.epam.project.action.hotel.ShowHotel;

/**
 *
 * @author User
 */
public enum CommandEnum {
    LOCALIZATION {
        {
            setCurrentCommand(new LocalCommand());
        }  
    },
    GOLOGIN {
        {
            setCurrentCommand(new GoLoginCommand());
        }
    },
    LOGIN {
        {
            setCurrentCommand(new LoginCommand());
        }
    },
    LOGOUT {
        {
            setCurrentCommand(new LogoutCommand());
        }
    },
    GETSESSION {
        {
            setCurrentCommand(new GetSessionCommand());
        }
    },
    GOREGISTRATION {
        {
            setCurrentCommand(new GoRegistrationCommand());
        }
    },
    REGISTRATION {
        {
            setCurrentCommand(new RegistrationCommand());
        }
    },
    GOSHOWCOUNTRY {
        {
            setCurrentCommand(new GoShowCountry());
        }
    },
    SHOWCOUNTRY {
        {
            setCurrentCommand(new ShowCountry());
        }
    },
    GOCREATENEWCOUNTRY {
        {
            setCurrentCommand(new GoCreateNewCountry());
        }
    },
    GOEDITCOUNTRY {
        {
            setCurrentCommand(new GoEditCountry());
        }
    },
    SAVEREDACTCOUNTRY {
        {
            setCurrentCommand(new SaveRedactCountry());
        }
    },
    GOSHOWCITY {
        {
            setCurrentCommand(new GoShowCity());
        }
    },
    SHOWCITY {
        {
            setCurrentCommand(new ShowCity());
        }
    },
    GOCREATENEWCITY {
        {
            setCurrentCommand(new GoCreateNewCity());
        }
    },
    GOEDITCITY {
        {
            setCurrentCommand(new GoEditCity());
        }
    },
    SAVEREDACTCITY {
        {
            setCurrentCommand(new SaveRedactCity());
        }
    },
    GOSHOWHOTEL {
        {
            setCurrentCommand(new GoShowHotel());
        }
    },
    SHOWHOTEL {
        {
            setCurrentCommand(new ShowHotel());
        }
    },
    GOCREATENEWHOTEL {
        {
            setCurrentCommand(new GoCreateNewHotel());
        }
    },
    GOEDITHOTEL {
        {
            setCurrentCommand(new GoEditHotel());
        }
    },
    SAVEREDACTHOTEL {
        {
            setCurrentCommand(new SaveRedactHotel());
        }
    },
    SHOWCITIESOFCOUNTRY {
        {
            setCurrentCommand(new ShowCitiesOfCountry());
        }
    },
    GOSHOWDIRECTIONS {
        {
            setCurrentCommand(new GoShowDirections());
        }
    },
    GOCREATENEWDIRECTION {
        {
            setCurrentCommand(new GoCreateNewDirection());
        }
    },
    SAVEREDACTDIRECTION {
        {
            setCurrentCommand(new SaveRedactDirection());
        }
    },
    IFCOUNTRYSELECTED {
        {
            setCurrentCommand(new IfCountrySelected());
        }
    }, 
    IFCITYSELECTED {
        {
            setCurrentCommand(new IfCitySelected());
        }
    },
    SHOWDIRECTION {
        {
            setCurrentCommand(new ShowDirection());
        }
    };
            
            
    
    private ActionCommand command;
    
    public void setCurrentCommand(ActionCommand command) {
        this.command = command;
    }
    
    public ActionCommand getCurrentCommand() {
        return command;
    }
}