package fr.centralesupelec.sio.model

import java.util.*

/**
 * An entity class for a movie.
 */
data class Movie(
    var id: Long,
    var title: String,
    var genres: EnumSet<MovieGenre>
)