import java.time.LocalDateTime;
import java.util.*;

public class Director extends ObjectPlus {
    private String name;
    private String secondName;
    private String surname;
    private TreeSet<Film> directs = new TreeSet<>(Comparator.comparing(Film::getReleaseDate));
    private List<Film> plays = new ArrayList<>();


    public Director(String name, String secondName, String surname) {
        setName(name);
        setSecondName(secondName);
        setSurname(surname);
    }

    public Director(String name, String surname, LocalDateTime birth) {
        this(name, null, surname);
    }

    public void destroyDirector() {
        if (!directs.isEmpty()) {
            for (Film film : new ArrayList<>(directs)) {
                film.removeDirectedBy(this);
            }
            this.directs.clear();
        }
        if (!plays.isEmpty()) {
            for (Film film : new ArrayList<>(plays)) {
                film.removePlaysBy(this);
            }
            this.plays.clear();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public SortedSet<Film> getDirects() {
        return Collections.unmodifiableSortedSet(directs);
    }

    public List<Film> getPlays() {
        return Collections.unmodifiableList(plays);
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public void addDirects(Film film) {
        if (!directs.contains(film) && film != null) {
            directs.add(film);
            film.addDirectedBy(this);
        }
    }

    public void removeDirects(Film film) {
        if (directs.contains(film) && film != null) {
            if (plays.contains(film)) {
                plays.remove(film);
                film.removePlaysBy(this);
            }
            directs.remove(film);
            film.removeDirectedBy(this);
        }
    }

    public void addPlays(Film film) {
        if(film == null){
            throw new IllegalArgumentException("Film cannot be empty");
        }
        if (!directs.contains(film)) {
            throw new IllegalArgumentException("Director needs to be a director of this film in order to play in it");
        }
        if (!plays.contains(film)) {
            plays.add(film);
            film.addPlaysBy(this);
        }
    }

    public void removePlays(Film film) {
        if (plays.contains(film) && film != null) {
            plays.remove(film);
            film.removePlaysBy(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (checkIfEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        } else if (name.length() < 2) {
            throw new IllegalArgumentException("Name length must be at least 2");
        }
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (secondName == null || secondName.isBlank()) {
            this.secondName = null;
        } else if (secondName.length() < 2) {
            throw new IllegalArgumentException("Second name length must be at least 2");
        } else {
            this.secondName = secondName;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (checkIfEmpty(surname)) {
            throw new IllegalArgumentException("Surname cannot be empty");
        } else if (surname.length() < 2) {
            throw new IllegalArgumentException("Surname length must be at least 2");
        }
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", directs=" + directs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name) && Objects.equals(secondName, director.secondName) && Objects.equals(surname, director.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, surname);
    }
}
