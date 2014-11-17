package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of command of displaying the tourists list of current tour.
 * @author Helena.Grouk
 */
public class ShowTourTourists extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tourists");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowOrder(request);
        formOrderList(request);
        formTouristsList(request);
        return page;
    }

    /**
     * Find the list of tourists and save it as the attribute of session.
     * Also determine and store in session attributes display options of tour 
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    private void formTouristsList(SessionRequestContent request) {
        List<Order> orders = (List<Order>) request.getSessionAttribute(JSP_ORDER_LIST);
        List<Tourist> tourists = new ArrayList();
        if (orders != null && !orders.isEmpty()) {
            for (Order order: orders) {
                tourists.addAll(order.getTouristCollection());
            }
            request.setSessionAttribute(JSP_CURRENT_TOUR, orders.get(0).getTour());
        }
        request.setSessionAttribute(JSP_TOURIST_LIST, tourists);
    }
}
