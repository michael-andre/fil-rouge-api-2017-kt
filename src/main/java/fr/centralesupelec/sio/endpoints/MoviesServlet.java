package fr.centralesupelec.sio.endpoints;

import com.google.gson.Gson;
import fr.centralesupelec.sio.data.MoviesRepository;
import fr.centralesupelec.sio.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/movies")
public class MoviesServlet extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies = MoviesRepository.getInstance().getMovies();
        writeJsonResponse(movies, resp);
    }

}
