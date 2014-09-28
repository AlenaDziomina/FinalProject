/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

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
    },;
            
            
    
    private ActionCommand command;
    
    public void setCurrentCommand(ActionCommand command) {
        this.command = command;
    }
    
    public ActionCommand getCurrentCommand() {
        return command;
    }
}