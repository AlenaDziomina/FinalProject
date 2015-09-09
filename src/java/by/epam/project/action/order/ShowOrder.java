package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Order;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class of command of displaying the order selected in order list
 * @author Helena.Grouk
 */
public class ShowOrder extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.order");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        showSelectedOrder(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowOrder(request);
        }
        return page;
    }
    
    /**
     * Determine and store in session attributes current order for 
     * displaying it. It needs selected id in request.
     * @param request parameters and attributes of the request and the session
     */
    private void showSelectedOrder(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Order currOrder = null;
        if (selected != null) {
            Integer idOrder = Integer.decode(selected); 
            if (idOrder != null) {
                List<Order> list = (List<Order>) request.getSessionAttribute(JSP_ORDER_LIST);
                Iterator<Order> it = list.iterator();
                while (it.hasNext() && currOrder == null) {
                    Order order = it.next();
                    if (Objects.equals(order.getIdOrder(), idOrder)) {
                        currOrder = order;
                        request.setAttribute(JSP_CURR_ID_ORDER, idOrder);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_ORDER, currOrder);
    }
}