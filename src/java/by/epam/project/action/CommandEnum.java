/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.action.user.RestoreUser;
import by.epam.project.action.user.DeleteUser;
import by.epam.project.action.city.*;
import by.epam.project.action.country.*;
import by.epam.project.action.direction.*;
import by.epam.project.action.hotel.*;
import by.epam.project.action.order.*;
import by.epam.project.action.tour.*;
import by.epam.project.action.user.*;

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
    GOEDITDIRECTION {
        {
            setCurrentCommand(new GoEditDirection());
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
    },
    GOCREATENEWTOUR {
        {
            setCurrentCommand(new GoCreateNewTour());
        }
    },
    SAVEREDACTTOUR {
        {
            setCurrentCommand(new SaveRedactTour());
        }
    },
    GOSHOWTOURS {
        {
            setCurrentCommand(new GoShowTour());
        }
    },
    SHOWTOUR {
        {
            setCurrentCommand(new ShowTour());
        }
    },
    GOEDITTOUR {
        {
            setCurrentCommand(new GoEditTour());
        }
    },
    SEARCHTOUR {
        {
            setCurrentCommand(new SearchTour());
        }
    },
    SHOWPAGE {
        {
            setCurrentCommand(new ShowPage());
        }
    },
    GOBUYTOUR {
        {
            setCurrentCommand(new GoBuyTour());
        }
    },
    GOCREATENEWORDER {
        {
            setCurrentCommand(new GoCreateNewOrder());
        }
    },
    PAYFORORDER {
        {
            setCurrentCommand(new PayForOrder());
        }
    }, 
    GOSHOWUSERORDER {
        {
            setCurrentCommand(new GoShowUserOrder());
        }
    },
    GOSHOWORDERS {
        {
            setCurrentCommand(new GoShowOrders());
        }
    },
    SHOWORDER {
        {
            setCurrentCommand(new ShowOrder());
        }
    },
    GOEDITORDER {
        {
            setCurrentCommand(new GoEditOrder());
        }
    }, 
    SAVEREDACTORDER {
        {
            setCurrentCommand(new SaveRedactOrder());
        }
    }, 
    DELETEORDER {
        {
            setCurrentCommand(new DeleteOrder());
        }
    },
    SHOWUSER {
        {
            setCurrentCommand(new ShowUser());
        }
    }, 
    GOSHOWUSERS {
        {
            setCurrentCommand(new GoShowUsers());
        }
    },
    DELETEUSER {
        {
            setCurrentCommand(new DeleteUser());
        }
    },
    RESTOREUSER {
        {
            setCurrentCommand(new RestoreUser());
        }
    },
    DELETECOUNTRY {
        {
            setCurrentCommand(new DeleteCountry());
        }
    },
    RESTORECOUNTRY {
        {
            setCurrentCommand(new RestoreCountry());
        }
    },
    DELETECITY {
        {
            setCurrentCommand(new DeleteCity());
        }
    },
    RESTORECITY {
        {
            setCurrentCommand(new RestoreCity());
        }
    },
    DELETEHOTEL {
        {
            setCurrentCommand(new DeleteHotel());
        }
    },
    RESTOREHOTEL {
        {
            setCurrentCommand(new RestoreHotel());
        }
    },
    DELETEDIRECTION {
        {
            setCurrentCommand(new DeleteDirection());
        }
    },
    RESTOREDIRECTION {
        {
            setCurrentCommand(new RestoreDirection());
        }
    },
    DELETETOUR {
        {
            setCurrentCommand(new DeleteTour());
        }
    },
    RESTORETOUR {
        {
            setCurrentCommand(new RestoreTour());
        }
    },
    GOEDITUSER {
        {
            setCurrentCommand(new GoEditUser());
        }
    },
    SAVEREDACTUSER {
        {
            setCurrentCommand(new SaveRedactUser());
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