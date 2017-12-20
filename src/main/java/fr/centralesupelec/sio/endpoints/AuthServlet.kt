package fr.centralesupelec.sio.endpoints

import fr.centralesupelec.sio.data.AccountsRepository
import fr.centralesupelec.sio.endpoints.utils.writeError
import fr.centralesupelec.sio.endpoints.utils.writeJson
import fr.centralesupelec.sio.model.Token
import org.apache.commons.validator.routines.EmailValidator
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * An authentication servlet for the API.
 * Clients must obtain an access token with this endpoint, then they sign subsequent requests to other data endpoints with it.
 * This servlet MUST NOT be matched by the [AuthFilter]!
 */

@WebServlet(urlPatterns = ["/auth/token"])
class AuthServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {

        val username = req.getParameter("username")
        if (username.isNullOrEmpty()) {
            resp.writeError("Missing username")
            return
        }
        if (!EmailValidator.getInstance().isValid(username)) {
            resp.writeError("Invalid username")
            return
        }

        val password = req.getParameter("password")
        if (password.isNullOrEmpty()) {
            resp.writeError("Missing password")
            return
        }

        // Check that the account exists
        val account = AccountsRepository.sharedInstance.getAccount(username)
        if (account == null) {
            resp.writeError("Invalid credentials", HttpServletResponse.SC_UNAUTHORIZED)
            return
        }
        // Check the password
        // TODO: Better check with a hash
        if (account.passwordHash != password) {
            resp.writeError( "Invalid credentials", HttpServletResponse.SC_UNAUTHORIZED)
            return
        }

        // Generate a successful Token response
        val token = Token(accessToken = AuthManager.generateAccessToken(username))
        resp.writeJson(token)

    }

}
