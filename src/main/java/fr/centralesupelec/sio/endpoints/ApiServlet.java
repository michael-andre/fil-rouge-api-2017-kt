package fr.centralesupelec.sio.endpoints;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiServlet extends HttpServlet {

    protected static void writeJsonResponse(Object response, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        gson.toJson(response, resp.getWriter());
    }

    protected static void writeError(String reason, int status, HttpServletResponse resp) throws IOException {
        resp.setStatus(status);
        Error error = new Error(reason);
        writeJsonResponse(error, resp);
    }

    private static class Error {

        public String error;

        public Error(String error) {
            this.error = error;
        }

    }

}
