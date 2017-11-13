package fr.centralesupelec.sio.endpoints;

import fr.centralesupelec.sio.data.AccountsRepository;
import fr.centralesupelec.sio.model.Account;
import fr.centralesupelec.sio.model.Token;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/token")
public class AuthServlet extends ApiServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        if (username == null || username.isEmpty()) {
            writeError("Missing username", resp.SC_BAD_REQUEST, resp);
            return;
        }
        if (!EmailValidator.getInstance().isValid(username)) {
            writeError("Invalid username", resp.SC_BAD_REQUEST, resp);
            return;
        }

        String password = req.getParameter("password");
        if (password == null || password.isEmpty()) {
            writeError("Missing password", resp.SC_BAD_REQUEST, resp);
            return;
        }

        Account account = AccountsRepository.getInstance().getAccount(username);
        if (account == null) {
            writeError("Invalid credentials", resp.SC_UNAUTHORIZED, resp);
            return;
        }
        if (!account.getPasswordHash().equals(password)) {
            writeError("Invalid credentials", resp.SC_UNAUTHORIZED, resp);
            return;
        }

        Token token = new Token();
        token.setAccessToken(AuthManager.generateAccessToken(username));
        writeJsonResponse(token, resp);

    }

}
