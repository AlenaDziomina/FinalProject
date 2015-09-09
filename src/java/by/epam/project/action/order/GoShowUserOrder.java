package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 * Class of command of displaying the page of users order object list
 * @author Helena.Grouk
 */
public class GoShowUserOrder extends OrderCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.userorder");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowOrder(request);
        formUserOrderList(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowOrder(request);
        }
        return page;
    }

    /**
     * Find the list of user orders and save it as the attribute of session.
     * Also determine and store in session attributes display options of order
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the
     * exceptions of logic layer
     */
    private void formUserOrderList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            criteria.addParam(DAO_ID_USER, user.getIdUser());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }

        Short orderStatus = getOrderStatus(request);
        if (orderStatus != null) {
            criteria.addParam(DAO_ORDER_STATUS, orderStatus);
        }

        try {
            AbstractLogic orderLogic = LogicFactory.getInctance(LogicType.ORDERLOGIC);
            List<Order> orders = orderLogic.doGetEntity(criteria);
            request.setSessionAttribute(JSP_ORDER_LIST, orders);
            ObjList<Order> list = new ObjList<>(orders);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
}
