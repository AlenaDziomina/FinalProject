package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;
   
/**
 * Class of command of displaying the page of login user.
 * @author Helena.Grouk
 */
public class GoLoginCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request){
        String page = ConfigurationManager.getProperty("path.page.login");
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
}
