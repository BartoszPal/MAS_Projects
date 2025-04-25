import Enums.MyStatus;

import java.util.Objects;

public class FilmCopy extends ObjectPlus {
    private MyStatus status;
    private Film film;

    private FilmCopy(Film film, MyStatus status) {
        setFilm(film);
        setStatus(status);
    }

    public static FilmCopy createFilmCopy(Film film, MyStatus status) {
        if (film == null) {
            throw new IllegalArgumentException("The given film does not exist!");
        }
        return new FilmCopy(film, status);
    }

    public void destroyFilmCopy() {
        if (this.film != null) {
            this.film.removeFilmCopy(this);
            this.film = null;
            ObjectPlus.removeFromExtent(this);
        }
    }

    public MyStatus getStatus() {
        return status;
    }

    public void setStatus(MyStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        this.status = status;
    }

    public Film getFilm() {
        return film;
    }

    private void setFilm(Film film) {
        if (film == null) {
            throw new IllegalArgumentException("Film cannot be null");
        }
        this.film = film;
    }

    @Override
    public String toString() {
        return "FilmCopy{" +
                "status=" + status +
                '}';
    }
// todo trzeba dodac jakies id bo jeden film ma takie same kopie i jak mam y equals to set w film ma tylko jeden element
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FilmCopy filmCopy = (FilmCopy) o;
//        return status == filmCopy.status && Objects.equals(film, filmCopy.film);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(status, film);
    }
}
