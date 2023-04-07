package models;

import java.util.List;
import java.util.Objects;

public class DVD extends Item {
    String director;
    int releaseYear;
    int duration;
    String genre;

    public DVD(int itemId, String name, int numberOfCopies, String director, int releaseYear, int duration, String genre) {
        super(itemId, name, numberOfCopies);
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genre;
    }

    public void setGenres(String genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "DVD{" +
                "id=" + itemId +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", duration=" + duration +
                ", genre=" + genre +
                ", name='" + name + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", numberOfAvailableCopies=" + numberOfAvailableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DVD dvd = (DVD) o;
        return releaseYear == dvd.releaseYear && duration == dvd.duration && director.equals(dvd.director) && genre.equals(dvd.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), director, releaseYear, duration, genre);
    }
}
