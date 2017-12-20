package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Movie

/**
 * A data repository to expose movie-related entities.
 */
interface MoviesRepository {

    companion object {
        val sharedInstance: MoviesRepository by lazy { DummyMoviesRepository() }
    }

    /**
     * Query all available movies.
     * @return A list of [Movie] entities.
     */
    fun getMovies(): List<Movie>

    /**
     * @param id: The id of the movie.
     * @return The [Movie] entity, or null of the movie does not exist.
     */
    fun getMovie(id: Long): Movie?

    // TODO: Add other movie-related methods here

}
