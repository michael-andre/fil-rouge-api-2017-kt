package fr.centralesupelec.sio.endpoints;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiServlet extends HttpServlet {

    protected static void writeJsonResponse(Object response, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        gson.toJson(response, resp.getWriter());
    }

}
