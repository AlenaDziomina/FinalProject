package by.epam.project.action;

import by.epam.project.manager.MessageManager;

/**
 * Class-factory of objects corresponding the command
 * @author Helena.Grouk
 */
public abstract class CommandFactory {
    
    /**
     * Create a command object corresponding to the command name.
     * @param request parameters and attributes of the request and the session
     * @return object of command
     */
    public static ActionCommand defineCommand(SessionRequestContent request){

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        
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
