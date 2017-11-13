package fr.centralesupelec.sio.endpoints;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/movies")
public class AuthFilter implements Filter {

    private Pattern HEADER_PATTERN = Pattern.compile("Bearer (.+)");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String auth = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (auth == null || auth.isEmpty()) {
            ApiServlet.writeError("Missing Authorization header", HttpServletResponse.SC_UNAUTHORIZED, (HttpServletResponse) servletResponse);
            return;
        }
        Matcher m = HEADER_PATTERN.matcher(auth);
        if (!m.matches()) {
            ApiServlet.writeError("Invalid Authorization header", HttpServletResponse.SC_UNAUTHORIZED, (HttpServletResponse) servletResponse);
            return;
        }
        if (!AuthManager.checkAccessToken(m.group(1))) {
            ApiServlet.writeError("Invalid access token", HttpServletResponse.SC_UNAUTHORIZED, (HttpServletResponse) servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
