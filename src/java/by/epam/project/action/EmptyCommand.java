package by.epam.project.action;

import by.epam.project.manager.ConfigurationManager;

/**
 * Used in case of an error name of the command or a direct appeal to the 
 * controller for redirecting on login page
 * @author Helena.Grouk
 */
public class EmptyCommand implements ActionCommand{
    @Override
    public String execute(SessionRequestContent request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
