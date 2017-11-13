package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Movie;

import java.util.List;

public abstract class MoviesRepository {

    private static MoviesRepository sRepository;

    public static MoviesRepository getInstance() {
        if (sRepository == null) {
            sRepository = new DummyMoviesRepository();
        }
        return sRepository;
    }

    protected MoviesRepository() { }

    public abstract List<Movie> getMovies();

}
