package by.mlionik.cafe.filter;

import by.mlionik.cafe.entity.type.RoleType;
import by.mlionik.cafe.manager.ConfigurationManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST, //todo не нужны они все, посмотреть, какие нужны
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE},
        urlPatterns = {"/controller", "/jsp/*"}, servletNames = {"Controller"})
public class SessionFilter implements Filter{
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String MAIN_PAGE_PATH = "path.page.main";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session=request.getSession(false);
        if (session == null) {
            response.sendRedirect(ConfigurationManager.getProperty(INDEX_PAGE_PATH));
            return;
        }
        if (session.getAttribute(SESSION_ROLE) == null)
        {
            session.setAttribute(SESSION_ROLE, RoleType.GUEST);

            session.setAttribute(SESSION_LAST_PAGE,ConfigurationManager.getProperty(MAIN_PAGE_PATH));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
