package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of user logouting.
 * @author Helena.Grouk
 */
public class LogoutCommand implements ActionCommand{
    @Override
    public String execute(SessionRequestContent request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.sessionInvalidate();
        return page;
    }
}
