package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Movie;

import java.util.List;

public interface MoviesRepository {

    List<Movie> getMovies();

}
