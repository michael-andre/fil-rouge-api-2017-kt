package fr.centralesupelec.sio.endpoints

import fr.centralesupelec.sio.data.MoviesRepository
import fr.centralesupelec.sio.endpoints.utils.writeError
import fr.centralesupelec.sio.endpoints.utils.writeJson
import fr.centralesupelec.sio.model.Movie
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * A data servlet to access a single [Movie] entity.
 */
@WebServlet(urlPatterns = ["/movies/*"])
class MovieServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        val id = req.pathInfo.substring(1).toLongOrNull()
        if (id == null) {
            resp.writeError("Invalid id", HttpServletResponse.SC_NOT_FOUND)
            return
        }

        val movie = MoviesRepository.sharedInstance.getMovie(id)
        if (movie != null) {
            resp.writeJson(movie)
        } else {
            resp.writeError("Movie not found", HttpServletResponse.SC_NOT_FOUND)
        }
    }

}
