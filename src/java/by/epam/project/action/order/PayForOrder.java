package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 * Class of command of creation new order and paying for it after confirmation.
 * @author Helena.Grouk
 */
public class PayForOrder extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.createorder");
        try {
            Criteria criteria = new Criteria();
            Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
            putOrderParams(criteria, order);
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            Integer resIdOrder = new OrderLogic().doRedactEntity(criteria);
            List<User> users = new UserLogic().doGetEntity(criteria);
            if (users != null) {
                request.setSessionAttribute(JSP_USER, users.get(0));
            }
            request.setParameter(JSP_SELECT_ID, resIdOrder.toString());
            page = new GoShowUserOrder().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorSaveData", "message.errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
        }       
        return page;
    }
    
    /**
     * Put order parameters in criteria.
     * @param criteria in witch put parameters
     * @param order os witch put parameters
     */
    private void putOrderParams(Criteria criteria, Order order) {
        criteria.addParam(DAO_ORDER_CURR_DISCOUNT, order.getCurrentDiscount());
        criteria.addParam(DAO_ORDER_CURR_PRICE, order.getCurrentPrice());
        criteria.addParam(DAO_ORDER_USER_DISCOUNT, order.getCurrentUserDiscount());
        criteria.addParam(DAO_ORDER_FINAL_PRICE, order.getFinalPrice());
        criteria.addParam(DAO_ORDER_SEATS, order.getSeats());
        criteria.addParam(DAO_ORDER_DATE, order.getOrderDate());
        criteria.addParam(DAO_ORDER_TOURIST_LIST, order.getTouristCollection());
        criteria.addParam(DAO_ID_TOUR, order.getTour().getIdTour());
        criteria.addParam(DAO_ID_USER, order.getUser().getIdUser());
    }
}
