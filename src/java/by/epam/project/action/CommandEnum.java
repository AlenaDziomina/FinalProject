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
    GOREDACTCOUNTRY {
        {
            setCurrentCommand(new GoRedactCounry());
        }
    },
    GOREDACTCITY {
        {
            setCurrentCommand(new GoRedactCity());
        }
    },
    GOREDACTHOTEL {
        {
            setCurrentCommand(new GoRedacrHotel());
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