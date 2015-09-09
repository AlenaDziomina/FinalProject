package by.epam.project.tag;

import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.User;
import by.epam.project.manager.ClientTypeManager;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Helena.Grouk
 */
public class RoleUserTag extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @return
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            if (type == ClientType.USER || type == ClientType.ADMIN) {
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }
}
