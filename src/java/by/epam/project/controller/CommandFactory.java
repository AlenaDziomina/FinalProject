/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.controller;

import by.epam.project.action.ActionCommand;
import by.epam.project.action.CommandEnum;
import by.epam.project.action.EmptyCommand;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public abstract class CommandFactory {
    
    public static ActionCommand defineCommand(SessionRequestContent request){

        // извлечение имени команды из запроса
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            // если команда не задана в текущем запросе
            return new EmptyCommand();
        }
        
        // получение объекта, соответствующего команде
        ActionCommand current;
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new EmptyCommand();
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }       
    
}
