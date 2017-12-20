package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Movie
import fr.centralesupelec.sio.model.MovieGenre
import java.util.*

/**
 * A concrete [MoviesRepository] backed by an in-memory list of static [Movie] entities.
 */
// This class is not accessible outside of its package.
internal class DummyMoviesRepository : MoviesRepository {

    // Hold entities in a simple list.
    private val movies = listOf(
            Movie(
                    id = 1,
                    title = "Lord of the Rings: The Return of the King",
                    genres = EnumSet.of(MovieGenre.FANTASY)
            ),
            Movie(
                    id = 2,
                    title = "Star Wars VIII: The Last Jedi",
                    genres = EnumSet.of(MovieGenre.SCIENCE_FICTION)
            ),
            Movie(
                    id = 3,
                    title = "Kingsman 2: The Golden Circle",
                    genres = EnumSet.of(MovieGenre.COMEDY, MovieGenre.ACTION)
            )
    )

    override fun getMovies(): List<Movie> = movies

    override fun getMovie(id: Long): Movie? =
        movies.firstOrNull { it.id == id }
    
}
