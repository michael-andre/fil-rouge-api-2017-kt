package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Movie;

import java.util.Arrays;
import java.util.List;

class DummyMoviesRepository extends MoviesRepository {

    @Override
    public List<Movie> getMovies() {
        Movie m1 = new Movie();
        m1.setTitle("Lord of the Rings");
        m1.setId(7);
        return Arrays.asList(m1);
    }

    @Override
    public Movie getMovie(long id) {
        return getMovies().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
