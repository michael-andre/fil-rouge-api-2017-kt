package fr.centralesupelec.sio.endpoints

import fr.centralesupelec.sio.data.MoviesRepository
import fr.centralesupelec.sio.endpoints.utils.writeJson
import fr.centralesupelec.sio.model.Movie
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * A servlet to access the list of [Movie]s.
 */
@WebServlet(urlPatterns = ["/movies"])
class MoviesServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        // TODO: Add pagination parameters
        val movies = MoviesRepository.sharedInstance.getMovies()
        resp.writeJson(movies)

    }

}
