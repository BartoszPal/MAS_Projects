import Enums.Genre;
import Enums.MyStatus;

import java.time.LocalDateTime;
import java.util.*;

public class Film extends ObjectPlus {
    private String title;
    private LocalDateTime releaseDate;
    private Integer minutesDuration;
    private Locale language;
    private Integer ageRating;
    private List<Genre> genre = new ArrayList<>();
    private Double copyPrice;
    private List<Director> directedBy = new ArrayList<>();
    private Set<FilmCopy> filmCopies = new HashSet<>(); //todo czy to nazwac inaczej??
    private List<Rating> rates = new ArrayList<>();
    private FilmStorage filmStorage;

    public Film(String title, LocalDateTime releaseDate, Integer minutesDuration, String language, Integer ageRating, List<Genre> genre, Integer numberOfCopies, Double copyPrice) {
        setTitle(title);
        setReleaseDate(releaseDate);
        setMinutesDuration(minutesDuration);
        setLanguage(language);
        setAgeRating(ageRating);
        setGenre(genre);
        setCopyPrice(copyPrice);
        setFilmCopies(numberOfCopies);
    }

    public Film(String title, Integer minutesDuration, String language, Integer ageRating, List<Genre> genre, Integer numberOfCopies, Double copyPrice) {
        this(title, null, minutesDuration, language, ageRating, genre, numberOfCopies, copyPrice);
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }

    public void setFilmStorage(FilmStorage filmStorage) {
        if (filmStorage == null) {
            throw new IllegalArgumentException("Film storage cannot be empty");
        }else if(this.filmStorage == filmStorage){
            return;
        } else if (this.filmStorage != null) {
            removeFilmFromFilmStorage();
        }
        this.filmStorage = filmStorage;
        this.filmStorage.addFilm(this);
    }

    public void removeFilmFromFilmStorage() {
        if (this.filmStorage == null) {
            throw new IllegalArgumentException("Film storage is empty");
        }
        try {
            this.filmStorage.removeFilm(this);
            this.filmStorage = null;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void destroyFilm() { // usuwanie wszytskich połączeń
        if (!rates.isEmpty()) {
            for (Rating rating : new ArrayList<>(rates)) {
                rating.destroyRating();
            }
            this.rates.clear();
        }
        if (!filmCopies.isEmpty()) {
            for (FilmCopy filmCopy : new ArrayList<>(filmCopies)) {
                filmCopy.destroyFilmCopy();
            }
            this.filmCopies.clear();
        }
        if (!directedBy.isEmpty()) {
            for (Director director : new ArrayList<>(directedBy)) {
                director.removeFilm(this);
            }
            this.directedBy.clear();
        }
        if (this.filmStorage != null) {
            removeFilmFromFilmStorage();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public List<Rating> getRates() {
        return Collections.unmodifiableList(rates);
    }

    public void addDirector(Director director) {
        if (!directedBy.contains(director) && director != null) {
            directedBy.add(director);
            director.addFilm(this);
        }
    }

    public void removeDirector(Director director) {
        if (directedBy.contains(director) && director != null) {
            directedBy.remove(director);
            director.removeFilm(this);
        }
    }

    public void removeRating(Rating rating) {
        if (rating != null && rates.contains(rating)) {
            rates.remove(rating);
            rating.destroyRating();
        }
    }

    public void addRating(Rating rating) {
        if (!rates.contains(rating) && rating.getRatedFilm() == this) {
            rates.add(rating);
        }
    }

    public void addRating(Client client, Integer score, String comment) {
        for (Rating rating : rates) {
            if (rating.getRatedFilm() == this && rating.getRatedBy() == client) {
                throw new IllegalArgumentException("Client already rated this film");
            }
        }
        rates.add(new Rating(client, this, score, comment, LocalDateTime.now()));
    }

    public void createNewFilmCopy() {
        FilmCopy filmCopy = FilmCopy.createFilmCopy(this, MyStatus.AVAILABLE);
        this.filmCopies.add(filmCopy);
    }

    public void removeFilmCopy(FilmCopy filmCopy) {
        if (filmCopy == null) {
            throw new IllegalArgumentException("Film copy cannot be empty");
        } else if (filmCopy.getStatus() == MyStatus.BORROWED) {
            throw new IllegalArgumentException("Film copy is currently borrowed");
        }
        if (this.filmCopies.contains(filmCopy)) {
            this.filmCopies.remove(filmCopy);
            filmCopy.destroyFilmCopy();
        }
    }

    public List<Director> getDirectedBy() {
        return Collections.unmodifiableList(directedBy);
    }

    public Set<FilmCopy> getFilmCopies() {
        return Collections.unmodifiableSet(filmCopies);
    }

    private void setFilmCopies(Integer numberOfCopies) {
        if (numberOfCopies == null) {
            throw new IllegalArgumentException("Number of copies cannot be null");
        } else if (numberOfCopies < 1) {
            throw new IllegalArgumentException("Number of copies cannot be less than 1");
        } else if (filmCopies.isEmpty()) {
            for (int i = 0; i < numberOfCopies; i++) {
                createNewFilmCopy();
            }
        }
    }

    public int getNumberOfCopies() {
        return this.filmCopies.toArray().length;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        if (numberOfCopies == null) {
            throw new IllegalArgumentException("Number of copies cannot be empty");
        } else if (numberOfCopies < 1) {
            throw new IllegalArgumentException("Number of copies cannot be less than 1");
        } else if (Objects.equals(this.filmCopies.size(), numberOfCopies)) {
            throw new IllegalArgumentException("Number of copies is currently set to " + numberOfCopies);
        } else if (numberOfCopies < this.filmCopies.size() && filmCopies.stream().filter(copy -> copy.getStatus() == MyStatus.AVAILABLE).count() < this.filmCopies.size() - numberOfCopies) {
            throw new IllegalArgumentException("Not enough available copies");
        }
        int diff = numberOfCopies - this.filmCopies.size();

        if (!filmCopies.isEmpty()) {
            if (diff > 0) { // wiecej filmów
                for (int i = diff; i > 0; i--) {
                    createNewFilmCopy();
                }
            } else { // mniej filmów
                for (FilmCopy filmCopy : filmCopies) {
                    if (diff < 0 && filmCopy.getStatus() == MyStatus.AVAILABLE) {
                        removeFilmCopy(filmCopy);
                        diff++;
                        if (diff == 0) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public Double getCopyPrice() {
        return copyPrice;
    }

    public void setCopyPrice(Double copyPrice) {
        if (copyPrice == null) {
            throw new IllegalArgumentException("Copy price cannot be empty");
        } else if (copyPrice < 1) {
            throw new IllegalArgumentException("Copy price cannot be less than 1");
        }
        this.copyPrice = copyPrice;
    }

    private boolean isEmpty(String value) {
        return value == null || value.isBlank();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (isEmpty(title)) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMinutesDuration() {
        return minutesDuration;
    }

    public void setMinutesDuration(Integer minutesDuration) {
        if (minutesDuration == null) {
            throw new IllegalArgumentException("Film duration(minutes) cannot be empty");
        } else if (minutesDuration <= 0) {
            throw new IllegalArgumentException("Film duration(minutes) cannot be less than 1");
        }
        this.minutesDuration = minutesDuration;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be empty");
        }
        this.language = language;
    }

    public void setLanguage(String languageCode) {
        if (isEmpty(languageCode)) {
            throw new IllegalArgumentException("Language code cannot be empty");
        }
        Locale locale = Locale.forLanguageTag(languageCode);
        if (locale.getLanguage().isEmpty()) {
            throw new IllegalArgumentException("Invalid language code: " + languageCode);
        }
        this.language = locale;
    }

    public Integer getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(Integer ageRating) {
        if (ageRating == null) {
            throw new IllegalArgumentException("Age rating cannot be empty");
        } else if (ageRating <= 0) {
            throw new IllegalArgumentException("Age rating cannot be less than 1");
        }
        this.ageRating = ageRating;
    }

    public List<Genre> getGenre() {
        return Collections.unmodifiableList(this.genre);
    }

    public void setGenre(List<Genre> genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be empty");
        }
        this.genre = genre;
    }

    public void addGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be empty");
        } else if (!this.genre.contains(genre)) {
            this.genre.add(genre);
        }
    }

    public void removeGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be empty");
        } else if (!this.genre.contains(genre)) {
            throw new IllegalArgumentException("Genre not found in the list");
        } else if (this.genre.size() == 1) {
            throw new IllegalStateException("There must be at least one genre in the list");
        }
        this.genre.remove(genre);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", filmCopies=" + filmCopies +
                ", rates=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(minutesDuration, film.minutesDuration) && Objects.equals(language, film.language) && Objects.equals(ageRating, film.ageRating) && Objects.equals(genre, film.genre) && Objects.equals(copyPrice, film.copyPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, minutesDuration, language, ageRating, genre, copyPrice);
    }
}