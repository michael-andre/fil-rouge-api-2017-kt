package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Movie;

import java.util.Arrays;
import java.util.List;

class DummyMoviesRepository extends MoviesRepository {

    @Override
    public List<Movie> getMovies() {
        Movie m1 = new Movie();
        m1.setTitle("Lord of the Rings");
        m1.setId(007);
        return Arrays.asList(m1);
    }

}
