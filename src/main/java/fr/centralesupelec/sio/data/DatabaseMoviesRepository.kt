package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Movie

/**
 * A [MoviesRepository] backed by a database.
 */
internal class DatabaseMoviesRepository : MoviesRepository {

    override fun getMovies(): List<Movie> {
        TODO("Not implemented")
    }

    override fun getMovie(id: Long): Movie {
        TODO("Not implemented")
    }

}
