/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.ProcessSavedParameters.resaveParams;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoCreateNewTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        request.setSessionAttribute(JSP_PAGE, page);
        cleanSession(request);
        resaveParams(request);
        return page;
    }
    
}
