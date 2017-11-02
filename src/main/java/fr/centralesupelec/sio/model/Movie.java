package fr.centralesupelec.sio.model;

import java.util.EnumSet;

public class Movie {

    private long id;
    private String title;
    private EnumSet<MovieGenre> genre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumSet<MovieGenre> getGenre() {
        return genre;
    }

    public void setGenre(EnumSet<MovieGenre> genre) {
        this.genre = genre;
    }

}
