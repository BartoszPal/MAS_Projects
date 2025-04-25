import java.lang.instrument.UnmodifiableModuleException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class FilmStorage extends ObjectPlus {
    private Map<String, Film> moviesByTitle = new TreeMap<>();

    public void addFilm(Film film) {
        String title = film.getTitle().toLowerCase();
        if (moviesByTitle.containsKey(title)) {
            throw new IllegalArgumentException("Film with title (" + title + ") already exists in storage");
        } else if (moviesByTitle.containsValue(film)) {
            throw new IllegalArgumentException("Film already exists in storage");
        }
        moviesByTitle.put(title, film);
        film.setFilmStorage(this);
    }

    public void removeFilm(Film film) {
        String title = film.getTitle().toLowerCase();
        if (!moviesByTitle.containsKey(title)) {
            throw new IllegalArgumentException("Cannot remove. Film with title \"" + title + "\" does not exist in storage");
        }
        moviesByTitle.remove(title);
        film.removeFilmFromFilmStorage();
    }

    public Film findFilmByTitle(String title) {
        title = title.toLowerCase();
        if (!moviesByTitle.containsKey(title)) {
            throw new IllegalArgumentException("Unable to find a movie with title: " + title);
        }
        return moviesByTitle.get(title);
    }

    public Map<String, Film> getMoviesByTitle() {
        return Collections.unmodifiableMap(moviesByTitle);
    }

    @Override
    public String toString() {
        return "FilmStorage{" + moviesByTitle + "}";
    }
}

