package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of order object editing
 * @author Helena.Grouk
 */
public class GoEditOrder extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittourist");
        request.setSessionAttribute(JSP_PAGE, page);        
        return page;
    }
}
