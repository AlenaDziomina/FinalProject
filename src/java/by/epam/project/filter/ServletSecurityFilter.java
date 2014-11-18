package by.epam.project.filter;

import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import by.epam.project.dao.ClientType;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Helena.Grouk
 */
public class ServletSecurityFilter implements Filter {

    private String guestPath;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        ClientType type = (ClientType)session.getAttribute(JSP_ROLE_TYPE);
        if (type == null) {
            type = ClientType.GUEST;
        }
        session.setAttribute(JSP_ROLE_TYPE, type);


        chain.doFilter(request, response);

    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        guestPath = filterConfig.getInitParameter("GUEST_PATH");

    }


}
