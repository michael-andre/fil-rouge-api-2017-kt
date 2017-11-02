package fr.centralesupelec.sio.data;

public class DataManager {

    public static MoviesRepository getRepository() {
        return new DummyMoviesRepository();
    }

}
