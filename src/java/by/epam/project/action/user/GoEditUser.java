package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of user object editing.
 * @author Helena.Grouk
 */
public class GoEditUser implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edituser");
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
}
