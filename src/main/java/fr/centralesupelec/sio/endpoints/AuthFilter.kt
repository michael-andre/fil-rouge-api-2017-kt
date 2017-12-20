package fr.centralesupelec.sio.endpoints

import fr.centralesupelec.sio.endpoints.utils.writeError
import java.io.IOException
import java.util.regex.Pattern
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * A filter to check that all requests to the API are authenticated with a valid token.
 */
// TODO: Configure the filter to match ALL endpoints (except for the login endpoint)
@WebFilter(urlPatterns = ["/movies"])
class AuthFilter : Filter {

    private val headerPattern = Pattern.compile("Bearer (.+)")

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {

        val auth = (servletRequest as HttpServletRequest).getHeader("Authorization")
        if (auth.isNullOrEmpty()) {
            (servletResponse as HttpServletResponse).writeError(
                    "Missing Authorization header",
                    HttpServletResponse.SC_UNAUTHORIZED
            )
            return
        }

        val m = headerPattern.matcher(auth)
        if (!m.matches()) {
            (servletResponse as HttpServletResponse).writeError(
                    "Invalid Authorization header",
                    HttpServletResponse.SC_UNAUTHORIZED
            )
            return
        }

        if (!AuthManager.checkAccessToken(m.group(1))) {
            (servletResponse as HttpServletResponse).writeError(
                    "Invalid access token",
                    HttpServletResponse.SC_UNAUTHORIZED
            )
            return
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
    }

}
