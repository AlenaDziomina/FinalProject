/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.filter;

import by.epam.project.manager.LocaleManager;
import java.io.IOException;
import java.util.Locale;
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
 * @author User
 */
public class LocaleFilter implements Filter {
    
    private static Locale defLocale;
    
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
        
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            if (defLocale != null) {
                session.setAttribute("locale", defLocale);
            } else {
                Locale loc = response.getLocale();
                session.setAttribute("locale", response.getLocale());
            }
        }
        
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
        String defCountry = filterConfig.getInitParameter("DEF_COUNTRY");
        String defLanguage = filterConfig.getInitParameter("DEF_LANGUAGE");
        
        defLocale = LocaleManager.getLocale(defLanguage, defCountry);
        
    }
    
}