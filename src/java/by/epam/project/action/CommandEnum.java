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
    GOLOGIN {
        {
            this.command = new GoLoginCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
            }
    };
    
    ActionCommand command;
    
    public ActionCommand getCurrentCommand() {
        return command;
    }
}