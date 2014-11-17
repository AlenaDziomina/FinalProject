package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command to delete order object
 * @author Helena.Grouk
 */
public class DeleteOrder extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.order");
        try {
            Criteria criteria = new Criteria();
            Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
            if (order == null) {
                throw new TechnicalException("message.errorNullEntity");
            }
            criteria.addParam(DAO_ID_ORDER, order.getIdOrder());
            criteria.addParam(DAO_ID_USER, order.getUser().getIdUser());
            criteria.addParam(DAO_ORDER_TOURIST_LIST, order.getTouristCollection());
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            Integer resIdOrder = new OrderLogic().doDeleteEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdOrder.toString());
            order.setStatus(DELETED);
            page = new ShowOrder().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorDeleteReason", ex.getMessage());
            request.setAttribute("errorDelete", "message.errorDeleteData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }   
}
